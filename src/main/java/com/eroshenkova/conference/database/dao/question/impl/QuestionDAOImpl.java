package com.eroshenkova.conference.database.dao.question.impl;

import com.eroshenkova.conference.database.dao.AbstractDAO;
import com.eroshenkova.conference.database.dao.question.QuestionDAO;
import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDAOImpl extends AbstractDAO<Long, Question> implements QuestionDAO {
    private static final Logger LOGGER = LogManager.getLogger(QuestionDAOImpl.class);

    private static final String SQL_FIND_BY_KEY = "SELECT * FROM question WHERE id_question=?";
    private static final String SQL_INSERT = "INSERT INTO question(login,question,answer) VALUES(?,?,?)";
    private static final String SQL_UPDATE = "UPDATE question SET login=?, question=?, answer=? WHERE id_question=?";
    private static final String SQl_DELETE = "DELETE FROM question WHERE id_question=?";

    private static final String SQL_FIND_WITH_ANSWER = "SELECT * FROM question WHERE answer IS NOT NULL";
    private static final String SQL_FIND_WITHOUT_ANSWER = "SELECT * FROM question WHERE answer IS NULL";


    @Override
    public List<Question> findWithoutAnswer() throws DAOException {
        Connection connection = super.receiveConnection();
        List<Question> questions;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_WITHOUT_ANSWER)) {
            questions = processSelectStatement(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't find questions without answer");
            throw new DAOException(e);
        } finally {
            super.returnConnection(connection);
        }
        LOGGER.log(Level.INFO, questions.size() + " questions without answer were found");
        return questions;
    }

    @Override
    public List<Question> findWithAnswer() throws DAOException {
        Connection connection = super.receiveConnection();
        List<Question> questions;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_WITH_ANSWER)) {
            questions = processSelectStatement(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't find questions with answer");
            throw new DAOException(e);
        } finally {
            super.returnConnection(connection);
        }
        LOGGER.log(Level.INFO, questions.size() + " questions with answer were found");
        return questions;
    }

    @Override
    public Question parseResultSet(ResultSet resultSet) throws SQLException {
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
        if (key == null) {
            statement.setLong(4, entity.getIdquestion());
        } else {
            statement.setLong(4, key);
        }
        return statement;
    }

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQl_DELETE);
        statement.setLong(1, key);
        return statement;
    }
}
