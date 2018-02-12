package com.eroshenkova.conference.service.impl.user;

import com.eroshenkova.conference.entity.impl.Participant;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.Service;

public interface UserService extends Service<String, User> {

    long register(User user, Participant participant) throws ServiceException, DAOException;

    User formProfile(String login) throws ServiceException, DAOException;

    User logIn(User user) throws ServiceException, DAOException;

    User findByKey(String login) throws ServiceException, DAOException;

    User updateProfile(User user, String login) throws ServiceException, DAOException;

    void updatePassword(String password, String login) throws DAOException, ServiceException;
}
