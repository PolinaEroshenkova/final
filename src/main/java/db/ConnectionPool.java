package db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool pool;
    private static final String DATASOURCE_NAME = "jdbc/conference";
    private static DataSource datasource;
    private static ReentrantLock lock = new ReentrantLock();

    static {
        try {
            Context initcontext = new InitialContext();
            Context envcontext = (Context) initcontext.lookup("java:/comp/env");
            datasource = (DataSource) envcontext.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            //LOGGER
        }
    }

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        try {
            lock.lock();
            if (pool == null) {
                pool = new ConnectionPool();
            }
        } finally {
            lock.unlock();
        }
        return pool;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            lock.lock();
            connection = datasource.getConnection();
        } finally {
            lock.unlock();
        }
        return connection;
    }

    public void returnConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
