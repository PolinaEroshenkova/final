package com.eroshenkova.conference.service.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.conference.ConferenceDAO;
import com.eroshenkova.conference.database.dao.conference.impl.ConferenceDAOImpl;
import com.eroshenkova.conference.database.dao.section.SectionDAO;
import com.eroshenkova.conference.database.dao.section.impl.SectionDAOImpl;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.entity.Section;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class ConferenceService {
    private static final Logger LOGGER = LogManager.getLogger(ConferenceService.class);

    public Conference findByKey(Long key) {
        DAO<Long, Conference> conferenceDao = new ConferenceDAOImpl();
        Conference conference = null;
        try {
            conference = conferenceDao.findByKey(key);
            SectionDAO sectionDao = new SectionDAOImpl();
            List<Section> sections = sectionDao.findByConferenceId(key);
            conference.setSections(sections);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return conference;
    }


    public List<Conference> findByDate() {
        List<Conference> conferences = null;
        try {
            ConferenceDAO conferenceDao = new ConferenceDAOImpl();
            conferences = conferenceDao.findByDate();
            Collections.sort(conferences);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return conferences;
    }

    public boolean deleteConference(Long idConference) {
        boolean flag = false;
        try {
            DAO<Long, Conference> dao = new ConferenceDAOImpl();
            dao.delete(idConference);
            flag = true;
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return flag;
    }

    public boolean register(Conference conference) {
        DAO<Long, Conference> conferenceDAO = new ConferenceDAOImpl();
        boolean flag = false;
        try {
            long result = conferenceDAO.create(conference, true);
            if (result > 0) {
                DAO<Long, Section> sectionDao = new SectionDAOImpl();
                for (Section section : conference.getSections()) {
                    section.setIdConference(result);
                    sectionDao.create(section, false);
                }
            }
            flag = true;
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return flag;
    }
}
