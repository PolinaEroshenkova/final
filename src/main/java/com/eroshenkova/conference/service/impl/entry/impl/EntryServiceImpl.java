package com.eroshenkova.conference.service.impl.entry.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.conference.impl.ConferenceDAOImpl;
import com.eroshenkova.conference.database.dao.entry.EntryDAO;
import com.eroshenkova.conference.database.dao.entry.impl.EntryDAOImpl;
import com.eroshenkova.conference.database.dao.participant.ParticipantDAO;
import com.eroshenkova.conference.database.dao.section.impl.SectionDAOImpl;
import com.eroshenkova.conference.database.dao.sectionentry.SectionEntryDAO;
import com.eroshenkova.conference.database.dao.sectionentry.impl.SectionEntryDAOImpl;
import com.eroshenkova.conference.database.dao.user.impl.UserDAOImpl;
import com.eroshenkova.conference.entity.impl.*;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.impl.entry.EntryService;
import com.eroshenkova.conference.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines methods individual for entry
 *
 * @author Palina Yerashenkava
 * @see EntryService
 */
public class EntryServiceImpl implements EntryService {
    private static final Logger LOGGER = LogManager.getLogger(EntryServiceImpl.class);

    /**
     * Used to delete entry entity by long key
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void delete(Long id) throws ServiceException, DAOException {
        if (id == null) {
            throw new ServiceException();
        }
        DAO<Long, Entry> entryDAO = new EntryDAOImpl();
        entryDAO.delete(id);
    }

    /**
     * Used to register new entry
     * @param login is user's login
     * @param sectionIds is section ids than defines in entry
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void register(String login, String[] sectionIds) throws ServiceException, DAOException {
        if (login == null || sectionIds == null || sectionIds.length == 0) {
            throw new ServiceException();
        }
        Entry entry = new Entry(login);
        DAO<Long, Entry> entryDAO = new EntryDAOImpl();
        long insertedEntryId = entryDAO.create(entry, true);
        if (insertedEntryId > 0) {
            DAO<Long, SectionEntry> sectionEntryDao = new SectionEntryDAOImpl();
            for (String id : sectionIds) {
                SectionEntry sectionEntry = new SectionEntry(insertedEntryId, Long.parseLong(id));
                sectionEntryDao.create(sectionEntry, false);
            }
        }
    }

    /**
     * Used to find entries for defines user
     * @param login is user's login
     * @return list of entries for particular user
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public List<Entry> findByLogin(String login) throws ServiceException, DAOException {
        if (login == null) {
            throw new ServiceException();
        }
        EntryDAO entryDao = new EntryDAOImpl();
        List<Entry> entries = entryDao.findByLogin(login);
        entries = fillWithConference(entries);
        return entries;
    }

    /**
     * Used to find entries by waiting status
     * @return list of entries with waiting status
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public List<Entry> findByStatus() throws ServiceException, DAOException {
        EntryDAO entryDao = new EntryDAOImpl();
        List<Entry> entries = entryDao.findByStatus();
        entries = fillWithConference(entries);
        entries = fillWithUser(entries);
        return entries;
    }

    /**
     * Used to change status of entry
     * @param entry is entry entity where status should be changed
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void changeStatus(Entry entry) throws ServiceException, DAOException {
        Validator validator = new Validator();
        if (entry == null || validator.validate(entry)) {
            throw new ServiceException();
        }
        DAO<Long, Entry> entryDAO = new EntryDAOImpl();
        entryDAO.update(entry, entry.getIdentry());
    }

    /**
     * Used to register new T entity.
     * @param entity is T entity
     * @throws UnsupportedOperationException because entry cannot be applied
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    @Override
    public void register(Entry entity) throws ServiceException, DAOException {
        throw new UnsupportedOperationException();
    }

    /**
     * Used to fill list of entries with defined conference
     * @param entries is list of entries which has empty field conference
     * @return list of entries with filled conferences
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    private List<Entry> fillWithConference(List<Entry> entries) throws DAOException, ServiceException {
        if (entries == null) {
            return null;
        }
        DAO<Long, Section> sectionDAO = new SectionDAOImpl();
        DAO<Long, Conference> conferenceDao = new ConferenceDAOImpl();
        SectionEntryDAO sectionEntryDAO = new SectionEntryDAOImpl();
        for (Entry entry : entries) {
            long entryId = entry.getIdentry();
            List<SectionEntry> sectionEntries = sectionEntryDAO.findByEntryId(entryId);
            List<Section> sections = new ArrayList<>();
            for (SectionEntry current : sectionEntries) {
                long sectionId = current.getIdsection();
                Section section = sectionDAO.findByKey(sectionId);
                sections.add(section);
            }
            long idconference = sections.get(0).getIdConference();
            Conference conference = conferenceDao.findByKey(idconference);
            conference.setSections(sections);
            entry.setConference(conference);
        }
        return entries;
    }

    /**
     * Used to fill list of entries with defined user
     * @param entries is list of entries which has empty field user
     * @return list of entries with filled user field
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    private List<Entry> fillWithUser(List<Entry> entries) throws DAOException, ServiceException {
        if (entries == null) {
            return null;
        }
        DAO<String, User> userDAO = new UserDAOImpl();
        ParticipantDAO participantDAO = new ParticipantDAO();
        for (Entry entry : entries) {
            String login = entry.getLogin();
            User user = userDAO.findByKey(login);
            Participant participant = participantDAO.findByKey(login);
            user.setParticipant(participant);
            entry.setUser(user);
        }
        return entries;
    }
}
