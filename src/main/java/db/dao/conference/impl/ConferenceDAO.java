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

public class ConferenceDAO extends AbstractDAO<Long, Conference> implements IConferenceDAO {
    private static final Logger LOGGER = LogManager.getLogger(ConferenceDAO.class);

    private final static String SQL_FIND_BY_KEY = "SELECT * FROM conference WHERE id_conference=?";
    private final static String SQL_INSERT = "INSERT INTO conference" +
            "(topic,number_of_participants,place,date_start,date_end,deadline) VALUES(?,?,?,?,?,?)";
    private final static String SQL_UPDATE = "UPDATE conference SET id_conference=?, topic=?, " +
            "number_of_participants=?, place=?, date_start=?, date_end=?, deadline=? WHERE id_conference=?";
    private final static String SQL_DELETE = "DELETE FROM conference WHERE id_conference=?";

    private final static String SQL_FIND_BY_DATE = "SELECT * FROM conference WHERE deadline>=?";
    private final static String SQL_FIND_BY_LOGIN = "SELECT * FROM conference";


    @Override
    public List<Conference> findByDate() {
        Connection connection = super.receiveConnection();
        List<Conference> conferences = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_DATE)) {
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
    public List<Conference> findByLogin() {
        return null;
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
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setLong(1, key);
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

    @Override
    public PreparedStatement receiveUpdateStatement(Connection connection, Conference entity, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
        statement.setLong(1, entity.getIdconference());
        statement.setString(2, entity.getTopic());
        statement.setInt(3, entity.getParticipantsnumber());
        statement.setString(4, entity.getPlace());
        statement.setString(5, entity.getBegin()); //TODO проверить корректность даты
        statement.setString(6, entity.getEnd());
        statement.setString(7, entity.getDeadline());
        if (key == null) {
            statement.setLong(8, entity.getIdconference());
        } else {
            statement.setLong(8, key);
        }
        return statement;
    }

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Conference entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, entity.getIdconference());
        return statement;
    }
}
