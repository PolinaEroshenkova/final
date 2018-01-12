package command;

import db.dao.AbstractDAO;
import db.dao.participant.entity.Participant;
import db.dao.participant.impl.ParticipantDAO;
import db.dao.user.entity.User;
import db.dao.user.impl.UserDAO;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements ActionCommand {
    private final static String PARAM_NAME_SURNAME = "surname";
    private final static String PARAM_NAME_NAME = "name";
    private final static String PARAM_NAME_SCOPE = "scope";
    private final static String PARAM_NAME_EMAIL = "email";
    private final static String PARAM_NAME_REGISTRATION_LOGIN = "reglogin";
    private final static String PARAM_NAME_REGISTRATION_PASSWORD = "regpassword";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = (User) formUserObject(request);
        Participant participant = (Participant) formParticipantObject(request);
        AbstractDAO<String, User> userDAO = new UserDAO();
        AbstractDAO<String, Participant> participantDAO = new ParticipantDAO();
        if (userDAO.create(user) && participantDAO.create(participant)) {
            request.setAttribute("state", "success");
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }

    private User formUserObject(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_REGISTRATION_LOGIN);
        String password = request.getParameter(PARAM_NAME_REGISTRATION_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        return new User(login, password, email);
    }

    private Participant formParticipantObject(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_REGISTRATION_LOGIN);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String name = request.getParameter(PARAM_NAME_NAME);
        String scope = request.getParameter(PARAM_NAME_SCOPE);
        return new Participant(login, surname, name, scope);
    }
}
