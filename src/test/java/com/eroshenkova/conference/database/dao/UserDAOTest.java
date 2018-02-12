package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.user.UserDAO;
import com.eroshenkova.conference.database.dao.user.impl.UserDAOImpl;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDAOTest {

    @Test
    public void findByEmail() throws DAOException {
        UserDAO dao = new UserDAOImpl();
        String email = "dianarock@yandex.ru";
        String login = "rock4";
        String password = "ZxcasdqwE";
        String type = "user";
        User user = new User(login, password, email, type);
        User find = dao.findByEmail(email);
        Assert.assertEquals(find, user);
    }

    @Test(expectedExceptions = DAOException.class)
    public void insertWithError() throws DAOException {
        DAO<String, User> dao = new UserDAOImpl();
        String email = "dianarock@yandex.ru";
        String login = null;
        String password = "ZxcasdqwE";
        User user = new User(login, password, email);
        dao.create(user, false);
    }

    @Test
    public void insert() throws DAOException {
        DAO<String, User> dao = new UserDAOImpl();
        String email = "didi@yandex.ru";
        String login = "didi4";
        String password = "qwerty";
        User user = new User(login, password, email);
        long result = dao.create(user, false);
        long expected = 0;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void delete() throws DAOException {
        DAO<String, User> dao = new UserDAOImpl();
        String login = "qwerty4";
        dao.delete(login);
        Assert.assertNull(dao.findByKey(login));
    }

    @Test
    public void update() throws DAOException {
        DAO<String, User> dao = new UserDAOImpl();
        String email = "dianarock@yandex.ru";
        String login = "rock4";
        String password = "zxcvbjujytr";
        String type = "user";
        User user = new User(login, password, email, type);
        dao.update(user, null);
        User find = dao.findByKey(user.getLogin());
        Assert.assertEquals(find, user);
    }

    @Test
    public void findByKey() throws DAOException {
        DAO<String, User> dao = new UserDAOImpl();
        String email = "evgenbatikov@gmail.com";
        String login = "science_man";
        String password = "ZhEnIa1";
        String type = "user";
        User find = dao.findByKey(login);
        User entry = new User(login, password, email, type);
        Assert.assertEquals(find, entry);
    }
}
