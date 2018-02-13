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


/**
 * Defines individual methods for Question table in database
 * Extends AbstractDAO what stipulates maintenance of basic CRUD operations and
 * implements QuestionDAO interface what makes possible extend class by individual methods
 */
public class QuestionDAOImpl extends AbstractDAO<Long, Question> implements QuestionDAO {
    private static final Logger LOGGER = LogManager.getLogger(QuestionDAOImpl.class);

    /**
     * Query to database for selecting by key
     */
    private static final String SQL_FIND_BY_KEY = "SELECT * FROM question WHERE id_question=?";

    /**
     * Query to database for inserting new question
     */
    private static final String SQL_INSERT = "INSERT INTO question(login,question,answer) VALUES(?,?,?)";

    /**
     * Query to database for updating existing entity
     */
    private static final String SQL_UPDATE = "UPDATE question SET login=?, question=?, answer=? WHERE id_question=?";

    /**
     * Query to database for deleting entry by key
     */
    private static final String SQl_DELETE = "DELETE FROM question WHERE id_question=?";

    /**
     * Query to database for selecting questions with answer
     */
    private static final String SQL_FIND_WITH_ANSWER = "SELECT * FROM question WHERE answer IS NOT NULL";

    /**
     * Query to database for selecting questions without answer
     */
    private static final String SQL_FIND_WITHOUT_ANSWER = "SELECT * FROM question WHERE answer IS NULL";


    /**
     * Finds all questions which's answer field is not filled
     *
     * @return List of Question without answer
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
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

    /**
     * Finds all questions that have answer
     * @return List of Question with answer
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
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

    /**
     * Parses result set to retrieve entity object
     * @param resultSet is used for further transformation in entity
     * @return parsed entity
     * @throws SQLException if database exception occurred
     */
    @Override
    public Question parseResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_question");
        String login = resultSet.getString("login");
        String question = resultSet.getString("question");
        String answer = resultSet.getString("answer");
        return new Question(id, login, question, answer);
    }

    /**
     * Creates statement for further select by key
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_KEY);
        statement.setLong(1, key);
        return statement;
    }

    /**
     * Creates statement for further inserting to table
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for create statement
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, Question entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getQuestion());
        statement.setString(3, entity.getAnswer());
        return statement;
    }

    /**
     * Creates statement for further updating
     * @param connection is used for creating prepared statement
     * @param entity is database entity for retrieving data for update statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
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

    /**
     * Creates statement for further deleting from table
     * @param connection is used for creating prepared statement
     * @param key is used as primary key in table
     * @return Prepared statement for further parsing
     * @throws SQLException if database exception occurred
     */
    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, Long key) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQl_DELETE);
        statement.setLong(1, key);
        return statement;
    }
}
