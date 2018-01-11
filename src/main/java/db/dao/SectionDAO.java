package db.dao;

import db.AbstractDAO;
import entity.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionDAO extends AbstractDAO<Integer, Section> {
    private static final String SQL_FIND_BY_KEY = "SELECT * FROM section WHERE id_section=?";
    private static final String SQL_INSERT = "INSERT INTO section(id_conference, title) VALUES(?,?)";

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
