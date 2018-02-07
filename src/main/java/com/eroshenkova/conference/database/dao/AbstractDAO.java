package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.pool.ConnectionPool;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<K, T> implements DAO<K, T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    protected Connection receiveConnection() {
        ConnectionPool pool = ConnectionPool.getInstance();
        return pool.getConnection();
    }

    protected void returnConnection(Connection connection) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.closeConnection(connection);
    }

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
        return result;
    }

    public void update(T entity, K key) throws DAOException {
        Connection connection = receiveConnection();
        try (PreparedStatement statement = receiveUpdateStatement(connection, entity, key)) {
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't update table", e);
        } finally {
            returnConnection(connection);
        }
    }

    public void delete(K key) throws DAOException {
        Connection connection = receiveConnection();
        try (PreparedStatement statement = receiveDeleteStatement(connection, key)) {
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't delete entity", e);
        } finally {
            returnConnection(connection);
        }
    }

    public T findByKey(K key) throws DAOException {
        Connection connection = receiveConnection();
        T entity;
        try (PreparedStatement statement = receiveFindByKeyStatement(connection, key)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            entity = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't find entity", e);
        } finally {
            returnConnection(connection);
        }
        return entity;
    }

    protected List<T> processSelectStatement(PreparedStatement statement) throws SQLException {
        List<T> entities = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            T entity = parseResultSet(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    public abstract T parseResultSet(ResultSet resultSet) throws SQLException;

    public abstract PreparedStatement receiveFindByKeyStatement(Connection connection, K key) throws SQLException;

    public abstract PreparedStatement receiveCreateStatement(Connection connection, T entity) throws SQLException;

    public abstract PreparedStatement receiveUpdateStatement(Connection connection, T entity, K key) throws SQLException;

    public abstract PreparedStatement receiveDeleteStatement(Connection connection, K key) throws SQLException;
}
