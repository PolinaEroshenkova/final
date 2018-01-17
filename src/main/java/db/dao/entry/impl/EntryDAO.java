package db.dao.entry.impl;

import db.dao.AbstractDAO;
import db.dao.entry.IEntryDAO;
import db.dao.entry.entity.Entry;
import db.dao.section.entity.Section;
import db.dao.sectionentry.entity.SectionEntry;
import db.dao.sectionentry.impl.SectionEntryDAO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EntryDAO extends AbstractDAO<Integer, Entry> implements IEntryDAO {
    private static final Logger LOGGER = LogManager.getLogger(EntryDAO.class);

    private static final String SQL_FIND_BY_KEY = "SELECT * FROM entry WHERE id_entry=?";
    private static final String SQL_INSERT = "INSERT INTO entry(login) VALUES(?)";
    private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM entry WHERE login=?";

    @Override
    public boolean create(Entry entry, List<Section> sections) {
        Connection connection = super.receiveConnection();
        boolean flag = false;
        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, entry.getLogin());
            statement.execute();
            AbstractDAO<List<Integer>, SectionEntry> dao = new SectionEntryDAO();
            for (Section section : sections) {
                SectionEntry sectionEntry = new SectionEntry(section.getIdsection());
                statement = dao.receiveCreateStatement(connection, sectionEntry);
                statement.execute();
            }
            connection.commit();
            flag = true;
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Transaction failed");
        } finally {
            super.returnConnection(connection);
        }
        return flag;
    }

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
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Integer key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setInt(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Entry entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        statement.setString(1, entity.getLogin());
        return statement;
    }
}
