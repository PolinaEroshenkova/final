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

/**
 * Defines individual methods for User table in database
 * Extends AbstractDAO what stipulates maintenance of basic CRUD operations and
 * implements UserDAO interface what makes possible extend class by individual methods
 */
public class UserDAOImpl extends AbstractDAO<String, User> implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    /**
     * Query to database for inserting new user
     */
    private static final String SQL_INSERT = "INSERT INTO user(login,password,email) VALUES(?,?,?)";

    /**
     * Query to database for updating user by key
     */
    private static final String SQL_UPDATE = "UPDATE user SET login=?, password=?, email=? WHERE login=?";

    /**
     * Query to database for deleting row by key
     */
    private static final String SQL_DELETE = "DELETE FROM user WHERE login=?";

    /**
     * Query to database for selecting by key
     */
    private static final String SQL_FIND_BY_KEY = "SELECT * FROM user WHERE login=?";

    /**
     * Query to database for selecting by email address
     */
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM user WHERE email=?";

    /**
     * Query to database for updating user's password
     */
    private static final String SQL_UPDATE_PASSWORD = "UPDATE user SET password=? WHERE login=?";

    /**
     * Selects user by email address
     *
     * @param email user's personal email address
     * @return found user entity
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
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


    /**
     * Updates password of certain user
     * @param password new user's password value
     * @param login user's personal identificator
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
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


    /**
     * Parses result set to retrieve entity object
     * @param resultSet is used for further transformation in entity
     * @return parsed entity
     * @throws SQLException if database exception occurred
     */
    @Override
    public User parseResultSet(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String type = resultSet.getString("type");
        return new User(login, password, email, type);
    }


    /**
     * Creates statement for further select by key
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setString(1, key);
        return statement;
    }


    /**
     * Creates statement for further inserting to table
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for create statement
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getEmail());
        return statement;
    }

    /**
     * Creates statement for further updating
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for update statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
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


    /**
     * Creates statement for further deleting from table
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setString(1, key);
        return statement;
    }
}
