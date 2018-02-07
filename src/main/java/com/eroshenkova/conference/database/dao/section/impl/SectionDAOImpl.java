package com.eroshenkova.conference.database.dao.section.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.section.SectionDAO;
import com.eroshenkova.conference.entity.Section;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SectionDAOImpl extends AbstractDAO<Long, Section> implements SectionDAO {
    private static final Logger LOGGER = LogManager.getLogger(SectionDAOImpl.class);

    private static final String SQL_FIND_BY_KEY = "SELECT * FROM section WHERE id_section=?";
    private static final String SQL_INSERT = "INSERT INTO section(id_conference, title) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE section SET id_section=?, id_conference=?, title=? " +
            "WHERE id_section=?";
    private static final String SQL_DELETE = "DELETE FROM section WHERE id_section=?";

    private static final String SQL_FIND_BY_CONFERENCE_ID = "SELECT * FROM section WHERE id_conference=?";

    @Override
    public List<Section> findByConferenceId(long id) throws DAOException {
        Connection connection = super.receiveConnection();
        List<Section> sections;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_CONFERENCE_ID)) {
            statement.setLong(1, id);
            sections = super.processSelectStatement(statement);
        } catch (SQLException e) {
            throw new DAOException("Database error. Can't find sections", e);
        } finally {
            super.returnConnection(connection);
        }
        return sections;
    }

    @Override
    public Section parseResultSet(ResultSet resultSet) throws SQLException {
        int idsect = resultSet.getInt("id_section");
        int idconf = resultSet.getInt("id_conference");
        String title = resultSet.getString("title");
        return new Section(idsect, idconf, title);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setLong(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Section entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, entity.getIdConference());
        statement.setString(2, entity.getTitle());
        return statement;
    }

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

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, key);
        return statement;
    }
}
