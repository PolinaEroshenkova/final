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

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute("user");
        AbstractDAO<String, User> userDAO = new UserDAO();
        User user = userDAO.findByKey(login);
        AbstractDAO<String, Participant> participantDAO = new ParticipantDAO();
        Participant participant = participantDAO.findByKey(login);
        request.setAttribute("user", user);
        request.setAttribute("participant", participant);
        page = ConfigurationManager.getProperty("path.page.profile");
        return page;
    }
}
