package com.eroshenkova.conference.database.dao.participant;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.entity.impl.Participant;

import java.sql.*;

/**
 * Defines individual methods for Participant table in database
 * Extends AbstractDAO what stipulates maintenance of basic CRUD operations
 * @see AbstractDAO
 * @author Palina Yerashenkava
 */
public class ParticipantDAO extends AbstractDAO<String, Participant> {

    /**
     * Query to database for selecting by key
     */
    private static final String SQL_FIND_BY_KEY = "SELECT login,surname,name,scope,position,company " +
            "FROM participant WHERE login=?";

    /**
     * Query to database for inserting new participant
     */
    private static final String SQL_INSERT = "INSERT INTO participant(login,surname,name,scope,position,company) VALUES(?,?,?,?,?,?)";

    /**
     * Query to database for updating by key
     */
    private static final String SQL_UPDATE = "UPDATE participant SET login=?, surname=?, name=?, " +
            "scope=?, position=?, company=? WHERE login=?";

    /**
     * Query to database for deleting by key
     */
    private static final String SQL_DELETE = "DELETE FROM participant WHERE login=?";


    /**
     * Parses result set to retrieve entity object
     *
     * @param resultSet is used for further transformation in entity
     * @return parsed entity
     * @throws SQLException if database exception occurred
     */
    @Override
    public Participant parseResultSet(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString("login");
        String surname = resultSet.getString("surname");
        String name = resultSet.getString("name");
        String scope = resultSet.getString("scope");
        String position = resultSet.getString("position");
        String company = resultSet.getString("company");
        return new Participant(login, surname, name, scope, position, company);
    }


    /**
     * Creates statement for further select by key
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setString(1, key);
        return statement;
    }

    /**
     * Creates statement for further inserting to table
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for create statement
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Participant entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getScope());
        statement.setString(5, entity.getPosition());
        statement.setString(6, entity.getCompany());
        return statement;
    }

    /**
     * Creates statement for further updating
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for update statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveUpdateStatement(Connection connection, Participant entity, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getScope());
        statement.setString(5, entity.getPosition());
        statement.setString(6, entity.getCompany());
        if (key == null) {
            statement.setString(7, entity.getLogin());
        } else {
            statement.setString(7, key);
        }
        return statement;
    }


    /**
     * Creates statement for further deleting from table
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setString(1, key);
        return statement;
    }
}
