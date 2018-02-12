package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.sectionentry.SectionEntryDAO;
import com.eroshenkova.conference.database.dao.sectionentry.impl.SectionEntryDAOImpl;
import com.eroshenkova.conference.entity.impl.SectionEntry;
import com.eroshenkova.conference.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SectionentryDAOTest {

    @Test
    public void findByEntryId() throws DAOException {
        SectionEntryDAO dao = new SectionEntryDAOImpl();
        long id = 4;
        List<SectionEntry> sectionEntries = dao.findByEntryId(id);
        int expected = 2;
        Assert.assertEquals(sectionEntries.size(), expected);
    }

    @Test
    public void insert() throws DAOException {
        DAO<Long, SectionEntry> dao = new SectionEntryDAOImpl();
        long idEntry = 9;
        long idSection = 8;
        SectionEntry section = new SectionEntry(idEntry, idSection);
        long result = dao.create(section, false);
        long expected = 0;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void delete() throws DAOException {
        DAO<Long, SectionEntry> dao = new SectionEntryDAOImpl();
        long key = 10;
        dao.delete(key);
        SectionEntryDAO sectionEntryDAO = new SectionEntryDAOImpl();
        List<SectionEntry> sectionEntries = sectionEntryDAO.findByEntryId(key);
        Assert.assertTrue(sectionEntries.isEmpty());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void update() throws DAOException {
        DAO<Long, SectionEntry> dao = new SectionEntryDAOImpl();
        long idEntry = 8;
        long idSection = 28;
        SectionEntry section = new SectionEntry(idEntry, idSection);
        dao.update(section, idEntry);

    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void findByKey() throws DAOException {
        DAO<Long, SectionEntry> dao = new SectionEntryDAOImpl();
        long key = 11;
        dao.findByKey(key);
    }
}
