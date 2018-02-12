package com.eroshenkova.conference.database.dao.entry;

import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

public interface EntryDAO {
    List<Entry> findByLogin(String login) throws DAOException;

    List<Entry> findByStatus() throws DAOException;
}
