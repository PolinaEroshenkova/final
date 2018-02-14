package com.eroshenkova.conference.service;

import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.entity.impl.Section;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.conference.ConferenceService;
import com.eroshenkova.conference.service.impl.conference.impl.ConferenceServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConferenceServiceTest {
    private ConferenceService service = new ConferenceServiceImpl();

    @Test
    public void findByKey() throws ServiceException, DAOException {
        long id = 2;
        Conference conference = service.findByKey(id);
        String topic = "Annual Autumn Conference of Otorhinolaryngologists of the Republic of Belarus";
        Assert.assertEquals(conference.getTopic(), topic);
    }

    @Test
    public void findByKeyNull() throws ServiceException, DAOException {
        long id = 222;
        Conference conference = service.findByKey(id);
        Assert.assertNull(conference);
    }

    @Test
    public void findByNullKey() throws ServiceException, DAOException {
        long id = 222;
        Assert.assertNull(service.findByKey(id));
    }

    @Test
    public void findByDate() throws ServiceException, DAOException {
        List<Conference> conferences = service.findByDate();
        int expected = 6;
        Assert.assertEquals(conferences.size(), expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void deleteWithException() throws ServiceException, DAOException {
        service.delete(null);
    }

    @Test
    public void delete() throws ServiceException, DAOException {
        long id = 5;
        service.delete(id);
        Assert.assertNull(service.findByKey(id));
    }

    @Test
    public void register() throws ServiceException, DAOException {
        List<Conference> expectedConferences = service.findByDate();
        String topic = "Test topic";
        int number = 40;
        String place = "Test place";
        Date dateStart = new Date();
        Date dateEnd = new Date();
        Date deadline = new Date();
        List<Section> sections = new ArrayList<>();
        String title1 = "Test first title";
        String title2 = "Test second title";
        Section section1 = new Section(title1);
        Section section2 = new Section(title2);
        sections.add(section1);
        sections.add(section2);
        Conference conference = new Conference(topic, number, place, dateStart, dateEnd, deadline, sections);
        service.register(conference);
        List<Conference> realConferences = service.findByDate();
        Assert.assertEquals(realConferences.size(), expectedConferences.size() + 1);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void registerNullConference() throws ServiceException, DAOException {
        service.register(null);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void registerNullSections() throws ServiceException, DAOException {
        String topic = "Test topic";
        int number = 40;
        String place = "Test place";
        Date dateStart = new Date();
        Date dateEnd = new Date();
        Date deadline = new Date();
        Conference conference = new Conference(topic, number, place, dateStart, dateEnd, deadline);
        service.register(conference);
    }
}
