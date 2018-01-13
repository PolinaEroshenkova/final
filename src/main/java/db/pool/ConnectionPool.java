package db.pool;

import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final DatabaseConfiguration config = new DatabaseConfiguration();
    private static ConnectionPool instance;
    private static AtomicBoolean instanceexist = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        connections = new ArrayBlockingQueue<>(config.getPoolsize());
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, "Can't register driver");
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
            LOGGER.log(Level.ERROR, "Can't get connection from database");
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Can't get connection from connection pool");
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Returning connection to pool is failed");
        }
    }

    public void closePool() {
        try {
            for (int i = 0; i < config.getPoolsize(); i++) {
                connections.take().close();
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.log(Level.ERROR, "Can't close pool");
        }
    }

}
