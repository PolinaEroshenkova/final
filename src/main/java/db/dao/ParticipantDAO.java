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


    public ParticipantDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Participant findEntityByKey(String key) {
        Participant participant = null;
        Connection connection = super.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_KEY);
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String login = resultSet.getString("login");
            String surname = resultSet.getString("surname");
            String name = resultSet.getString("name");
            String scope = resultSet.getString("scope");
            String position = resultSet.getString("position");
            String company = resultSet.getString("company");
            participant = new Participant(login, surname, name, scope, position, company);
        } catch (SQLException e) {
            //LOGGER
        } finally {
            super.closeStatement(statement);
        }
        return participant;
    }

    @Override
    public boolean create(Participant entity) {
        Connection connection = super.getConnection();
        PreparedStatement statement = null;
        boolean result = false;
        try {
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getScope());
            statement.execute();
            result = true;
        } catch (SQLException e) {
            //LOGGER
        } finally {
            super.closeStatement(statement);
        }
        return result;
    }
}
