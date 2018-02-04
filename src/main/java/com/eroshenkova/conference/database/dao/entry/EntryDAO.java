package com.eroshenkova.conference.database.dao.entry;

import com.eroshenkova.conference.entity.Entry;

import java.util.List;

public interface EntryDAO {
    List<Entry> findByLogin(String login);

    List<Entry> findByStatus();
}
