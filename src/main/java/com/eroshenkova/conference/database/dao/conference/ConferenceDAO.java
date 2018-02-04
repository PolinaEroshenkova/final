package com.eroshenkova.conference.database.dao.conference;

import com.eroshenkova.conference.entity.Conference;

import java.util.List;

public interface ConferenceDAO {
    List<Conference> findByDate();
}
