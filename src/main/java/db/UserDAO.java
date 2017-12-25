package db;

import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<String, User> {
    public static final String SQL_FIND_BY_KEY = "SELECT * FROM user WHERE login=?";


    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public User findEntityByKey(String enterLogin) {
        User user = null;
        Connection connection = super.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_KEY);
            statement.setString(1, enterLogin);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String type = resultSet.getString("type");
            user = new User(login, password, email, type);
        } catch (SQLException e) {
            //LOGGER
        }
        super.closeStatement(statement);
        return user;
    }
}
