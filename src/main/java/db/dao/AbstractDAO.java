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

    public boolean execute(DAOCommandEnum command, T entity, K key) {
        Connection connection = receiveConnection();
        boolean flag = false;
        PreparedStatement statement = null;
        try {
            switch (command) {
                case CREATE:
                    statement = receiveCreateStatement(connection, entity);
                    break;
                case UPDATE:
                    statement = receiveUpdateStatement(connection, entity, key);
                    break;
                case DELETE:
                    statement = receiveDeleteStatement(connection, entity);
                    break;
                default: //EXCEPTION
            }
            statement.execute();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Statement preparation error");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Statement close error");
            }
            returnConnection(connection);
        }
        return flag;
    }

    public boolean execute(DAOCommandEnum command, T entity) {
        return execute(command, entity, null);
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
        List<T> entities = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            T entity = parseResultset(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    public abstract T parseResultset(ResultSet resultSet) throws SQLException;

    public abstract PreparedStatement receiveFindByKeyStatement(Connection connection, K key) throws SQLException;

    public abstract PreparedStatement receiveCreateStatement(Connection connection, T entity) throws SQLException;

    public abstract PreparedStatement receiveUpdateStatement(Connection connection, T entity, K key) throws SQLException;

    public abstract PreparedStatement receiveDeleteStatement(Connection connection, T entity) throws SQLException;
}
