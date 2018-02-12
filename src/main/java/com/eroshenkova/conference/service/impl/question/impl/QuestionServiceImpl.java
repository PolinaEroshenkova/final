package com.eroshenkova.conference.service.impl.question.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.question.QuestionDAO;
import com.eroshenkova.conference.database.dao.question.impl.QuestionDAOImpl;
import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.entry.impl.EntryServiceImpl;
import com.eroshenkova.conference.service.impl.question.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private static final Logger LOGGER = LogManager.getLogger(EntryServiceImpl.class);

    @Override
    public void register(Question entity) throws ServiceException, DAOException {
        if (entity == null) {
            throw new ServiceException();
        }
        DAO<Long, Question> dao = new QuestionDAOImpl();
        dao.create(entity, false);
    }

    @Override
    public void delete(Long key) throws ServiceException, DAOException {
        if (key == null) {
            throw new ServiceException();
        }
        DAO<Long, Question> dao = new QuestionDAOImpl();
        dao.delete(key);
    }

    @Override
    public List<Question> findWithoutAnswer() throws DAOException {
        QuestionDAO dao = new QuestionDAOImpl();
        return dao.findWithoutAnswer();
    }

    @Override
    public List<Question> findWithAnswer() throws DAOException {
        QuestionDAO dao = new QuestionDAOImpl();
        return dao.findWithAnswer();
    }
}
