package com.eroshenkova.conference.database.dao.conference.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.conference.ConferenceDAO;
import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.locale.DateWorker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConferenceDAOImpl extends AbstractDAO<Long, Conference> implements ConferenceDAO {
    private static final Logger LOGGER = LogManager.getLogger(ConferenceDAOImpl.class);

    private final static String SQL_FIND_BY_KEY = "SELECT * FROM conference WHERE id_conference=?";
    private final static String SQL_INSERT = "INSERT INTO conference" +
            "(topic,number_of_participants,place,date_start,date_end,deadline) VALUES(?,?,?,?,?,?)";
    private final static String SQL_UPDATE = "UPDATE conference SET id_conference=?, topic=?, " +
            "number_of_participants=?, place=?, date_start=?, date_end=?, deadline=? WHERE id_conference=?";
    private final static String SQL_DELETE = "DELETE FROM conference WHERE id_conference=?";

    private final static String SQL_FIND_BY_DATE = "SELECT * FROM conference WHERE deadline>=?";


    @Override
    public List<Conference> findByDate() throws DAOException {
        Connection connection = super.receiveConnection();
        List<Conference> conferences;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_DATE)) {
            statement.setString(1, DateWorker.receiveNow());
            conferences = super.processSelectStatement(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't find conferences by date");
            throw new DAOException(e);
        } finally {
            super.returnConnection(connection);
        }
        LOGGER.log(Level.INFO, conferences.size() + " conferences were found by date");
        return conferences;
    }

    @Override
    public Conference parseResultSet(ResultSet resultSet) throws SQLException {
        long idconf = resultSet.getLong("id_conference");
        String topic = resultSet.getString("topic");
        int numberOfParticipants = resultSet.getInt("number_of_participants");
        String place = resultSet.getString("place");
        Calendar nowGMT = Calendar.getInstance();
        Date start = resultSet.getTimestamp("date_start", nowGMT);
        Date end = resultSet.getTimestamp("date_end", nowGMT);
        Date deadline = resultSet.getTimestamp("deadline", nowGMT);
        return new Conference(idconf, topic, numberOfParticipants, place, start, end, deadline);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setLong(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Conference entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getTopic());
        statement.setInt(2, entity.getParticipantsnumber());
        statement.setString(3, entity.getPlace());
        statement.setString(4, DateWorker.formatDateTimeToSQL(entity.getBegin()));
        statement.setString(5, DateWorker.formatDateTimeToSQL(entity.getEnd()));
        statement.setString(6, DateWorker.formatDateTimeToSQL(entity.getDeadline()));
        return statement;
    }

    @Override
    public PreparedStatement receiveUpdateStatement(Connection connection, Conference entity, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, entity.getIdconference());
        statement.setString(2, entity.getTopic());
        statement.setInt(3, entity.getParticipantsnumber());
        statement.setString(4, entity.getPlace());
        statement.setString(5, DateWorker.formatDateTimeToSQL(entity.getBegin()));
        statement.setString(6, DateWorker.formatDateTimeToSQL(entity.getEnd()));
        statement.setString(7, DateWorker.formatDateTimeToSQL(entity.getDeadline()));
        if (key == null) {
            statement.setLong(8, entity.getIdconference());
        } else {
            statement.setLong(8, key);
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
