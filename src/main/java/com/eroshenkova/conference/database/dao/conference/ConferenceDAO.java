package com.eroshenkova.conference.database.dao.conference;

import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

public interface ConferenceDAO {
    List<Conference> findByDate() throws DAOException;
}
