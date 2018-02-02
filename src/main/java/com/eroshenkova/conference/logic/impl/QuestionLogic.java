package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.db.DAO;
import com.eroshenkova.conference.db.dao.DAOCommandEnum;
import com.eroshenkova.conference.db.dao.question.IQuestionDAO;
import com.eroshenkova.conference.db.dao.question.entity.Question;
import com.eroshenkova.conference.db.dao.question.impl.QuestionDAO;
import com.eroshenkova.conference.logic.Logic;

import java.util.List;

public class QuestionLogic implements Logic<Long, Question> {

    @Override
    public boolean create(Question entity) {
        DAO<Long, Question> dao = new QuestionDAO();
        return dao.execute(DAOCommandEnum.CREATE, entity);
    }

    @Override
    public Question findByKey(Long key) {
        DAO<Long, Question> dao = new QuestionDAO();
        return dao.findByKey(key);
    }

    public List<Question> findAll() {
        IQuestionDAO dao = new QuestionDAO();
        return dao.findAll();
    }
}
