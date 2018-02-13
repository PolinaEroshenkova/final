package com.eroshenkova.conference.database.dao.sectionentry.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.sectionentry.SectionEntryDAO;
import com.eroshenkova.conference.entity.impl.SectionEntry;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Defines individual methods for intermediate table in database
 * Extends AbstractDAO what stipulates maintenance of basic CRUD operations and
 * implements SectionEntryDAO interface what makes possible extend class by individual methods
 * @see AbstractDAO
 * @see SectionEntryDAO
 * @author Palina Yerashenkava
 */
public class SectionEntryDAOImpl extends AbstractDAO<Long, SectionEntry> implements SectionEntryDAO {
    private static final Logger LOGGER = LogManager.getLogger(SectionEntryDAOImpl.class);

    /**
     * Query to database for selecting by key
     */
    private static final String SQL_CREATE = "INSERT INTO sectionentry VALUES(?,?)";

    /**
     * Query to database for deleting by key
     */
    private static final String SQL_DELETE = "DELETE FROM sectionentry WHERE id_entry=?";

    /**
     * Query to database for selecting by entry id
     */
    private static final String SQL_FIND_BY_ENTRY_ID = "SELECT id_section,id_entry " +
            "FROM sectionentry WHERE id_entry=?";

    /**
     * Selects from intermediate table entities by entry id
     *
     * @param id entry
     * @return list of intermediate table entities
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    @Override
    public List<SectionEntry> findByEntryId(long id) throws DAOException {
        Connection connection = super.receiveConnection();
        List<SectionEntry> sectionEntries;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ENTRY_ID)) {
            statement.setLong(1, id);
            sectionEntries = processSelectStatement(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't find sectionentry by entry id");
            throw new DAOException(e);
        }
        LOGGER.log(Level.INFO, sectionEntries.size() + " sectionentries were found by entry id");
        return sectionEntries;
    }


    /**
     * Parses result set to retrieve entity object
     * @param resultSet is used for further transformation in entity
     * @return parsed entity
     * @throws SQLException if database exception occurred
     */
    @Override
    public SectionEntry parseResultSet(ResultSet resultSet) throws SQLException {
        long idsection = resultSet.getLong("id_section");
        long identry = resultSet.getLong("id_entry");
        return new SectionEntry(identry, idsection);
    }


    /**
     * Always returns UnsupportedOperationException because table is intermediate
     * and selecting by key cannot return one entity
     * @param connection is used for creating prepared statement
     * @param key        is used as primary key in table
     * @return PreparedStatement for processing
     */
    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) {
        throw new UnsupportedOperationException();
    }


    /**
     * Creates statement for further inserting to table
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for create statement
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, SectionEntry entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, entity.getIdsection());
        statement.setLong(2, entity.getIdentry());
        return statement;
    }

    /**
     * Always returns UnsupportedOperationException because table is intermediate
     * and updating by key is impossible because of key complexity
     * @param connection is used for creating prepared statement
     * @param entity     is database entity for retrieving data for update statement
     * @param key        is used as primary key in table
     * @return PreparedStatement for further processing
     */
    @Override
    public PreparedStatement receiveUpdateStatement(Connection connection, SectionEntry entity, Long key) {
        throw new UnsupportedOperationException();
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
