package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.question.QuestionDAO;
import com.eroshenkova.conference.database.dao.question.impl.QuestionDAOImpl;
import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class QuestionDAOTest {

    @Test
    public void findWithAnswer() throws DAOException {
        QuestionDAO dao = new QuestionDAOImpl();
        List<Question> questions = dao.findWithAnswer();
        int expected = 3;
        Assert.assertEquals(questions.size(), expected);
    }

    @Test
    public void findWithoutAnswer() throws DAOException {
        QuestionDAO dao = new QuestionDAOImpl();
        List<Question> questions = dao.findWithoutAnswer();
        int expected = 0;
        Assert.assertEquals(questions.size(), expected);
    }

    @Test(expectedExceptions = DAOException.class)
    public void insertWithError() throws DAOException {
        DAO<Long, Question> dao = new QuestionDAOImpl();
        String login = null;
        String questionText = "Where I can find sig in button?";
        Question question = new Question(login, questionText);
        dao.create(question, false);
    }

    @Test
    public void insert() throws DAOException {
        DAO<Long, Question> dao = new QuestionDAOImpl();
        String login = "popo83";
        String questionText = "Where I can find sig in button?";
        Question question = new Question(login, questionText);
        long result = dao.create(question, false);
        long expected = 0;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void delete() throws DAOException {
        DAO<Long, Question> dao = new QuestionDAOImpl();
        long key = 3;
        dao.delete(key);
        Assert.assertNull(dao.findByKey(key));
    }

    @Test
    public void update() throws DAOException {
        DAO<Long, Question> dao = new QuestionDAOImpl();
        long id = 5;
        String login = "zver'";
        String questionText = "Where I can find sig in button?";
        String answer = "Please, check conferences table";
        Question question = new Question(id, login, questionText, answer);
        dao.update(question, null);
        Question find = dao.findByKey(question.getIdquestion());
        Assert.assertEquals(find, question);
    }

    @Test
    public void findByKey() throws DAOException {
        DAO<Long, Question> dao = new QuestionDAOImpl();
        long key = 2;
        Question find = dao.findByKey(key);
        Question entry = new Question(2, "meneger3", "Can I delete a conference application?", "You can do this in your account. Opposite to each application there is a button \"Cancel application\" ");
        Assert.assertEquals(find, entry);
    }
}
