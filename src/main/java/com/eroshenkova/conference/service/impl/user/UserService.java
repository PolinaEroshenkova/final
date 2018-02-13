package com.eroshenkova.conference.service.impl.user;

import com.eroshenkova.conference.entity.impl.Participant;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.Service;

/**
 * Defines personal user service methods
 *
 * @author Palina Yerashenkava
 * @see Service
 */
public interface UserService extends Service<String, User> {

    /**
     * Register new user in database
     * @param user is system user
     * @param participant is user
     * @return -1 if email address of user is already exists
     *          >1 if registration completed successfully
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    long register(User user, Participant participant) throws ServiceException, DAOException;

    /**
     * Get data from database needed to form profile page
     * @param login is user's login
     * @return User object with profile data
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    User formProfile(String login) throws ServiceException, DAOException;

    /**
     * Log in user in system
     * @param user is user object
     * @return logged in user
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    User logIn(User user) throws ServiceException, DAOException;

    /**
     * Finds user by login
     * @param login is user's login
     * @return founded user
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    User findByKey(String login) throws ServiceException, DAOException;

    /**
     * Update user data in database
     * @param user is user data needed to be updated
     * @param login is user's login
     * @return updated user
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    User updateProfile(User user, String login) throws ServiceException, DAOException;


    /**
     * Updates user's password
     * @param password is user's new password
     * @param login is user's login
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    void updatePassword(String password, String login) throws DAOException, ServiceException;
}
