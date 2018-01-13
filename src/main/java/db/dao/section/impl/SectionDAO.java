package db.dao.section.impl;

import db.dao.AbstractDAO;
import db.dao.section.ISectionDAO;
import db.dao.section.entity.Section;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SectionDAO extends AbstractDAO<Integer, Section> implements ISectionDAO {
    private static final Logger LOGGER = LogManager.getLogger(SectionDAO.class);

    private static final String SQL_FIND_BY_KEY = "SELECT * FROM section WHERE id_section=?";
    private static final String SQL_INSERT = "INSERT INTO section(id_conference, title) VALUES(?,?)";
    private static final String SQL_FIND_BY_CONFERENCE_ID = "SELECT * FROM section WHERE id_conference=?";

    @Override
    public List<Section> findByConferenceId(long id) {
        Connection connection = super.receiveConnection();
        PreparedStatement statement = null;
        List<Section> sections = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_CONFERENCE_ID);
            statement.setLong(1, id);
            sections = super.receiveChildSelect(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Statement preparation error");
        } finally {
            super.returnConnection(connection);
        }
        return sections;
    }

    @Override
    public Section parseResultset(ResultSet resultSet) throws SQLException {
        int idsect = resultSet.getInt("id_section");
        int idconf = resultSet.getInt("id_conference");
        String title = resultSet.getString("title");
        return new Section(idsect, idconf, title);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Integer key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setInt(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Section entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        statement.setLong(1, entity.getIdConference());
        statement.setString(2, entity.getTitle());
        return statement;
    }
}
