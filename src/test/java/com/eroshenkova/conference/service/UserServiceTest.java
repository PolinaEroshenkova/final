package com.eroshenkova.conference.service;

import com.eroshenkova.conference.entity.impl.Participant;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.user.UserService;
import com.eroshenkova.conference.service.impl.user.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserServiceTest {
    private UserService service = new UserServiceImpl();

    @Test
    public void findByKey() throws ServiceException, DAOException {
        String email = "allagrish55@yandex.ru";
        String key = "qwerty4";
        User user = service.findByKey(key);
        Assert.assertEquals(user.getEmail(), email);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByNullKey() throws ServiceException, DAOException {
        service.findByKey(null);
    }

    @Test
    public void findByNonExistingKey() throws ServiceException, DAOException {
        String key = "non existing";
        Assert.assertNull(service.findByKey(key));
    }

    @Test
    public void register() throws ServiceException, DAOException {
        String login = "new";
        String password = "123";
        String email = "email@mail.ru";
        User user = new User(login, password, email);
        String surname = "test";
        String name = "test";
        String scope = "test";
        Participant participant = new Participant(login, surname, name, scope);
        service.register(user, participant);
        User real = service.findByKey(login);
        Assert.assertEquals(real.getEmail(), email);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void registerNull() throws ServiceException, DAOException {
        service.register(null, null);
    }

    @Test
    public void delete() throws ServiceException, DAOException {
        String login = "test";
        service.delete(login);
        Assert.assertNull(service.findByKey(login));
    }

    @Test(expectedExceptions = ServiceException.class)
    public void deleteNull() throws ServiceException, DAOException {
        service.delete(null);
    }

    @Test
    public void formProfile() throws ServiceException, DAOException {
        String login = "popo83";
        User real = service.formProfile(login);
        User expected = service.findByKey(login);
        Assert.assertEquals(real.getEmail(), expected.getEmail());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void formProfileNull() throws ServiceException, DAOException {
        service.formProfile(null);
    }

    @Test
    public void updateProfile() throws ServiceException, DAOException {
        String realLogin = "science_man";
        String login = "new";
        String password = "123";
        String email = "email@mail.ru";
        User user = new User(login, password, email);
        String surname = "test";
        String name = "test";
        String scope = "test scope";
        Participant participant = new Participant(login, surname, name, scope);
        user.setParticipant(participant);
        User updatedUser = service.updateProfile(user, realLogin);
        Assert.assertEquals(updatedUser, user);
    }

    @Test
    public void updateProfileWrongLogin() throws ServiceException, DAOException {
        String realLogin = "science_man";
        String login = "popo83";
        String password = "123";
        String email = "email@mail.ru";
        User user = new User(login, password, email);
        String surname = "test";
        String name = "test";
        String scope = "test scope";
        Participant participant = new Participant(login, surname, name, scope);
        user.setParticipant(participant);
        User systemUser = service.updateProfile(user, realLogin);
        Assert.assertEquals(systemUser.getLogin(), user.getLogin());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void updateProfileNullUser() throws ServiceException, DAOException {
        String login = "popo83";
        service.updateProfile(null, login);
    }

    @Test
    public void updatePassword() throws ServiceException, DAOException {
        String login = "zver\'";
        String password = "test password";
        service.updatePassword(password, login);
        User user = service.findByKey(login);
        Assert.assertEquals(user.getPassword(), password);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void updatePasswordNull() throws ServiceException, DAOException {
        String password = "test password";
        service.updatePassword(password, null);
    }
}
