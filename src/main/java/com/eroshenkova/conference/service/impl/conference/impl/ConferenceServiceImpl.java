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
import com.eroshenkova.conference.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * Defines methods individual for conference
 *
 * @author Palina Yerashenkava
 * @see ConferenceService
 */
public class ConferenceServiceImpl implements ConferenceService {
    private static final Logger LOGGER = LogManager.getLogger(ConferenceServiceImpl.class);

    /**
     * @param key is key of database table conference
     * @return conference entity if it was found in database or null if not
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public Conference findByKey(Long key) throws ServiceException, DAOException {
        if (key == null) {
            throw new ServiceException();
        }
        DAO<Long, Conference> conferenceDao = new ConferenceDAOImpl();
        Conference conference = conferenceDao.findByKey(key);
        if (conference == null) {
            return null;
        }
        SectionDAO sectionDao = new SectionDAOImpl();
        List<Section> sections = sectionDao.findByConferenceId(key);
        conference.setSections(sections);
        LOGGER.log(Level.INFO, "Conference was found by id=" + conference.getIdconference());
        return conference;
    }

    /**
     * Used for finding conferences by today's day
     * @return list of conferences that was found by today's date
     * @throws DAOException when DAO layer exception occurred
     */
    @Override
    public List<Conference> findByDate() throws DAOException {
        ConferenceDAO conferenceDao = new ConferenceDAOImpl();
        List<Conference> conferences = conferenceDao.findByDate();
        if (conferences != null && !conferences.isEmpty()) {
            Collections.sort(conferences);
            for (Conference conference : conferences) {
                long idConference = conference.getIdconference();
                SectionDAO sectionDAO = new SectionDAOImpl();
                List<Section> sections = sectionDAO.findByConferenceId(idConference);
                conference.setSections(sections);
            }
            LOGGER.log(Level.INFO, conferences.size() + " conferences were found by today's date");
        }
        return conferences;
    }


    /**
     * Used to delete conference entity by K key
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void delete(Long idConference) throws ServiceException, DAOException {
        if (idConference == null) {
            throw new ServiceException();
        }
        DAO<Long, Conference> dao = new ConferenceDAOImpl();
        dao.delete(idConference);
        LOGGER.log(Level.INFO, "Conference was deleted successfully");
    }

    /**
     * Used to register new conference entity
     * @param conference is conference entity
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void register(Conference conference) throws ServiceException, DAOException {
        Validator validator = new Validator();
        if (conference == null || conference.getSections() == null ||
                !validator.validate(conference)) {
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
        LOGGER.log(Level.INFO, "Conference was registered successfully");
    }
}
