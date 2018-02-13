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


/**
 * Defines the individual methods for Conference table in database.
 * Extends AbstractDAO what stipulates maintenance of basic CRUD operations and
 * implements ConferenceDAO interface what makes possible extend class by individual methods
 * @see AbstractDAO
 * @see ConferenceDAO
 * @author Palina Yerashenkava
 */
public class ConferenceDAOImpl extends AbstractDAO<Long, Conference> implements ConferenceDAO {
    private static final Logger LOGGER = LogManager.getLogger(ConferenceDAOImpl.class);

    /**
     * Query to database for selecting by key
     */
    private final static String SQL_FIND_BY_KEY = "SELECT id_conference, topic," +
            "number_of_participants, place, date_start, date_end, deadline " +
            "FROM conference WHERE id_conference=?";

    /**
     * Query to database for inserting to conference table
     */
    private final static String SQL_INSERT = "INSERT INTO conference" +
            "(topic,number_of_participants,place,date_start,date_end,deadline) " +
            "VALUES(?,?,?,?,?,?)";

    /**
     * Query to database for updating row by key
     */
    private final static String SQL_UPDATE = "UPDATE conference SET id_conference=?, topic=?, " +
            "number_of_participants=?, place=?, date_start=?, date_end=?, deadline=? " +
            "WHERE id_conference=?";
    /**
     * Query to database for deleting row by key
     */
    private final static String SQL_DELETE = "DELETE FROM conference WHERE id_conference=?";

    /**
     * Query to database for selecting conferences by today's date
     */
    private final static String SQL_FIND_BY_DATE = "SELECT id_conference, topic," +
            " number_of_participants, place, date_start, date_end, deadline FROM " +
            "conference WHERE deadline>=?";


    /**
     * Finds list of Conferences by today's date
     *
     * @return List of Conferences which has deadline date more than now
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     * @see ResultSet
     * @see PreparedStatement
     */
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


    /**
     * Parses ResultSet for Conference entity
     * @param resultSet is used for further transformation in Conference entity
     * @return new Conference with retrieved fields from ResultSet
     * @throws SQLException if statement preparation exception occurred
     * @see ResultSet
     */
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

    /**
     * Creates statement for further select by key
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if statement preparation exception occurred
     * @see PreparedStatement
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
     * @throws SQLException if statement preparation exception occurred
     * @see PreparedStatement
     */
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

    /**
     * Creates statement for further updating
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for update statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if statement preparation exception occurred
     * @see PreparedStatement
     */
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

    /**
     * Creates statement for further deleting from Conference table
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     * @see PreparedStatement
     */
    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, key);
        return statement;
    }
}
