package com.eroshenkova.conference.database.dao.entry;

import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

/**
 * Defines specific options for entry table
 */
public interface EntryDAO {
    /**
     * Selects from entry database entries by user's login
     *
     * @param login user's login
     * @return List of entries for certain user
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    List<Entry> findByLogin(String login) throws DAOException;

    /**
     * Selects from entry database entries by waiting status
     * @return List of entries with waiting status
     * @throws DAOException if database exception occurred
     */
    List<Entry> findByStatus() throws DAOException;
}
