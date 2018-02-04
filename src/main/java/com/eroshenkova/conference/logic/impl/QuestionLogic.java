package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.question.QuestionDAO;
import com.eroshenkova.conference.database.dao.question.impl.QuestionDAOImpl;
import com.eroshenkova.conference.entity.Question;

import java.util.List;

public class QuestionLogic {

    public long create(Question entity) {
        DAO<Long, Question> dao = new QuestionDAOImpl();
        return dao.create(entity, false);
    }

    public Question findByKey(Long key) {
        DAO<Long, Question> dao = new QuestionDAOImpl();
        return dao.findByKey(key);
    }

    public boolean deleteByKey(Long key) {
        DAO<Long, Question> dao = new QuestionDAOImpl();
        return dao.delete(key);
    }

    public List<Question> findWithoutAnswer() {
        QuestionDAO dao = new QuestionDAOImpl();
        return dao.findWithoutAnswer();
    }

    public List<Question> findWithAnswer() {
        QuestionDAO dao = new QuestionDAOImpl();
        return dao.findWithAnswer();
    }

    public List<Question> findAll() {
        QuestionDAO dao = new QuestionDAOImpl();
        return dao.findAll();
    }
}
