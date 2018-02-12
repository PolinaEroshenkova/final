package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.section.SectionDAO;
import com.eroshenkova.conference.database.dao.section.impl.SectionDAOImpl;
import com.eroshenkova.conference.entity.impl.Section;
import com.eroshenkova.conference.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SectionDAOTest {

    @Test
    public void findByConferenceId() throws DAOException {
        SectionDAO dao = new SectionDAOImpl();
        long id = 3;
        List<Section> sections = dao.findByConferenceId(id);
        int expected = 3;
        Assert.assertEquals(sections.size(), expected);
    }

    @Test(expectedExceptions = DAOException.class)
    public void insertWithError() throws DAOException {
        DAO<Long, Section> dao = new SectionDAOImpl();
        long id = 3;
        String title = null;
        Section section = new Section(id, title);
        dao.create(section, false);
    }

    @Test
    public void insert() throws DAOException {
        DAO<Long, Section> dao = new SectionDAOImpl();
        long id = 3;
        String title = "Information technologies and control";
        Section section = new Section(id, title);
        long result = dao.create(section, false);
        long expected = 0;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void delete() throws DAOException {
        DAO<Long, Section> dao = new SectionDAOImpl();
        long key = 15;
        dao.delete(key);
        Assert.assertNull(dao.findByKey(key));
    }

    @Test
    public void update() throws DAOException {
        DAO<Long, Section> dao = new SectionDAOImpl();
        long id = 5;
        long idConference = 10;
        String title = "E-business";
        Section section = new Section(id, idConference, title);
        dao.update(section, null);
        Section find = dao.findByKey(section.getIdsection());
        Assert.assertEquals(find, section);
    }

    @Test
    public void findByKey() throws DAOException {
        DAO<Long, Section> dao = new SectionDAOImpl();
        long key = 2;
        long idConference = 3;
        String title = "Industrial robotics and mechatronics";
        Section find = dao.findByKey(key);
        Section entry = new Section(key, idConference, title);
        Assert.assertEquals(find, entry);
    }
}
