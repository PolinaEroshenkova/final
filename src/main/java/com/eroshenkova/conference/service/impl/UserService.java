package com.eroshenkova.conference.service.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.participant.ParticipantDAO;
import com.eroshenkova.conference.database.dao.user.UserDAO;
import com.eroshenkova.conference.database.dao.user.impl.UserDAOImpl;
import com.eroshenkova.conference.entity.Participant;
import com.eroshenkova.conference.entity.User;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public long create(User user) {
        long result = -1;
        try {
            DAO<String, User> userDAO = new UserDAOImpl();
            result = userDAO.create(user, false);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return result;
    }

    public long register(User user, Participant participant) {
        long result = -1;
        try {
            if (isEmailExist(user.getEmail())) {
                return result;
            }
            if (create(user) == 0) {
                DAO<String, Participant> participantDAO = new ParticipantDAO();
                result = participantDAO.create(participant, false);
            }
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return result;
    }

    public User formProfile(String login) {
        User user = null;
        try {
            DAO<String, User> userDAO = new UserDAOImpl();
            user = userDAO.findByKey(login);
            DAO<String, Participant> participantDAO = new ParticipantDAO();
            Participant participant = participantDAO.findByKey(login);
            user.setParticipant(participant);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return user;
    }

    public User logIn(User user) {
        User dbUser = findByKey(user.getLogin());
        if (dbUser != null && user.getPassword().equals(dbUser.getPassword())) {
            return dbUser;
        }
        return null;
    }

    public User findByKey(String login) {
        User user = null;
        try {
            DAO<String, User> dao = new UserDAOImpl();
            user = dao.findByKey(login);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return user;
    }

    private boolean isEmailExist(String email) throws DAOException {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.findByEmail(email) != null;

    }
}
