package db.dao;

import db.AbstractDAO;
import entity.Conference;
import locale.DateWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ConferenceDAO extends AbstractDAO<Integer, Conference> {
    private final static String SQL_FIND_BY_KEY = "SELECT * FROM conference WHERE id_conference=?";
    private final static String SQL_INSERT = "INSERT INTO conference" +
            "(topic,number_of_participants,place,date_start,date_end,deadline) VALUES(?,?,?,?,?,?)";

    private final static String SQL_FIND_BY_DATE = "SELECT * FROM conference WHERE deadline>=?";

    @Override
    public Conference parseResultset(ResultSet resultSet) throws SQLException {
        long idconf = resultSet.getLong("id_conference");
        String topic = resultSet.getString("topic");
        int numberOfParticipants = resultSet.getInt("number_of_participants");
        String place = resultSet.getString("place");
        Date start = resultSet.getDate("date_start");
        Date end = resultSet.getDate("date_end");
        Date deadline = resultSet.getDate("deadline");
        Conference conference = new Conference(idconf, topic, numberOfParticipants, place);
        DateWorker dateWorker = new DateWorker();
        conference.setBegin(dateWorker.receiveFormatDateByLocale(start));
        conference.setEnd(dateWorker.receiveFormatDateByLocale(end));
        conference.setDeadline(dateWorker.receiveFormatDateByLocale(deadline));
        return conference;
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Integer key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setInt(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Conference entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        statement.setString(1, entity.getTopic());
        statement.setInt(2, entity.getParticipantsnumber());
        statement.setString(3, entity.getPlace());
        statement.setString(4, entity.getBegin());
        statement.setString(5, entity.getEnd());
        statement.setString(6, entity.getDeadline());
        return statement;
    }
}
