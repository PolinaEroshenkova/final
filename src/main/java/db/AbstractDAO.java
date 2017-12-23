package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractDAO<T> {
    private FabricMySQLDriver driver;
    private final String URL = "jdbc:mysql://localhost:3306/bonus?autoReconnect=true&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "2510";

    public AbstractDAO(){
        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            //connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            //LOGGER
        }
    }

    public abstract boolean create(T entity);
    public abstract ArrayList<T> findAll();
    public abstract boolean deleteBy(String login);
    public abstract boolean deleteAllUsers();
    public abstract ArrayList<T> findBy(String login);
    public abstract boolean update(String field,String change,String login);

    public void close(Connection connection){
        try{
            if(connection!=null){
                connection.close();
            }
        }
        catch (SQLException e){
            //LOGGER
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
