package com.eroshenkova.conference.database.dao.question;

import com.eroshenkova.conference.entity.Question;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

public interface QuestionDAO {

    List<Question> findAll() throws DAOException;

    List<Question> findWithoutAnswer() throws DAOException;

    List<Question> findWithAnswer() throws DAOException;
}
