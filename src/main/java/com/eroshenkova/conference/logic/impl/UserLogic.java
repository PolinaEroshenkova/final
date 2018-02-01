package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.db.DAO;
import com.eroshenkova.conference.db.dao.DAOCommandEnum;
import com.eroshenkova.conference.db.dao.participant.entity.Participant;
import com.eroshenkova.conference.db.dao.participant.impl.ParticipantDAO;
import com.eroshenkova.conference.db.dao.user.IUserDAO;
import com.eroshenkova.conference.db.dao.user.entity.User;
import com.eroshenkova.conference.db.dao.user.impl.UserDAO;
import com.eroshenkova.conference.logic.Logic;

public class UserLogic implements Logic<String, User> {

    public boolean create(User user) {
        DAO<String, User> userDAO = new UserDAO();
        return userDAO.execute(DAOCommandEnum.CREATE, user);
    }

    public int register(User user, Participant participant) {
        if (isEmailExist(user.getEmail())) {
            return -1;
        }
        if (create(user)) {
            DAO<String, Participant> participantDAO = new ParticipantDAO();
            if (participantDAO.execute(DAOCommandEnum.CREATE, participant)) {
                return 1;
            }
        }
        return 0;
    }

    public User formProfile(String login) {
        DAO<String, User> userDAO = new UserDAO();
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
        DAO<String, User> dao = new UserDAO();
        return dao.findByKey(login);
    }

    private boolean isEmailExist(String email) {
        IUserDAO iUserDAO = new UserDAO();
        return iUserDAO.findByEmail(email) == null;
    }
}
