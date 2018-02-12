package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.conference.ConferenceDAO;
import com.eroshenkova.conference.database.dao.conference.impl.ConferenceDAOImpl;
import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.locale.DateWorker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ConferenceDAOTest {
    private Conference conference;

    @BeforeClass
    public void init() {
        long id = 8;
        String topic = "Information technologies";
        int numberParticipants = 20;
        String place = "Business-center \"Solo\"";
        Date start = new Date();
        Date end = new Date();
        Date deadline = new Date();
        conference = new Conference(id, topic, numberParticipants, place, start, end, deadline);
    }

    @Test
    public void findByDate() throws DAOException {
        ConferenceDAO dao = new ConferenceDAOImpl();
        List<Conference> conferences = dao.findByDate();
        int expected = 6;
        Assert.assertEquals(conferences.size(), expected);
    }


    @Test(expectedExceptions = DAOException.class)
    public void insertWithError() throws DAOException {
        DAO<Long, Conference> dao = new ConferenceDAOImpl();
        conference.setTopic(null);
        dao.create(conference, false);
    }

    @Test
    public void insert() throws DAOException {
        DAO<Long, Conference> dao = new ConferenceDAOImpl();
        long result = dao.create(conference, false);
        long expected = 0;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void delete() throws DAOException {
        DAO<Long, Conference> dao = new ConferenceDAOImpl();
        long key = 5;
        dao.delete(key);
        Assert.assertNull(dao.findByKey(key));
    }

    @Test
    public void findByKey() throws DAOException, ParseException {
        long id = 8;
        String topic = "Modern management: problems, studies, prospects";
        int numberParticipants = 250;
        String place = "Business-center \"Titan\"";
        String start = "2018-02-02 00:00";
        String end = "2018-02-05 00:00";
        String deadline = "2018-01-29";
        Date dateStart = new Timestamp(DateWorker.parseDateTimeToSQL(start).getTime());
        Date dateEnd = new Timestamp(DateWorker.parseDateTimeToSQL(end).getTime());
        Date dateDeadline = new Timestamp(DateWorker.parseDateFromSQL(deadline).getTime());
        conference = new Conference(id, topic, numberParticipants, place, dateStart, dateEnd, dateDeadline);
        DAO<Long, Conference> dao = new ConferenceDAOImpl();
        Conference find = dao.findByKey(conference.getIdconference());
        Assert.assertEquals(find, conference);
    }
}
