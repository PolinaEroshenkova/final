package com.eroshenkova.conference.logic.impl;

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

import java.util.ArrayList;
import java.util.List;

public class EntryLogic {

    public boolean delete(long id) {
        DAO<Long, Entry> entryDAO = new EntryDAOImpl();
        return entryDAO.delete(id);
    }

    public boolean register(String login, String[] sectionIds) {
        Entry entry = new Entry(login);
        DAO<Long, Entry> entryDAO = new EntryDAOImpl();
        long insertedEntryId = entryDAO.create(entry, true);
        boolean flag = true;
        if (insertedEntryId > 0) {
            DAO<Long, SectionEntry> sectionEntryDao = new SectionEntryDAOImpl();
            for (String id : sectionIds) {
                SectionEntry sectionEntry = new SectionEntry(insertedEntryId, Long.parseLong(id));
                if (sectionEntryDao.create(sectionEntry, false) != 0) {
                    flag = false;
                    break;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    public List<Entry> findByLogin(String login) {
        EntryDAO entryDao = new EntryDAOImpl();
        List<Entry> entries = entryDao.findByLogin(login);
        return fillEntriesWithConference(entries);
    }

    public List<Entry> findByStatus() {
        EntryDAO entryDao = new EntryDAOImpl();
        List<Entry> entries = entryDao.findByStatus();
        return fillEntriesWithConference(entries);
    }

    public boolean changeStatus(Entry entity) {
        DAO<Long, Entry> entryDAO = new EntryDAOImpl();
        return entryDAO.update(entity, entity.getIdentry());
    }

    private List<Entry> fillEntriesWithConference(List<Entry> entries) {
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
