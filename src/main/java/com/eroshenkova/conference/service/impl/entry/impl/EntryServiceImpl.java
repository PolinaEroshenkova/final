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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EntryServiceImpl implements EntryService {
    private static final Logger LOGGER = LogManager.getLogger(EntryServiceImpl.class);

    @Override
    public void register(Entry entity) throws ServiceException, DAOException {
        if (entity == null) {
            throw new ServiceException();
        }
        DAO<Long, Entry> dao = new EntryDAOImpl();
        dao.create(entity, false);
    }

    @Override
    public void delete(Long id) throws ServiceException, DAOException {
        if (id == null) {
            throw new ServiceException();
        }
        DAO<Long, Entry> entryDAO = new EntryDAOImpl();
        entryDAO.delete(id);
    }

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

    @Override
    public List<Entry> findByStatus() throws ServiceException, DAOException {
        EntryDAO entryDao = new EntryDAOImpl();
        List<Entry> entries = entryDao.findByStatus();
        entries = fillWithConference(entries);
        entries = fillWithUser(entries);
        return entries;
    }

    @Override
    public void changeStatus(Entry entry) throws ServiceException, DAOException {
        if (entry == null) {
            throw new ServiceException();
        }
        DAO<Long, Entry> entryDAO = new EntryDAOImpl();
        entryDAO.update(entry, entry.getIdentry());

    }

    private List<Entry> fillWithConference(List<Entry> entries) throws DAOException, ServiceException {
        if (entries == null) {
            throw new ServiceException();
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

    private List<Entry> fillWithUser(List<Entry> entries) throws DAOException, ServiceException {
        if (entries == null) {
            throw new ServiceException();
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
