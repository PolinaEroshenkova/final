package com.eroshenkova.conference.service;

import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.question.QuestionService;
import com.eroshenkova.conference.service.impl.question.impl.QuestionServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class QuestionServiceTest {
    private QuestionService service = new QuestionServiceImpl();

    @Test
    public void findWithAnswer() throws DAOException {
        List<Question> real = service.findWithAnswer();
        int expected = 3;
        Assert.assertEquals(real.size(), expected);
    }

    @Test
    public void findWithoutAnswer() throws DAOException {
        List<Question> real = service.findWithoutAnswer();
        int expected = 1;
        Assert.assertEquals(real.size(), expected);
    }

    @Test
    public void register() throws ServiceException, DAOException {
        List<Question> expected = service.findWithAnswer();
        String login = "popo83";
        String questionText = "Test question";
        String answer = "Test answer";
        Question question = new Question(login, questionText, answer);
        service.register(question);
        List<Question> real = service.findWithAnswer();
        Assert.assertEquals(real.size(), expected.size() + 1);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void registerException() throws ServiceException, DAOException {
        service.register(null);
    }

    @Test
    public void delete() throws ServiceException, DAOException {
        long key = 1;
        List<Question> expected = service.findWithAnswer();
        service.delete(key);
        List<Question> real = service.findWithAnswer();
        Assert.assertEquals(real.size(), expected.size() - 1);

    }

    @Test(expectedExceptions = ServiceException.class)
    public void deleteException() throws ServiceException, DAOException {
        service.delete(null);
    }

}
