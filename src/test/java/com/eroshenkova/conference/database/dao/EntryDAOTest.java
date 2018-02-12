package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.entry.EntryDAO;
import com.eroshenkova.conference.database.dao.entry.impl.EntryDAOImpl;
import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

public class EntryDAOTest {

    @Test
    public void findByLogin() throws DAOException {
        EntryDAO dao = new EntryDAOImpl();
        List<Entry> entries = dao.findByLogin("meneger3");
        int expected = 2;
        Assert.assertEquals(entries.size(), expected);
    }

    @Test
    public void findByStatus() throws DAOException {
        EntryDAO dao = new EntryDAOImpl();
        List<Entry> entries = dao.findByStatus();
        int expected = 4;
        Assert.assertEquals(entries.size(), expected);
    }

    @Test(expectedExceptions = DAOException.class)
    public void insertWithError() throws DAOException {
        DAO<Long, Entry> dao = new EntryDAOImpl();
        long id = 5;
        String login = null;
        String status = "Waiting";
        Entry entry = new Entry(id, login, status);
        dao.create(entry, false);
    }

    @Test
    public void insert() throws DAOException {
        DAO<Long, Entry> dao = new EntryDAOImpl();
        long id = 5;
        String login = "science_man";
        String status = "Waiting";
        Entry entry = new Entry(id, login, status);
        long result = dao.create(entry, false);
        long expected = 0;
        Assert.assertEquals(result, expected);
    }

    @Test(expectedExceptions = DAOException.class)
    public void delete() throws DAOException {
        DAO<Long, Entry> dao = new EntryDAOImpl();
        long key = 3;
        dao.delete(key);
        dao.findByKey(key);
    }

    @Test
    public void update() throws DAOException, ParseException {
        DAO<Long, Entry> dao = new EntryDAOImpl();
        long id = 5;
        String login = "qwerty4";
        String status = "Waiting";
        Entry entry = new Entry(id, login, status);
        dao.update(entry, null);
        Entry find = dao.findByKey(entry.getIdentry());
        Assert.assertEquals(find, entry);
    }

    @Test
    public void findByKey() throws DAOException {
        DAO<Long, Entry> dao = new EntryDAOImpl();
        long key = 1;
        Entry find = dao.findByKey(key);
        Entry entry = new Entry(1, "popo83", "Approved");
        Assert.assertEquals(find, entry);
    }
}
