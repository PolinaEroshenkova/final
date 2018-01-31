package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.db.DAO;
import com.eroshenkova.conference.db.dao.DAOCommandEnum;
import com.eroshenkova.conference.db.dao.entry.entity.Entry;
import com.eroshenkova.conference.db.dao.entry.impl.EntryDAO;
import com.eroshenkova.conference.db.dao.sectionentry.entity.SectionEntry;
import com.eroshenkova.conference.db.dao.sectionentry.impl.SectionEntryDAO;
import com.eroshenkova.conference.logic.Logic;

import java.util.List;

public class EntryLogic implements Logic<Long, Entry> {

    @Override
    public boolean create(Entry entry) {
        DAO<Long, Entry> dao = new EntryDAO();
        return dao.execute(DAOCommandEnum.CREATE, entry);
    }

    @Override
    public Entry findByKey(Long key) {
        DAO<Long, Entry> dao = new EntryDAO();
        return dao.findByKey(key);
    }

    public boolean delete(long id) {
        Entry entry = new Entry(id);
        DAO<Long, Entry> entryDAO = new EntryDAO();
        return entryDAO.execute(DAOCommandEnum.DELETE, entry);
    }

    public boolean register(String login, String[] sectionIds) {
        Entry entry = new Entry(login);
        DAO<Long, Entry> entryDAO = new EntryDAO();
        long insertedEntryId = entryDAO.insertWithGeneratedKeys(entry);
        boolean flag = true;
        if (insertedEntryId > 0) {
            DAO<List<Long>, SectionEntry> sectionEntryDao = new SectionEntryDAO();
            for (String id : sectionIds) {
                SectionEntry sectionEntry = new SectionEntry(insertedEntryId, Long.parseLong(id));
                if (!sectionEntryDao.execute(DAOCommandEnum.CREATE, sectionEntry)) {
                    flag = false;
                    break;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }
}
