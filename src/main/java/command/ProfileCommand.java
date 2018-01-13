package command;

import db.dao.AbstractDAO;
import db.dao.participant.entity.Participant;
import db.dao.participant.impl.ParticipantDAO;
import db.dao.user.entity.User;
import db.dao.user.impl.UserDAO;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        page = ConfigurationManager.getProperty(NEXT_PAGE);
        return page;
    }
}
