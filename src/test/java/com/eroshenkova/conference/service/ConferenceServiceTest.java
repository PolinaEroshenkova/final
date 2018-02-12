package com.eroshenkova.conference.service;

import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.conference.ConferenceService;
import com.eroshenkova.conference.service.impl.conference.impl.ConferenceServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

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

    @Test(expectedExceptions = ServiceException.class)
    public void findByNullKey() throws ServiceException, DAOException {
        long id = 222;
        service.findByKey(id);
    }

    @Test
    public void findByDate() throws ServiceException, DAOException {
        List<Conference> conferences = service.findByDate();
        int expected = 6;
        Assert.assertEquals(conferences.size(), expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void delete() throws ServiceException, DAOException {
        service.delete(null);
    }
}
