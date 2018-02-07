package com.eroshenkova.conference.service.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.question.QuestionDAO;
import com.eroshenkova.conference.database.dao.question.impl.QuestionDAOImpl;
import com.eroshenkova.conference.entity.Question;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class QuestionService {
    private static final Logger LOGGER = LogManager.getLogger(EntryService.class);

    public long create(Question entity) {
        long result = -1;
        try {
            DAO<Long, Question> dao = new QuestionDAOImpl();
            result = dao.create(entity, false);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return result;
    }

    public boolean deleteByKey(Long key) {
        boolean flag = false;
        try {
            DAO<Long, Question> dao = new QuestionDAOImpl();
            dao.delete(key);
            flag = true;
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return flag;
    }

    public List<Question> findWithoutAnswer() {
        List<Question> questions = null;
        try {
            QuestionDAO dao = new QuestionDAOImpl();
            questions = dao.findWithoutAnswer();
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return questions;
    }

    public List<Question> findWithAnswer() {
        List<Question> questions = null;
        try {
            QuestionDAO dao = new QuestionDAOImpl();
            questions = dao.findWithAnswer();
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return questions;
    }
}
