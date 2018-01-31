package com.eroshenkova.conference.logic.impl;

import com.eroshenkova.conference.db.DAO;
import com.eroshenkova.conference.db.dao.DAOCommandEnum;
import com.eroshenkova.conference.db.dao.conference.IConferenceDAO;
import com.eroshenkova.conference.db.dao.conference.entity.Conference;
import com.eroshenkova.conference.db.dao.conference.impl.ConferenceDAO;
import com.eroshenkova.conference.db.dao.section.entity.Section;
import com.eroshenkova.conference.db.dao.section.impl.SectionDAO;
import com.eroshenkova.conference.logic.Logic;

import java.util.List;

public class ConferenceLogic implements Logic<Long, Conference> {

    @Override
    public boolean create(Conference conference) {
        DAO<Long, Conference> conferenceDao = new ConferenceDAO();
        return conferenceDao.execute(DAOCommandEnum.CREATE, conference);
    }

    @Override
    public Conference findByKey(Long key) {
        DAO<Long, Conference> conferenceDao = new ConferenceDAO();
        return conferenceDao.findByKey(key);
    }

    public List<Conference> findByDate() {
        IConferenceDAO conferenceDao = new ConferenceDAO();
        return conferenceDao.findByDate();
    }

    public boolean deleteConference(Long idConference) {
        Conference conference = new Conference(idConference);
        DAO<Long, Conference> dao = new ConferenceDAO();
        return dao.execute(DAOCommandEnum.DELETE, conference);
    }

    public boolean register(Conference conference) {
        DAO<Long, Conference> conferenceDAO = new ConferenceDAO();
        long result = conferenceDAO.insertWithGeneratedKeys(conference);
        boolean flag = true;
        if (result > 0) {
            DAO<Long, Section> sectionDao = new SectionDAO();
            for (Section section : conference.getSections()) {
                section.setIdConference(result);
                if (!sectionDao.execute(DAOCommandEnum.CREATE, section)) {
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
