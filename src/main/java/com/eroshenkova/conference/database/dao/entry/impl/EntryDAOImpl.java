package com.eroshenkova.conference.database.dao.entry.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.entry.EntryDAO;
import com.eroshenkova.conference.entity.Entry;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class EntryDAOImpl extends AbstractDAO<Long, Entry> implements EntryDAO {
    private static final Logger LOGGER = LogManager.getLogger(EntryDAOImpl.class);

    private static final String SQL_FIND_BY_KEY = "SELECT * FROM entry WHERE id_entry=?";
    private static final String SQL_INSERT = "INSERT INTO entry(login) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE entry SET id_entry=?, login=?, status=? WHERE id_entry=?";
    private static final String SQL_DELETE = "DELETE FROM entry WHERE id_entry=?";

    private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM entry WHERE login=?";
    private static final String SQL_FIND_BY_STATUS = "SELECT * FROM entry WHERE status='Waiting'";

    @Override
    public List<Entry> findByLogin(String login) throws DAOException {
        Connection connection = super.receiveConnection();
        List<Entry> entries;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            statement.setString(1, login);
            entries = super.processSelectStatement(statement);
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't find entry", e);
        } finally {
            super.returnConnection(connection);
        }
        return entries;
    }

    @Override
    public List<Entry> findByStatus() throws DAOException {
        Connection connection = super.receiveConnection();
        List<Entry> entries;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS)) {
            entries = super.processSelectStatement(statement);
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't find entry", e);
        } finally {
            super.returnConnection(connection);
        }
        return entries;
    }

    @Override
    public Entry parseResultSet(ResultSet resultSet) throws SQLException {
        long identry = resultSet.getLong("id_entry");
        String login = resultSet.getString("login");
        String status = resultSet.getString("status");
        return new Entry(identry, login, status);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setLong(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Entry entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getLogin());
        return statement;
    }

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

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, key);
        return statement;
    }
}
