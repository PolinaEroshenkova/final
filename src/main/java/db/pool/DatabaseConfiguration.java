package db.pool;


import resource.DBConfigurationManager;

public class DatabaseConfiguration {
    private String url;
    private String username;
    private String password;
    private int poolsize;

    public DatabaseConfiguration() {
        username = DBConfigurationManager.getProperty("username");
        url = DBConfigurationManager.getProperty("url");
        password = DBConfigurationManager.getProperty("password");
        String size = DBConfigurationManager.getProperty("poolsize");
        poolsize = Integer.parseInt(size);
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolsize() {
        return poolsize;
    }
}
