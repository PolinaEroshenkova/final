package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connections;
    private static AtomicBoolean instanceexist = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private static final DatabaseConfiguration config = new DatabaseConfiguration();

    private ConnectionPool() {
        connections = new ArrayBlockingQueue<>(config.getPoolsize());
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < config.getPoolsize(); i++) {
            addConnection();
        }
    }

    public static ConnectionPool getInstance() {
        if (!instanceexist.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceexist.getAndSet(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void addConnection() {
        try {
            Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                    config.getPassword());
            connections.put(connection);
        } catch (SQLException | InterruptedException e) {
            //LOGGER
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            //LOGGER
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {

        }
    }

    public void closePool() {
        try {
            for (int i = 0; i < config.getPoolsize(); i++) {
                connections.take().close();
            }
        } catch (SQLException | InterruptedException e) {

        }
    }


//    private static ConnectionPool pool;
//    private static final String DATASOURCE_NAME = "jdbc/conference";
//    private static DataSource datasource;
//    private static ReentrantLock lock = new ReentrantLock();
//
//    static {
//        try {
//            Context initcontext = new InitialContext();
//            Context envcontext = (Context) initcontext.lookup("java:/comp/env");
//            datasource = (DataSource) envcontext.lookup(DATASOURCE_NAME);
//        } catch (NamingException e) {
//            //LOGGER
//        }
//    }
//
//    private ConnectionPool() {
//    }
//
//    public static ConnectionPool getInstance() {
//        try {
//            lock.lock();
//            if (pool == null) {
//                pool = new ConnectionPool();
//            }
//        } finally {
//            lock.unlock();
//        }
//        return pool;
//    }
//
//    public Connection getConnection() throws SQLException {
//        Connection connection = null;
//        try {
//            lock.lock();
//            connection = datasource.getConnection();
//        } finally {
//            lock.unlock();
//        }
//        return connection;
//    }
//
//    public void returnConnection(Connection connection) throws SQLException {
//        connection.close();
//    }

}
