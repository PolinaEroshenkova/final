package com.eroshenkova.conference.service.impl.entry;

import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.Service;

import java.util.List;

public interface EntryService extends Service<Long, Entry> {

    List<Entry> findByLogin(String login) throws ServiceException, DAOException;

    List<Entry> findByStatus() throws ServiceException, DAOException;

    void changeStatus(Entry entry) throws ServiceException, DAOException;

    void register(String login, String[] sectionIds) throws ServiceException, DAOException;
}
