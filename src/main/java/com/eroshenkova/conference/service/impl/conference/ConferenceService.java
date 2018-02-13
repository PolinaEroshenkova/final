package com.eroshenkova.conference.service.impl.conference;

import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.Service;

import java.util.List;

/**
 * Defines personal conference service methods
 *
 * @author Palina Yerashenkava
 * @see Service
 */
public interface ConferenceService extends Service<Long, Conference> {

    /**
     * Used for finding conferences by today's day
     * @return list of conferences that was found by today's date
     * @throws DAOException when DAO layer exception occurred
     */
    List<Conference> findByDate() throws DAOException;

    /**
     * @param key is key of database table conference
     * @return conference entity if it was found in database or null if not
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    Conference findByKey(Long key) throws ServiceException, DAOException;

}
