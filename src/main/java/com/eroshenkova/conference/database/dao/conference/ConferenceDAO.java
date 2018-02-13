package com.eroshenkova.conference.database.dao.conference;

import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

/**
 * Defines conference entity oriented operations
 */
public interface ConferenceDAO {

    /**
     * @return List of Conferences which has deadline date more than now
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    List<Conference> findByDate() throws DAOException;
}
