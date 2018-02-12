package com.eroshenkova.conference.service;

import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.entry.EntryService;
import com.eroshenkova.conference.service.impl.entry.impl.EntryServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EntryServiceTest {
    private EntryService service = new EntryServiceImpl();

    @Test
    public void findByLogin() throws ServiceException, DAOException {
        String login = "popo83";
        int expected = 3;
        List<Entry> real = service.findByLogin(login);
        Assert.assertEquals(real.size(), expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByLoginException() throws ServiceException, DAOException {
        service.findByLogin(null);
    }

    @Test
    public void findByNonexistLogin() throws ServiceException, DAOException {
        String login = "testLogin";
        List<Entry> real = service.findByLogin(login);
        int expected = 0;
        Assert.assertTrue(real.isEmpty());
    }

    @Test
    public void findByStatus() throws ServiceException, DAOException {
        List<Entry> real = service.findByStatus();
        int expected = 4;
        Assert.assertEquals(real.size(), expected);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void register() throws ServiceException, DAOException {
        String login = "popo83";
        Entry entry = new Entry(login);
        service.register(entry);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void delete() throws ServiceException, DAOException {
        service.delete(null);
    }

    @Test
    public void registerSections() throws ServiceException, DAOException {
        String login = "meneger3";
        String[] sectionsIds = {"12", "2"};
        List<Entry> expected = service.findByStatus();
        service.register(login, sectionsIds);
        List<Entry> real = service.findByStatus();
        Assert.assertEquals(real.size(), expected.size() + 1);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void registerNullSections() throws ServiceException, DAOException {
        String[] sectionsIds = {"17", "24"};
        service.register(null, sectionsIds);
    }

    @Test
    public void changeStatus() throws ServiceException, DAOException {
        long idEntry = 9;
        String login = "qwerty4";
        String status = "Approved";
        Entry entry = new Entry(idEntry, login, status);
        service.changeStatus(entry);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void changeStatusNull() throws ServiceException, DAOException {
        service.changeStatus(null);
    }
}
