package com.eroshenkova.conference.service.impl.entry;

import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.Service;

import java.util.List;

/**
 * Defines personal entry service methods
 *
 * @author Palina Yerashenkava
 * @see Service
 */
public interface EntryService extends Service<Long, Entry> {

    /**
     * Used to find entries for defines user
     * @param login is user's login
     * @return list of entries for particular user
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    List<Entry> findByLogin(String login) throws ServiceException, DAOException;

    /**
     * Used to find entries by waiting status
     * @return list of entries with waiting status
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    List<Entry> findByStatus() throws ServiceException, DAOException;

    /**
     * Used to change status of entry
     * @param entry is entry entity where status should be changed
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    void changeStatus(Entry entry) throws ServiceException, DAOException;

    /**
     * Used to register new entry
     * @param login is user's login
     * @param sectionIds is section ids than defines in entry
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    void register(String login, String[] sectionIds) throws ServiceException, DAOException;
}
