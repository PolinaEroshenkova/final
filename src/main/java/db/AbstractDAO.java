package db;

import entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO<K, T extends Entity> {
    private Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //LOGGER
        }
    }

    public abstract T findEntityByKey(K key);
}
