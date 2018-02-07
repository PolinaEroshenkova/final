package com.eroshenkova.conference.service.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.conference.impl.ConferenceDAOImpl;
import com.eroshenkova.conference.database.dao.entry.EntryDAO;
import com.eroshenkova.conference.database.dao.entry.impl.EntryDAOImpl;
import com.eroshenkova.conference.database.dao.section.impl.SectionDAOImpl;
import com.eroshenkova.conference.database.dao.sectionentry.SectionEntryDAO;
import com.eroshenkova.conference.database.dao.sectionentry.impl.SectionEntryDAOImpl;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.entity.Entry;
import com.eroshenkova.conference.entity.Section;
import com.eroshenkova.conference.entity.SectionEntry;
import com.eroshenkova.conference.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EntryService {
    private static final Logger LOGGER = LogManager.getLogger(EntryService.class);

    public boolean delete(long id) {
        boolean flag = false;
        try {
            DAO<Long, Entry> entryDAO = new EntryDAOImpl();
            entryDAO.delete(id);
            flag = true;
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return flag;
    }

    public boolean register(String login, String[] sectionIds) {
        boolean flag = false;
        try {
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
            flag = true;
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return flag;
    }

    public List<Entry> findByLogin(String login) {
        List<Entry> entries = null;
        try {
            EntryDAO entryDao = new EntryDAOImpl();
            entries = entryDao.findByLogin(login);
            entries = fillEntriesWithConference(entries);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return entries;
    }

    public List<Entry> findByStatus() {
        List<Entry> entries = null;
        try {
            EntryDAO entryDao = new EntryDAOImpl();
            entries = entryDao.findByStatus();
            entries = fillEntriesWithConference(entries);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return entries;
    }

    public boolean changeStatus(Entry entity) {
        boolean flag = false;
        try {
            DAO<Long, Entry> entryDAO = new EntryDAOImpl();
            entryDAO.update(entity, entity.getIdentry());
            flag = true;
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Error executing query");
        }
        return flag;
    }

    private List<Entry> fillEntriesWithConference(List<Entry> entries) throws DAOException {
        if (entries != null) {
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
        }
        return entries;
    }
}
