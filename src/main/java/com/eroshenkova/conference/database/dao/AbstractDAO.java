package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.pool.ConnectionPool;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes basic CRUD operations which are simple for different tables
 *
 * @param <K> is used as key
 * @param <T> is used as entity
 * @see DAO
 * @author Palina Yerashenkava
 */
public abstract class AbstractDAO<K, T> implements DAO<K, T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    /**
     * Receive connection from Connection Pool
     * Has protected modificator because this method is used only by this class and it's subclasses
     * @return Connection retrieving from Connection pool
     * @see ConnectionPool
     */
    protected Connection receiveConnection() {
        ConnectionPool pool = ConnectionPool.getInstance();
        return pool.getConnection();
    }

    /**
     * Return connection to Connection Pool
     * Has protected modificator because this method is used only by this class and it's subclasses
     * @param connection which is will be returned to pool after performing statement.
     * @see ConnectionPool
     */
    protected void returnConnection(Connection connection) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.closeConnection(connection);
    }

    /**
     * Creates new entity in database
     *
     * @param entity         is database entity for inserting to database
     * @param isKeyGenerated is used for specification the action of the insert statement.
     *                       If table after inserting entity generate key such as id, this value
     *                       would be returned
     * @return zero if inserting completed successfully and keys were not generated.
     * If keys generated return value of generated key.
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    @Override
    public long create(T entity, boolean isKeyGenerated) throws DAOException {
        Connection connection = receiveConnection();
        long result;
        try (PreparedStatement statement = receiveCreateStatement(connection, entity)) {
            statement.execute();
            result = 0;
            if (isKeyGenerated) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                result = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't add new entity", e);
        } finally {
            returnConnection(connection);
        }
        LOGGER.log(Level.INFO, "Entity was created successfully");
        return result;
    }

    /**
     * Updates existing database entity
     * @param entity is database entity for updating in database
     * @param key    is key value of the update statement
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    @Override
    public void update(T entity, K key) throws DAOException {
        Connection connection = receiveConnection();
        try (PreparedStatement statement = receiveUpdateStatement(connection, entity, key)) {
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't update table", e);
        } finally {
            returnConnection(connection);
        }
        LOGGER.log(Level.INFO, "Entity was updated successfully");
    }

    /**
     * Deletes existing database entity
     * @param key is key value of the deleted statement
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    @Override
    public void delete(K key) throws DAOException {
        Connection connection = receiveConnection();
        try (PreparedStatement statement = receiveDeleteStatement(connection, key)) {
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't delete entity", e);
        } finally {
            returnConnection(connection);
        }
        LOGGER.log(Level.INFO, "Entity was deleted successfully");
    }

    /**
     * Selects entity from database by key value
     * @param key is key value of the selected statement
     * @return found entity or null if entity wasn't found by key value
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    @Override
    public T findByKey(K key) throws DAOException {
        Connection connection = receiveConnection();
        T entity = null;
        try (PreparedStatement statement = receiveFindByKeyStatement(connection, key)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = parseResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't find entity", e);
        } finally {
            returnConnection(connection);
        }
        LOGGER.log(Level.INFO, "Entity was found successfully");
        return entity;
    }

    /**
     * Processes subclasses request by loop parsing
     * @param statement from subclass is used for complex processing
     * @return List which contains found T entities
     * @throws SQLException if database exception occurred
     */
    protected List<T> processSelectStatement(PreparedStatement statement) throws SQLException {
        List<T> entities = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            T entity = parseResultSet(resultSet);
            entities.add(entity);
        }
        return entities;
    }


    /**
     * Parses result set to retrieve entity object
     * @param resultSet is used for further transformation in entity
     * @return parsed entity
     * @throws SQLException if database exception occurred
     */
    public abstract T parseResultSet(ResultSet resultSet) throws SQLException;

    /**
     * Creates statement for further select by key
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    public abstract PreparedStatement receiveFindByKeyStatement(Connection connection, K key) throws SQLException;

    /**
     * Creates statement for further inserting to table
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for create statement
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    public abstract PreparedStatement receiveCreateStatement(Connection connection, T entity) throws SQLException;

    /**
     * Creates statement for further updating
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for update statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    public abstract PreparedStatement receiveUpdateStatement(Connection connection, T entity, K key) throws SQLException;

    /**
     * Creates statement for further deleting from table
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    public abstract PreparedStatement receiveDeleteStatement(Connection connection, K key) throws SQLException;
}
