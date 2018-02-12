package com.eroshenkova.conference.database.dao.user.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.user.UserDAO;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<String, User> implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    private static final String SQL_INSERT = "INSERT INTO user(login,password,email) VALUES(?,?,?)";
    private static final String SQL_UPDATE = "UPDATE user SET login=?, password=?, email=? WHERE login=?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE login=?";
    private static final String SQL_FIND_BY_KEY = "SELECT * FROM user WHERE login=?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM user WHERE email=?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE user SET password=? WHERE login=?";

    @Override
    public User findByEmail(String email) throws DAOException {
        Connection connection = super.receiveConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {
            statement.setString(1, email);
            List<User> users = processSelectStatement(statement);
            if (users != null && !users.isEmpty()) {
                user = users.get(0);
                LOGGER.log(Level.INFO, "User was found by email: " + email);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't find user by email");
            throw new DAOException(e);
        } finally {
            super.returnConnection(connection);
        }
        return user;
    }

    @Override
    public void updatePassword(String password, String login) throws DAOException {
        Connection connection = super.receiveConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD)) {
            statement.setString(1, password);
            statement.setString(2, login);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't update user's password ");
            throw new DAOException(e);
        } finally {
            super.returnConnection(connection);
        }
    }

    @Override
    public User parseResultSet(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String type = resultSet.getString("type");
        return new User(login, password, email, type);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setString(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getEmail());
        return statement;
    }

    @Override
    public PreparedStatement receiveUpdateStatement(Connection connection, User entity, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getEmail());
        if (key == null) {
            statement.setString(4, entity.getLogin());
        } else {
            statement.setString(4, key);
        }
        return statement;
    }

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setString(1, key);
        return statement;
    }
}
