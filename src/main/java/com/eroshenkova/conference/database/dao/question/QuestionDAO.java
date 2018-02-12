package com.eroshenkova.conference.database.dao.question;

import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

public interface QuestionDAO {

    List<Question> findWithoutAnswer() throws DAOException;

    List<Question> findWithAnswer() throws DAOException;
}
