package com.eroshenkova.conference.service.impl.user.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.participant.ParticipantDAO;
import com.eroshenkova.conference.database.dao.user.UserDAO;
import com.eroshenkova.conference.database.dao.user.impl.UserDAOImpl;
import com.eroshenkova.conference.entity.impl.Participant;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.user.UserService;
import com.eroshenkova.conference.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 * Defines methods individual for user
 *
 * @author Palina Yerashenkava
 * @see UserService
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    /**
     * Register new user in database
     * @param user is system user
     * @param participant is user
     * @return -1 if email address of user is already exists
     *          >1 if registration completed successfully
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public long register(User user, Participant participant) throws ServiceException, DAOException {
        Validator validator = new Validator();
        if (user == null || participant == null || !validator.validate(user) ||
                !validator.validate(participant)) {
            throw new ServiceException();
        }
        long result = -1;
        if (isEmailExist(user.getEmail())) {
            return result;
        }
        DAO<String, User> userDAO = new UserDAOImpl();
        userDAO.create(user, false);
        DAO<String, Participant> participantDAO = new ParticipantDAO();
        result = participantDAO.create(participant, false);
        LOGGER.log(Level.INFO, "User " + user.getLogin() + " was registered successfully");
        return result;
    }

    /**
     * Used to register new user entity
     * @param user is user entity
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void register(User user) throws ServiceException, DAOException {
        Validator validator = new Validator();
        if (user == null || !validator.validate(user)) {
            throw new ServiceException();
        }
        DAO<String, User> userDAO = new UserDAOImpl();
        userDAO.create(user, false);
        LOGGER.log(Level.INFO, "User " + user.getLogin() + " was registered successfully");
    }

    /**
     * Used to delete T entity by K key
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void delete(String key) throws ServiceException, DAOException {
        if (key == null) {
            throw new ServiceException();
        }
        DAO<String, User> dao = new UserDAOImpl();
        dao.delete(key);
        LOGGER.log(Level.INFO, "User " + key + " was deleted successfully");
    }

    /**
     * Get data from database needed to form profile page
     * @param login is user's login
     * @return User object with profile data
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public User formProfile(String login) throws ServiceException, DAOException {
        if (login == null) {
            throw new ServiceException();
        }
        DAO<String, User> userDAO = new UserDAOImpl();
        User user = userDAO.findByKey(login);
        DAO<String, Participant> participantDAO = new ParticipantDAO();
        Participant participant = participantDAO.findByKey(login);
        user.setParticipant(participant);
        LOGGER.log(Level.INFO, "Data for user " + login + " was received");
        return user;
    }

    /**
     * Log in user in system
     * @param user is user object
     * @return logged in user
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public User logIn(User user) throws ServiceException, DAOException {
        if (user == null) {
            throw new ServiceException();
        }
        User dbUser = findByKey(user.getLogin());
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
        if (dbUser != null && encryptor.checkPassword(user.getPassword(), dbUser.getPassword())) {
            return dbUser;
        }
        return null;
    }

    /**
     * Finds user by login
     * @param login is user's login
     * @return founded user
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public User findByKey(String login) throws ServiceException, DAOException {
        if (login == null) {
            throw new ServiceException();
        }
        DAO<String, User> dao = new UserDAOImpl();
        return dao.findByKey(login);
    }

    /**
     * Update user data in database
     * @param user is user data needed to be updated
     * @param login is user's login
     * @return updated user
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public User updateProfile(User user, String login) throws ServiceException, DAOException {
        Validator validator = new Validator();
        if (user == null || login == null || !validator.validate(user)) {
            throw new ServiceException();
        }
        DAO<String, User> userDAO = new UserDAOImpl();
        DAO<String, Participant> participantDAO = new ParticipantDAO();
        UserDAO dao = new UserDAOImpl();
        User systemUser = userDAO.findByKey(login);
        Participant systemParticipant = participantDAO.findByKey(login);
        systemUser.setParticipant(systemParticipant);
        User loginUser = userDAO.findByKey(user.getLogin());
        if (loginUser == null || loginUser.getLogin().equals(login)) {
            User emailUser = dao.findByEmail(user.getEmail());
            if (emailUser == null || emailUser.getEmail().equals(systemUser.getEmail())) {
                userDAO.update(user, null);
                participantDAO.update(user.getParticipant(), null);
                return user;
            }
        }
        LOGGER.log(Level.INFO, "Profile was updated successfully");
        return systemUser;
    }

    /**
     * Updates user's password
     * @param password is user's new password
     * @param login is user's login
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void updatePassword(String password, String login) throws DAOException, ServiceException {
        if (password == null || login == null) {
            throw new ServiceException();
        }
        UserDAO dao = new UserDAOImpl();
        dao.updatePassword(password, login);
        LOGGER.log(Level.INFO, "User's password was updated successfully");
    }

    private boolean isEmailExist(String email) throws DAOException {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.findByEmail(email) != null;

    }
}
