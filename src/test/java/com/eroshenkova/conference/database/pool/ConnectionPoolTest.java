package com.eroshenkova.conference.database.pool;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;

public class ConnectionPoolTest {

    @Test
    public void getInstance() {
        ConnectionPool pool = ConnectionPool.getInstance();
        ConnectionPool tensPool = null;
        for (int i = 0; i < 10; i++) {
            tensPool = ConnectionPool.getInstance();
        }
        Assert.assertEquals(pool, tensPool);
    }

    @Test
    public void getConnection() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        for (int i = 0; i < 20; i++) {
            connection = pool.getConnection();
        }
        Assert.assertNotNull(connection);
    }

    @Test
    public void returnConnection() {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.closeConnection(null);
    }
}
