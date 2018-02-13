package com.eroshenkova.conference.database.dao.question;

import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

/**
 * Defines specific options for question table
 * @author Palina Yerashenkava
 */
public interface QuestionDAO {

    /**
     * Finds all questions which's answer field is not filled
     *
     * @return List of Question without answer
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    List<Question> findWithoutAnswer() throws DAOException;

    /**
     * Finds all questions that have answer
     * @return List of Question with answer
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    List<Question> findWithAnswer() throws DAOException;
}
