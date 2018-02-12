package com.eroshenkova.conference.service.impl.question;

import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.service.Service;

import java.util.List;

public interface QuestionService extends Service<Long, Question> {

    List<Question> findWithAnswer() throws DAOException;

    List<Question> findWithoutAnswer() throws DAOException;
}
