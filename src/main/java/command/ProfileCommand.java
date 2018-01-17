package command;

import db.dao.AbstractDAO;
import db.dao.conference.entity.Conference;
import db.dao.conference.impl.ConferenceDAO;
import db.dao.entry.IEntryDAO;
import db.dao.entry.entity.Entry;
import db.dao.entry.impl.EntryDAO;
import db.dao.participant.entity.Participant;
import db.dao.participant.impl.ParticipantDAO;
import db.dao.section.ISectionDAO;
import db.dao.section.entity.Section;
import db.dao.section.impl.SectionDAO;
import db.dao.user.entity.User;
import db.dao.user.impl.UserDAO;
import resource.ConfigurationManager;

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
        AbstractDAO<String, User> userDAO = new UserDAO();
        User user = userDAO.findByKey(login);
        AbstractDAO<String, Participant> participantDAO = new ParticipantDAO();
        Participant participant = participantDAO.findByKey(login);
        request.setAttribute(SESSION_ATTRIBUTE_USER, user);
        request.setAttribute(ATTRIBUTE_PARTICIPANT, participant);
        IEntryDAO entryDao = new EntryDAO();
        List<Entry> entries = entryDao.findByLogin(login);
        ISectionDAO sectionDao = new SectionDAO();
        AbstractDAO<Long, Conference> conferenceDao = new ConferenceDAO();
        for (Entry entry : entries) {
            long id = entry.getIdentry();
            List<Section> sections = sectionDao.findByEntryId(id);
            long idconference = sections.get(0).getIdConference();
            Conference conference = conferenceDao.findByKey(idconference);
            conference.setSections(sections);
            entry.setConference(conference);
        }
        request.setAttribute("entries", entries);
        page = ConfigurationManager.getProperty(NEXT_PAGE);
        return page;
    }
}
