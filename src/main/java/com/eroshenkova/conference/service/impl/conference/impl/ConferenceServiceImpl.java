package com.eroshenkova.conference.service.impl.conference.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.conference.ConferenceDAO;
import com.eroshenkova.conference.database.dao.conference.impl.ConferenceDAOImpl;
import com.eroshenkova.conference.database.dao.section.SectionDAO;
import com.eroshenkova.conference.database.dao.section.impl.SectionDAOImpl;
import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.entity.impl.Section;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.conference.ConferenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class ConferenceServiceImpl implements ConferenceService {
    private static final Logger LOGGER = LogManager.getLogger(ConferenceServiceImpl.class);

    @Override
    public Conference findByKey(Long key) throws ServiceException, DAOException {
        if (key == null) {
            throw new ServiceException();
        }
        DAO<Long, Conference> conferenceDao = new ConferenceDAOImpl();
        Conference conference = conferenceDao.findByKey(key);
        SectionDAO sectionDao = new SectionDAOImpl();
        List<Section> sections = sectionDao.findByConferenceId(key);
        conference.setSections(sections);
        return conference;
    }

    @Override
    public List<Conference> findByDate() throws DAOException {
        ConferenceDAO conferenceDao = new ConferenceDAOImpl();
        List<Conference> conferences = conferenceDao.findByDate();
        Collections.sort(conferences);
        for (Conference conference : conferences) {
            long idConference = conference.getIdconference();
            SectionDAO sectionDAO = new SectionDAOImpl();
            List<Section> sections = sectionDAO.findByConferenceId(idConference);
            conference.setSections(sections);
        }
        return conferences;
    }


    @Override
    public void delete(Long idConference) throws ServiceException, DAOException {
        if (idConference == null) {
            throw new ServiceException();
        }
        DAO<Long, Conference> dao = new ConferenceDAOImpl();
        dao.delete(idConference);
    }

    @Override
    public void register(Conference conference) throws ServiceException, DAOException {
        if (conference == null) {
            throw new ServiceException();
        }
        DAO<Long, Conference> conferenceDAO = new ConferenceDAOImpl();
        long result = conferenceDAO.create(conference, true);
        if (result > 0) {
            DAO<Long, Section> sectionDao = new SectionDAOImpl();
            for (Section section : conference.getSections()) {
                section.setIdConference(result);
                sectionDao.create(section, false);
            }
        }
    }
}
