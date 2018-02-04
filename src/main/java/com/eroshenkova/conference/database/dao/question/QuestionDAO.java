package com.eroshenkova.conference.database.dao.question;

import com.eroshenkova.conference.entity.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> findAll();

    List<Question> findWithoutAnswer();

    List<Question> findWithAnswer();
}
