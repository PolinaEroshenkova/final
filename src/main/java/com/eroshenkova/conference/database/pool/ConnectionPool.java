package com.eroshenkova.conference.database.pool;

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

/**
 * Defines class which holds all possible database connections
 *
 * @author Palina Yerashenkava
 */
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    /**
     * Defines fatabase configuration for creating driver
     */
    private static final DatabaseConfiguration config = new DatabaseConfiguration();

    /**
     * Instance of class to implement Singleton pattern
     */
    private static ConnectionPool instance;


    /**
     * Defines if pool is exist. Provides multithreading protection of creating several Singletons
     */
    private static AtomicBoolean instanceexist = new AtomicBoolean(false);


    /**
     * Provides instance which protects some parts of code from multi-threaded intervention
     */
    private static ReentrantLock lock = new ReentrantLock();


    /**
     * Contains pool connections
     */
    private BlockingQueue<Connection> connections;

    /**
     * Private constructor for Singleton pattern. Thread-safe.
     */
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

    /**
     * Requires method for singleton pattern. Defines pool instance
     * @return instance of Connection pool if pool is exist or creates a new entity.
     */
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

    /**
     * Receives connections from database and enter them to Blocking queue
     */
    private void addConnection() {
        try {
            Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                    config.getPassword());
            connections.put(connection);
        } catch (SQLException | InterruptedException e) {
            LOGGER.log(Level.ERROR, "Can't get connection from database");
        }
    }

    /**
     * Gives back connection from database. If queue is empty waits forever for connection
     * @return Connection from database
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Can't get connection from connection pool");
        }
        return connection;
    }

    /**
     * Provides not closing connection operation, but returning it to queue
     * @param connection from database taken before
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connections.put(connection);
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR, "Returning connection to pool is failed");
            }
        }
    }

    /**
     * Provides closing all connections from database
     */
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
