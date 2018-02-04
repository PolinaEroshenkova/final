package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.conference.ConferenceDAO;
import com.eroshenkova.conference.database.dao.conference.impl.ConferenceDAOImpl;
import com.eroshenkova.conference.database.dao.section.SectionDAO;
import com.eroshenkova.conference.database.dao.section.impl.SectionDAOImpl;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.entity.Section;

import java.util.List;

public class ConferenceLogic {

    public long create(Conference conference) {
        DAO<Long, Conference> conferenceDao = new ConferenceDAOImpl();
        return conferenceDao.create(conference, false);
    }

    public Conference findByKey(Long key) {
        DAO<Long, Conference> conferenceDao = new ConferenceDAOImpl();
        Conference conference = conferenceDao.findByKey(key);
        if (conference != null) {
            SectionDAO sectionDao = new SectionDAOImpl();
            List<Section> sections = sectionDao.findByConferenceId(key);
            conference.setSections(sections);
        }
        return conference;
    }


    public List<Conference> findByDate() {
        ConferenceDAO conferenceDao = new ConferenceDAOImpl();
        return conferenceDao.findByDate();
    }

    public boolean deleteConference(Long idConference) {
        DAO<Long, Conference> dao = new ConferenceDAOImpl();
        return dao.delete(idConference);
    }

    public boolean register(Conference conference) {
        DAO<Long, Conference> conferenceDAO = new ConferenceDAOImpl();
        long result = conferenceDAO.create(conference, true);
        boolean flag = true;
        if (result > 0) {
            DAO<Long, Section> sectionDao = new SectionDAOImpl();
            for (Section section : conference.getSections()) {
                section.setIdConference(result);
                if (sectionDao.create(section, false) != 0) {
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
