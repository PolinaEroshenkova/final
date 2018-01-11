package db;

import entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO<K, T extends Entity> implements DAO<K, T> {

    public boolean create(T entity) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        boolean flag = false;
        try (PreparedStatement statement = receiveCreateStatement(connection, entity)) {
            flag = statement.execute();
        } catch (SQLException e) {
            //LOGGER
        } finally {
            pool.closeConnection(connection);
        }
        return flag;
    }

    public T findByKey(K key) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        T entity = null;
        try (PreparedStatement statement = receiveFindByKeyStatement(connection, key)) {
            ResultSet resultSet = statement.executeQuery();
            entity = parseResultset(resultSet);
        } catch (SQLException e) {
            //LOGGER
        } finally {
            pool.closeConnection(connection);
        }
        return entity;
    }

    public abstract T parseResultset(ResultSet resultSet) throws SQLException;

    public abstract PreparedStatement receiveFindByKeyStatement(Connection connection, K key) throws SQLException;

    public abstract PreparedStatement receiveCreateStatement(Connection connection, T entity) throws SQLException;
}
