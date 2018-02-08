package com.eroshenkova.conference.database.dao.sectionentry.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.sectionentry.SectionEntryDAO;
import com.eroshenkova.conference.entity.SectionEntry;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SectionEntryDAOImpl extends AbstractDAO<Long, SectionEntry> implements SectionEntryDAO {
    private static final Logger LOGGER = LogManager.getLogger(SectionEntryDAOImpl.class);

    private static final String SQL_CREATE = "INSERT INTO sectionentry VALUES(?,?)";
    private static final String SQL_DELETE = "DELETE FROM sectionentry WHERE id_entry=?";
    private static final String SQL_UPDATE = "UPDATE sectionentry SET id_section=?, id_entry=? WHERE id_entry=?";
    private static final String SQL_FIND_BY_ENTRY_ID = "SELECT * FROM sectionentry WHERE id_entry=?";


    @Override
    public SectionEntry parseResultSet(ResultSet resultSet) throws SQLException {
        long idsection = resultSet.getLong("id_section");
        long identry = resultSet.getLong("id_entry");
        return new SectionEntry(identry, idsection);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ENTRY_ID);
        statement.setLong(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, SectionEntry entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, entity.getIdsection());
        statement.setLong(2, entity.getIdentry());
        return statement;
    }

    @Override
    public PreparedStatement receiveUpdateStatement(Connection connection, SectionEntry entity, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
        statement.setLong(1, entity.getIdsection());
        statement.setLong(2, entity.getIdentry());
        if (key == null) {
            statement.setLong(3, entity.getIdentry());
        } else {
            statement.setLong(3, key);
        }
        return statement;
    }

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, key);
        return statement;
    }


    @Override
    public List<SectionEntry> findByEntryId(long id) throws DAOException {
        Connection connection = super.receiveConnection();
        List<SectionEntry> sectionEntries;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ENTRY_ID)) {
            statement.setLong(1, id);
            sectionEntries = processSelectStatement(statement);
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't find sectionentry", e);
        }
        return sectionEntries;
    }
}
