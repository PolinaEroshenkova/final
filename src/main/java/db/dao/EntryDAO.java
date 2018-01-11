package db.dao;

import db.AbstractDAO;
import entity.Entry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryDAO extends AbstractDAO<Integer, Entry> {
    private static final String SQL_FIND_BY_KEY = "SELECT * FROM ENTRY WHERE id_entry=?";
    private static final String SQL_INSERT = "INSERT INTO entry(login) VALUES(?)";

//    private final static String SQL_FIND_ENTRY_CONFERENCE_BY_LOGIN = "SELECT conference.topic, entry.status\n" +
//            "FROM entry\n" +
//            "INNER JOIN sectionentry ON sectionentry.id_entry=entry.id_entry\n" +
//            "INNER JOIN section ON section.id_section=sectionentry.id_section\n" +
//            "INNER JOIN conference ON conference.id_conference=section.id_conference\n" +
//            "WHERE entry.login=?\n" +
//            "GROUP BY entry.id_entry";

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
