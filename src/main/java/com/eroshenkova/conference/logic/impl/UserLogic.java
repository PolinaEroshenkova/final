package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.participant.ParticipantDAO;
import com.eroshenkova.conference.database.dao.user.UserDAO;
import com.eroshenkova.conference.database.dao.user.impl.UserDAOImpl;
import com.eroshenkova.conference.entity.Participant;
import com.eroshenkova.conference.entity.User;

public class UserLogic {

    public long create(User user) {
        DAO<String, User> userDAO = new UserDAOImpl();
        return userDAO.create(user, false);
    }

    public int register(User user, Participant participant) {
        if (isEmailExist(user.getEmail())) {
            return -1;
        }
        if (create(user) == 0) {
            DAO<String, Participant> participantDAO = new ParticipantDAO();
            if (participantDAO.create(participant, false) == 0) {
                return 1;
            }
        }
        return 0;
    }

    public User formProfile(String login) {
        DAO<String, User> userDAO = new UserDAOImpl();
        User user = userDAO.findByKey(login);
        DAO<String, Participant> participantDAO = new ParticipantDAO();
        Participant participant = participantDAO.findByKey(login);
        user.setParticipant(participant);
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
        DAO<String, User> dao = new UserDAOImpl();
        return dao.findByKey(login);
    }

    private boolean isEmailExist(String email) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.findByEmail(email) == null;
    }
}
