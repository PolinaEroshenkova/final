package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.db.DAO;
import com.eroshenkova.conference.db.dao.AbstractDAO;
import com.eroshenkova.conference.db.dao.conference.entity.Conference;
import com.eroshenkova.conference.db.dao.conference.impl.ConferenceDAO;
import com.eroshenkova.conference.db.dao.entry.IEntryDAO;
import com.eroshenkova.conference.db.dao.entry.entity.Entry;
import com.eroshenkova.conference.db.dao.entry.impl.EntryDAO;
import com.eroshenkova.conference.db.dao.participant.entity.Participant;
import com.eroshenkova.conference.db.dao.participant.impl.ParticipantDAO;
import com.eroshenkova.conference.db.dao.section.ISectionDAO;
import com.eroshenkova.conference.db.dao.section.entity.Section;
import com.eroshenkova.conference.db.dao.section.impl.SectionDAO;
import com.eroshenkova.conference.db.dao.user.entity.User;
import com.eroshenkova.conference.db.dao.user.impl.UserDAO;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileCommand implements ActionCommand {
    private static final String SESSION_ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_PARTICIPANT = "participant";
    private static final String NEXT_PAGE = "path.page.profile";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(SESSION_ATTRIBUTE_USER);
        DAO<String, User> userDAO = new UserDAO();
        User user = userDAO.findByKey(login);
        DAO<String, Participant> participantDAO = new ParticipantDAO();
        Participant participant = participantDAO.findByKey(login);
        request.setAttribute(SESSION_ATTRIBUTE_USER, user);
        request.setAttribute(ATTRIBUTE_PARTICIPANT, participant);
        IEntryDAO entryDao = new EntryDAO();
        List<Entry> entries = entryDao.findByLogin(login);
        ISectionDAO sectionDao = new SectionDAO();
        AbstractDAO<Long, Conference> conferenceDao = new ConferenceDAO();
        for (Entry entry : entries) {  //TODO cортировка по дате
            long id = entry.getIdentry();
            List<Section> sections = sectionDao.findByEntryId(id);
            long idconference = sections.get(0).getIdConference();
            Conference conference = conferenceDao.findByKey(idconference);
            conference.setSections(sections);
            entry.setConference(conference);
        }
        request.setAttribute("entries", entries);
        page = JspRoutesManager.getProperty(NEXT_PAGE);
        return page;
    }
}
