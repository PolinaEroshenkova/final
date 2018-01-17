package db.dao.conference;

import db.dao.conference.entity.Conference;

import java.util.List;

public interface IConferenceDAO {
    List<Conference> findByDate();

    List<Conference> findByLogin();
}
