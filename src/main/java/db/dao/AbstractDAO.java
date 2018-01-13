package db.dao;

import db.DAO;
import db.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
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

    public boolean create(T entity) {
        Connection connection = receiveConnection();
        boolean flag = false;
        try (PreparedStatement statement = receiveCreateStatement(connection, entity)) {
            flag = statement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Statement preparation error while inserting data");
        } finally {
            returnConnection(connection);
        }
        return flag;
    }

    public T findByKey(K key) {
        Connection connection = receiveConnection();
        T entity = null;
        try (PreparedStatement statement = receiveFindByKeyStatement(connection, key)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            entity = parseResultset(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Statement preparation error while selection by key");
        } finally {
            returnConnection(connection);
        }
        return entity;
    }

    protected List<T> receiveChildSelect(PreparedStatement statement) throws SQLException {
        List<T> entities = null;
        ResultSet resultSet = statement.executeQuery();
        entities = new ArrayList<>();
        while (resultSet.next()) {
            T entity = parseResultset(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    public abstract T parseResultset(ResultSet resultSet) throws SQLException;

    public abstract PreparedStatement receiveFindByKeyStatement(Connection connection, K key) throws SQLException;

    public abstract PreparedStatement receiveCreateStatement(Connection connection, T entity) throws SQLException;
}
