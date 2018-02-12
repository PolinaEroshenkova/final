package com.eroshenkova.conference.service.impl.conference;

import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.service.Service;

import java.util.List;

public interface ConferenceService extends Service<Long, Conference> {

    List<Conference> findByDate() throws DAOException;

    Conference findByKey(Long key) throws ServiceException, DAOException;

}
