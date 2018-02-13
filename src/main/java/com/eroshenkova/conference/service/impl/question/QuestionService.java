package com.eroshenkova.conference.service.impl.question;

import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.service.Service;

import java.util.List;

/**
 * Defines personal question service methods
 *
 * @author Palina Yerashenkava
 * @see Service
 */
public interface QuestionService extends Service<Long, Question> {

    /**
     * Used to find answered questions
     * @return list of questions with answer
     * @throws DAOException thrown when database throw exception
     */
    List<Question> findWithAnswer() throws DAOException;

    /**
     * Used to find non-answered questions
     * @return list of questions with answer
     * @throws DAOException thrown when database throw exception
     */
    List<Question> findWithoutAnswer() throws DAOException;
}
