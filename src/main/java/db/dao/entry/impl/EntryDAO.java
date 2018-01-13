package db.dao.entry.impl;

import db.dao.AbstractDAO;
import db.dao.entry.IEntryDAO;
import db.dao.entry.entity.Entry;
import db.dao.section.entity.Section;
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

    private static final String SQL_FIND_BY_KEY = "SELECT * FROM ENTRY WHERE id_entry=?";
    private static final String SQL_INSERT = "INSERT INTO entry(login) VALUES(?)";
    private static final String SQL_INSERT_INTO_LINK_TABLE = "INSERT INTO sectionentry VALUES(?,@identry)";
    private static final String SQL_SET_VARIABLE = "SET @identry=LAST_INSERT_ID()";

//    private final static String SQL_FIND_ENTRY_CONFERENCE_BY_LOGIN = "SELECT conference.topic, entry.status\n" +
//            "FROM entry\n" +
//            "INNER JOIN sectionentry ON sectionentry.id_entry=entry.id_entry\n" +
//            "INNER JOIN section ON section.id_section=sectionentry.id_section\n" +
//            "INNER JOIN conference ON conference.id_conference=section.id_conference\n" +
//            "WHERE entry.login=?\n" +
//            "GROUP BY entry.id_entry";

    @Override
    public boolean create(Entry entry, List<Section> sections) {
        Connection connection = super.receiveConnection();
        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, entry.getLogin());
            statement.execute();
            statement = connection.prepareStatement(SQL_SET_VARIABLE);
            statement.execute();
            statement = connection.prepareStatement(SQL_INSERT_INTO_LINK_TABLE);
            for (Section section : sections) {
                long id = section.getIdsection();
                statement.setLong(1, id);
                statement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Transaction failed");
        } finally {
            super.returnConnection(connection);
        }
        return true;
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
