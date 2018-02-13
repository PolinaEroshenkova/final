package com.eroshenkova.conference.database.dao.entry.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.entry.EntryDAO;
import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Defines individual methods for Entry table in database
 * Extends AbstractDAO what stipulates maintenance of basic CRUD operations and
 * implements EntryDAO interface what makes possible extend class by individual methods
 * @see AbstractDAO
 * @see EntryDAO
 * @author Palina Yerashenkava
 */
public class EntryDAOImpl extends AbstractDAO<Long, Entry> implements EntryDAO {
    private static final Logger LOGGER = LogManager.getLogger(EntryDAOImpl.class);

    /**
     * Query to database for selecting by key
     */
    private static final String SQL_FIND_BY_KEY = "SELECT id_entry,login,status " +
            "FROM entry WHERE id_entry=?";

    /**
     * Query to database for inserting new entry
     */
    private static final String SQL_INSERT = "INSERT INTO entry(login) VALUES(?)";

    /**
     * Query to database for updating existing entry
     */
    private static final String SQL_UPDATE = "UPDATE entry SET id_entry=?, login=?, status=? " +
            "WHERE id_entry=?";

    /**
     * Query to database for deleting existing entry
     */
    private static final String SQL_DELETE = "DELETE FROM entry WHERE id_entry=?";

    /**
     * Query to database for selecting by user's login
     */
    private static final String SQL_FIND_BY_LOGIN = "SELECT id_entry,login,status " +
            "FROM entry WHERE login=?";

    /**
     * Query to database for selecting by waiting status
     */
    private static final String SQL_FIND_BY_STATUS = "SELECT id_entry,login,status " +
            "FROM entry WHERE status='Waiting'";

    /**
     * Selects from entry database entries by user's login
     *
     * @param login user's login
     * @return List of entries for certain user
     * @throws DAOException if database exception occurred
     */
    @Override
    public List<Entry> findByLogin(String login) throws DAOException {
        Connection connection = super.receiveConnection();
        List<Entry> entries;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            statement.setString(1, login);
            entries = super.processSelectStatement(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't find entries by login");
            throw new DAOException(e);
        } finally {
            super.returnConnection(connection);
        }
        LOGGER.log(Level.INFO, entries.size() + " entries were found by login");
        return entries;
    }


    /**
     * Selects from entry database entries by waiting status
     * @return List of entries with waiting status
     * @throws DAOException if database exception occurred
     */
    @Override
    public List<Entry> findByStatus() throws DAOException {
        Connection connection = super.receiveConnection();
        List<Entry> entries;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS)) {
            entries = super.processSelectStatement(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't find entries by status");
            throw new DAOException(e);
        } finally {
            super.returnConnection(connection);
        }
        LOGGER.log(Level.INFO, entries.size() + " entries were found by status");
        return entries;
    }


    /**
     * Parses result set to retrieve entity object
     * @param resultSet is used for further transformation in entity
     * @return parsed entity
     * @throws SQLException if database exception occurred
     */
    @Override
    public Entry parseResultSet(ResultSet resultSet) throws SQLException {
        long identry = resultSet.getLong("id_entry");
        String login = resultSet.getString("login");
        String status = resultSet.getString("status");
        return new Entry(identry, login, status);
    }

    /**
     * Creates statement for further select by key
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setLong(1, key);
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
    public PreparedStatement receiveCreateStatement(Connection connection, Entry entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getLogin());
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
    public PreparedStatement receiveUpdateStatement(Connection connection, Entry entity, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
        statement.setLong(1, entity.getIdentry());
        statement.setString(2, entity.getLogin());
        statement.setString(3, entity.getStatus());
        if (key == null) {
            statement.setLong(4, entity.getIdentry());
        } else {
            statement.setLong(4, key);
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
    public PreparedStatement receiveDeleteStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, key);
        return statement;
    }
}
