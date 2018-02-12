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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.BasicPasswordEncryptor;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public long register(User user, Participant participant) throws ServiceException, DAOException {
        if (user == null || participant == null) {
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
        return result;
    }

    @Override
    public void register(User user) throws ServiceException, DAOException {
        if (user == null) {
            throw new ServiceException();
        }
        DAO<String, User> userDAO = new UserDAOImpl();
        userDAO.create(user, false);
    }

    @Override
    public void delete(String key) throws ServiceException, DAOException {
        if (key == null) {
            throw new ServiceException();
        }
        DAO<String, User> dao = new UserDAOImpl();
        dao.delete(key);
    }

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
        return user;
    }

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

    @Override
    public User findByKey(String login) throws ServiceException, DAOException {
        if (login == null) {
            throw new ServiceException();
        }
        DAO<String, User> dao = new UserDAOImpl();
        return dao.findByKey(login);
    }

    @Override
    public void updateProfile(User user, String login) throws ServiceException, DAOException {
        if (user == null || login == null) {
            throw new ServiceException();
        }
        DAO<String, User> userDAO = new UserDAOImpl();
        DAO<String, Participant> participantDAO = new ParticipantDAO();
        if (user.getLogin().equals(login)) {
            userDAO.update(user, null);
            participantDAO.update(user.getParticipant(), null);
        } else {
            userDAO.update(user, login);
            participantDAO.update(user.getParticipant(), login);
        }
    }

    private boolean isEmailExist(String email) throws DAOException {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.findByEmail(email) != null;

    }
}
