package db.dao.entry.impl;

import db.dao.AbstractDAO;
import db.dao.entry.IEntryDAO;
import db.dao.entry.entity.Entry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class EntryDAO extends AbstractDAO<Long, Entry> implements IEntryDAO {
    private static final Logger LOGGER = LogManager.getLogger(EntryDAO.class);

    private static final String SQL_FIND_BY_KEY = "SELECT * FROM entry WHERE id_entry=?";
    private static final String SQL_INSERT = "INSERT INTO entry(login) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE entry SET id_entry=?, login=?, status=? WHERE id_entry=?";
    private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM entry WHERE login=?";
    private static final String SQL_DELETE = "DELETE FROM entry WHERE id_entry=?";

    @Override
    public List<Entry> findByLogin(String login) {
        Connection connection = super.receiveConnection();
        List<Entry> entries = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            statement.setString(1, login);
            entries = super.receiveChildSelect(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Creating statement from database failed");
        } finally {
            super.returnConnection(connection);
        }
        return entries;
    }

    @Override
    public Entry parseResultset(ResultSet resultSet) throws SQLException {
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
    public PreparedStatement receiveDeleteStatement(Connection connection, Entry entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, entity.getIdentry());
        return statement;
    }
}
