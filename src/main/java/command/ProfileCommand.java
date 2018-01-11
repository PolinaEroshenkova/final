package command;

import db.AbstractDAO;
import db.dao.EntryDAO;
import db.dao.ParticipantDAO;
import db.dao.UserDAO;
import entity.Participant;
import entity.User;
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
        EntryDAO entryDAO = new EntryDAO();
        //List<Entry> entries=entryDAO.findByLogin(login);

        request.setAttribute("user", user);
        request.setAttribute("participant", participant);
        page = ConfigurationManager.getProperty("path.page.profile");
        return page;
    }
}
