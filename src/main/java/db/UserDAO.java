package db;

import entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO implements DAO<User> {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User entity) {
        boolean flag = false;
        try (Statement statement = connection.createStatement()) {
            String query = "INSERT INTO user VALUES('" + entity.getLogin() + "','" + entity.getPassword() + "','" +
                    entity.getEmail() + "','user');";
            statement.execute(query);
            flag = true;
        } catch (SQLException e) {
            //LOGGER
        }
        return flag;
    }

    @Override
    public ArrayList<User> find() {

        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }
}
