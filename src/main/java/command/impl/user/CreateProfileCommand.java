package command.impl.user;

import command.ActionCommand;
import db.DAO;
import db.dao.DAOCommandEnum;
import db.dao.participant.entity.Participant;
import db.dao.participant.impl.ParticipantDAO;
import db.dao.user.IUserDAO;
import db.dao.user.entity.User;
import db.dao.user.impl.UserDAO;
import resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class CreateProfileCommand implements ActionCommand {
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SCOPE = "scope";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_REGISTRATION_LOGIN = "reglogin";
    private static final String PARAM_NAME_REGISTRATION_PASSWORD = "regpassword";
    private static final String NEXT_PAGE = "path.page.registration";
    private static final String ERROR_LOGIN = "loginError";
    private static final String ERROR_EMAIL = "emailError";
    private static final String ATTRIBUTE_STATE = "state";
    private static final String MESSAGE = "message";
    private static final String SUCCESS_MESSAGE = "Вы успешно зарегистрировались. Войдите, чтобы продолжить";
    private static final String HREF = "href";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = formUserObject(request);
        Participant participant = formParticipantObject(request);
        DAO<String, User> userDAO = new UserDAO();
        DAO<String, Participant> participantDAO = new ParticipantDAO();
        IUserDAO iUserDAO = new UserDAO();
        if (iUserDAO.findByEmail(user.getEmail()) != null) {
            request.setAttribute(ERROR_EMAIL, "e-mail зарегистрирован в системе");
        } else if (userDAO.execute(DAOCommandEnum.CREATE, user) != 0 && participantDAO.execute(DAOCommandEnum.CREATE, participant) != 0) {
            request.setAttribute(ERROR_LOGIN, "Логин занят");
        } else {
            request.setAttribute(ATTRIBUTE_STATE, "success");
            request.setAttribute(MESSAGE, SUCCESS_MESSAGE);
            request.setAttribute(HREF, "/");
        }
        page = JspRoutesManager.getProperty(NEXT_PAGE);
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
