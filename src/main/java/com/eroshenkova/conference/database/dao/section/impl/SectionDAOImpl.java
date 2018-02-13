package com.eroshenkova.conference.database.dao.section.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.section.SectionDAO;
import com.eroshenkova.conference.entity.impl.Section;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Defines individual methods for Section table in database
 * Extends AbstractDAO what stipulates maintenance of basic CRUD operations and
 * implements SectionDAO interface what makes possible extend class by individual methods
 */
public class SectionDAOImpl extends AbstractDAO<Long, Section> implements SectionDAO {
    private static final Logger LOGGER = LogManager.getLogger(SectionDAOImpl.class);

    /**
     * Query to database for selecting by key
     */
    private static final String SQL_FIND_BY_KEY = "SELECT * FROM section WHERE id_section=?";

    /**
     * Query to database for inserting new section
     */
    private static final String SQL_INSERT = "INSERT INTO section(id_conference, title) VALUES(?,?)";

    /**
     * Query to database for updating row by key
     */
    private static final String SQL_UPDATE = "UPDATE section SET id_section=?, id_conference=?, title=? " +
            "WHERE id_section=?";

    /**
     * Query to database for deleting entity by key
     */
    private static final String SQL_DELETE = "DELETE FROM section WHERE id_section=?";

    /**
     * Query to database for selecting by conference id
     */
    private static final String SQL_FIND_BY_CONFERENCE_ID = "SELECT * FROM section WHERE id_conference=?";


    /**
     * Selects all sections for certain conference
     *
     * @param id of conference to find all sections for certain conference
     * @return list if sections for certain conference
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    @Override
    public List<Section> findByConferenceId(long id) throws DAOException {
        Connection connection = super.receiveConnection();
        List<Section> sections;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_CONFERENCE_ID)) {
            statement.setLong(1, id);
            sections = super.processSelectStatement(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't find sections by conference id");
            throw new DAOException(e);
        } finally {
            super.returnConnection(connection);
        }
        LOGGER.log(Level.INFO, sections.size() + " sections were found by conference id");
        return sections;
    }

    /**
     * Parses result set to retrieve entity object
     * @param resultSet is used for further transformation in entity
     * @return parsed entity
     * @throws SQLException if database exception occurred
     */
    @Override
    public Section parseResultSet(ResultSet resultSet) throws SQLException {
        int idsect = resultSet.getInt("id_section");
        int idconf = resultSet.getInt("id_conference");
        String title = resultSet.getString("title");
        return new Section(idsect, idconf, title);
    }


    /**
     * Creates statement for further select by key
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
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
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Section entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, entity.getIdConference());
        statement.setString(2, entity.getTitle());
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
    public PreparedStatement receiveUpdateStatement(Connection connection, Section entity, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
        statement.setLong(1, entity.getIdsection());
        statement.setLong(2, entity.getIdConference());
        statement.setString(3, entity.getTitle());
        if (key == null) {
            statement.setLong(4, entity.getIdsection());
        } else {
            statement.setLong(4, key);
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
    public PreparedStatement receiveDeleteStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, key);
        return statement;
    }
}
