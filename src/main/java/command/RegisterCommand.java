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
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SCOPE = "scope";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_REGISTRATION_LOGIN = "reglogin";
    private static final String PARAM_NAME_REGISTRATION_PASSWORD = "regpassword";
    private static final String NEXT_PAGE = "path.page.login";
    private static final String ERROR_LOGIN = "errorLoginPassMessage";
    private static final String MESSAGE_ERROR_LOGIN = "message.loginerror";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = (User) formUserObject(request);
        Participant participant = (Participant) formParticipantObject(request);
        AbstractDAO<String, User> userDAO = new UserDAO();
        AbstractDAO<String, Participant> participantDAO = new ParticipantDAO();
        if (userDAO.create(user) && participantDAO.create(participant)) {
            page = ConfigurationManager.getProperty(NEXT_PAGE);
        } else {
            request.setAttribute(ERROR_LOGIN, MessageManager.getProperty(MESSAGE_ERROR_LOGIN));
            page = ConfigurationManager.getProperty(NEXT_PAGE);
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
