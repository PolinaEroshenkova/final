package db.dao.participant.impl;

import db.dao.AbstractDAO;
import db.dao.participant.entity.Participant;

import java.sql.*;

public class ParticipantDAO extends AbstractDAO<String, Participant> {
    private static final String SQL_FIND_BY_KEY = "SELECT * FROM participant WHERE login=?";
    private static final String SQL_INSERT = "INSERT INTO participant(login,surname,name,scope) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE participant SET login=?, surname=?, name=?, " +
            "scope=?, position=?, company=? WHERE login=?";
    private static final String SQL_DELETE = "DELETE FROM participant WHERE login=?";

    @Override
    public Participant parseResultset(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString("login");
        String surname = resultSet.getString("surname");
        String name = resultSet.getString("name");
        String scope = resultSet.getString("scope");
        String position = resultSet.getString("position");
        String company = resultSet.getString("company");
        return new Participant(login, surname, name, scope, position, company);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, String key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setString(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Participant entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getScope());
        return statement;
    }

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

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Participant entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setString(1, entity.getLogin());
        return statement;
    }
}
