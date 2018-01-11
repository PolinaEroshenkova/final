package db.dao;

import db.AbstractDAO;
import entity.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantDAO extends AbstractDAO<String, Participant> {
    private static final String SQL_FIND_BY_KEY = "SELECT * FROM participant WHERE login=?";
    private static final String SQL_INSERT = "INSERT INTO participant(login,surname,name,scope) VALUES(?,?,?,?)";

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
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getScope());
        return statement;
    }
}
