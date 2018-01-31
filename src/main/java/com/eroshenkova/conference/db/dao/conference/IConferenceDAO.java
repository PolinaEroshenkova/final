package com.eroshenkova.conference.db.dao.conference;

import com.eroshenkova.conference.db.dao.conference.entity.Conference;

import java.util.List;

public interface IConferenceDAO {
    List<Conference> findByDate();
}
