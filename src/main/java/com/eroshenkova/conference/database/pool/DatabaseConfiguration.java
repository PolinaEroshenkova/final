package com.eroshenkova.conference.database.pool;


import com.eroshenkova.conference.resource.DBConfigurationManager;

class DatabaseConfiguration {
    private String url;
    private String username;
    private String password;
    private int poolsize;

    DatabaseConfiguration() {
        username = DBConfigurationManager.getProperty("username");
        url = DBConfigurationManager.getProperty("url");
        password = DBConfigurationManager.getProperty("password");
        String size = DBConfigurationManager.getProperty("poolsize");
        poolsize = Integer.parseInt(size);
    }

    String getUrl() {
        return url;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    int getPoolsize() {
        return poolsize;
    }
}
