package db.dao.conference.impl;

import db.dao.AbstractDAO;
import db.dao.conference.IConferenceDAO;
import db.dao.conference.entity.Conference;
import locale.DateWorker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ConferenceDAO extends AbstractDAO<Integer, Conference> implements IConferenceDAO {
    private static final Logger LOGGER = LogManager.getLogger(ConferenceDAO.class);

    private final static String SQL_FIND_BY_KEY = "SELECT * FROM conference WHERE id_conference=?";
    private final static String SQL_INSERT = "INSERT INTO conference" +
            "(topic,number_of_participants,place,date_start,date_end,deadline) VALUES(?,?,?,?,?,?)";

    private final static String SQL_FIND_BY_DATE = "SELECT * FROM conference WHERE deadline>=?";


    @Override
    public List<Conference> findByDate() {
        Connection connection = super.receiveConnection();
        PreparedStatement statement = null;
        List<Conference> conferences = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_DATE);
            DateWorker worker = new DateWorker();
            String deadline = worker.receiveDBformatDate();
            statement.setString(1, deadline);
            conferences = super.receiveChildSelect(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Statement preparation error");
        } finally {
            super.returnConnection(connection);
        }
        return conferences;
    }

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
