package com.eroshenkova.conference.db.dao.question.impl;

import com.eroshenkova.conference.db.dao.AbstractDAO;
import com.eroshenkova.conference.db.dao.question.IQuestionDAO;
import com.eroshenkova.conference.db.dao.question.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDAO extends AbstractDAO<Long, Question> implements IQuestionDAO {

    private static final String SQL_FIND_BY_KEY = "SELECT * FROM question WHERE id_question=?";
    private static final String SQL_INSERT = "INSERT INTO question(login,question,answer) VALUES(?,?,?)";
    private static final String SQL_UPDATE = "UPDATE question SET login=?, question=?, answer=? WHERE id_question=?";
    private static final String SQl_DELETE = "DELETE FROM question WHERE id_question=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM question";


    @Override
    public Question parseResultset(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_question");
        String login = resultSet.getString("login");
        String question = resultSet.getString("question");
        String answer = resultSet.getString("answer");
        return new Question(id, login, question, answer);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setLong(1, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Question entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getQuestion());
        statement.setString(3, entity.getAnswer());
        return statement;
    }

    @Override
    public PreparedStatement receiveUpdateStatement(Connection connection, Question entity, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getQuestion());
        statement.setString(3, entity.getAnswer());
        statement.setLong(4, key);
        return statement;
    }

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Question entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQl_DELETE);
        statement.setLong(1, entity.getIdquestion());
        return statement;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = null;
        try (PreparedStatement statement = receiveConnection().prepareStatement(SQL_FIND_ALL)) {
            questions = receiveChildSelect(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
