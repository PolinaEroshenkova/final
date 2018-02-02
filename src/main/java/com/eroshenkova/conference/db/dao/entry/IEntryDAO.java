package com.eroshenkova.conference.db.dao.entry;

import com.eroshenkova.conference.db.dao.entry.entity.Entry;

import java.util.List;

public interface IEntryDAO {
    List<Entry> findByLogin(String login);

    List<Entry> findByStatus();

    boolean changeStatus(long id, String status);
}
