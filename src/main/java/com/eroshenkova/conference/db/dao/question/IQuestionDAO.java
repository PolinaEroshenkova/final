package com.eroshenkova.conference.db.dao.question;

import com.eroshenkova.conference.db.dao.question.entity.Question;

import java.util.List;

public interface IQuestionDAO {

    List<Question> findAll();
}
