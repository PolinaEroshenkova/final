package com.eroshenkova.conference.service.impl.question.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.question.QuestionDAO;
import com.eroshenkova.conference.database.dao.question.impl.QuestionDAOImpl;
import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.entry.impl.EntryServiceImpl;
import com.eroshenkova.conference.service.impl.question.QuestionService;
import com.eroshenkova.conference.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Defines methods individual for question
 *
 * @author Palina Yerashenkava
 * @see QuestionService
 */
public class QuestionServiceImpl implements QuestionService {
    private static final Logger LOGGER = LogManager.getLogger(EntryServiceImpl.class);

    /**
     * Used to register new T entity
     * @param entity is T entity
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void register(Question entity) throws ServiceException, DAOException {
        Validator validator = new Validator();
        if (entity == null || !validator.validate(entity)) {
            throw new ServiceException();
        }
        DAO<Long, Question> dao = new QuestionDAOImpl();
        dao.create(entity, false);
        LOGGER.log(Level.INFO, "Created new question");
    }

    /**
     * Used to delete Question entity by long key
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void delete(Long key) throws ServiceException, DAOException {
        if (key == null) {
            throw new ServiceException();
        }
        DAO<Long, Question> dao = new QuestionDAOImpl();
        dao.delete(key);
        LOGGER.log(Level.INFO, "Question with id=" + key + " was deleted successfully");
    }

    /**
     * Used to find non-answered questions
     * @return list of questions with answer
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public List<Question> findWithoutAnswer() throws DAOException {
        QuestionDAO dao = new QuestionDAOImpl();
        return dao.findWithoutAnswer();
    }

    /**
     * Used to find answered questions
     * @return list of questions with answer
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public List<Question> findWithAnswer() throws DAOException {
        QuestionDAO dao = new QuestionDAOImpl();
        return dao.findWithAnswer();
    }
}
