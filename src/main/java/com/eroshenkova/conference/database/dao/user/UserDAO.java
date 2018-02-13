package com.eroshenkova.conference.database.dao.user;

import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;

/**
 * Defines specific options for user table
 */
public interface UserDAO {
    /**
     * Selects user by email address
     *
     * @param email user's personal email address
     * @return found user entity
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    User findByEmail(String email) throws DAOException;

    /**
     * Updates password of certain user
     * @param password new user's password value
     * @param login user's personal identificator
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    void updatePassword(String password, String login) throws DAOException;
}
