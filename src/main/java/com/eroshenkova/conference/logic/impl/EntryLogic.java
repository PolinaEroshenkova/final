package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.conference.impl.ConferenceDAOImpl;
import com.eroshenkova.conference.database.dao.entry.EntryDAO;
import com.eroshenkova.conference.database.dao.entry.impl.EntryDAOImpl;
import com.eroshenkova.conference.database.dao.section.SectionDAO;
import com.eroshenkova.conference.database.dao.section.impl.SectionDAOImpl;
import com.eroshenkova.conference.database.dao.sectionentry.SectionEntryDAO;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.entity.Entry;
import com.eroshenkova.conference.entity.Section;
import com.eroshenkova.conference.entity.SectionEntry;

import java.util.List;

public class EntryLogic {

    public long create(Entry entry) {
        DAO<Long, Entry> dao = new EntryDAOImpl();
        return dao.create(entry, false);
    }

    public Entry findByKey(Long key) {
        DAO<Long, Entry> dao = new EntryDAOImpl();
        return dao.findByKey(key);
    }

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
            DAO<List<Long>, SectionEntry> sectionEntryDao = new SectionEntryDAO();
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
            SectionDAO sectionDao = new SectionDAOImpl();
            DAO<Long, Conference> conferenceDao = new ConferenceDAOImpl();
            for (Entry entry : entries) {  //TODO cортировка по дате
                long id = entry.getIdentry();
                List<Section> sections = sectionDao.findByEntryId(id);
                long idconference = sections.get(0).getIdConference();
                Conference conference = conferenceDao.findByKey(idconference);
                conference.setSections(sections);
                entry.setConference(conference);
            }
        }
        return entries;
    }
}
