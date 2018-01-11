package db;


import resource.DBConfigurationManager;

public class DatabaseConfiguration {
    private String driver;
    private String url;
    private String username;
    private String password;
    private int poolsize;

    public DatabaseConfiguration() {
        driver = DBConfigurationManager.getProperty("driver");
        url = DBConfigurationManager.getProperty("url");
        username = DBConfigurationManager.getProperty("username");
        password = DBConfigurationManager.getProperty("password");
        String size = DBConfigurationManager.getProperty("poolsize");
        poolsize = Integer.parseInt(size);
    }

    public String getDriver() {
        return driver;
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
