package com.eroshenkova.conference.database.pool;


import com.eroshenkova.conference.resource.DBConfigurationManager;

/**
 * Defines properties for Connection pool
 *
 * @author Palina Yerashenkava
 * @see ConnectionPool
 */
class DatabaseConfiguration {
    /**
     * URL to database
     */
    private String url;
    /**
     * Username of database owner
     */
    private String username;
    /**
     * Password to connect to database
     */
    private String password;
    /**
     * Size of connection pool
     * @see ConnectionPool
     */
    private int poolsize;

    /**
     * Initial declaration of property class
     */
    DatabaseConfiguration() {
        username = DBConfigurationManager.getProperty("username");
        url = DBConfigurationManager.getProperty("url");
        password = DBConfigurationManager.getProperty("password");
        String size = DBConfigurationManager.getProperty("poolsize");
        poolsize = Integer.parseInt(size);
    }

    /**
     * @return Url to database
     */
    String getUrl() {
        return url;
    }

    /**
     * @return username of database owner
     */
    String getUsername() {
        return username;
    }

    /**
     * @return password to connect to database
     */
    String getPassword() {
        return password;
    }

    /**
     * @return size of Connection Pool
     */
    int getPoolsize() {
        return poolsize;
    }
}
