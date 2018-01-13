package command;

import db.dao.AbstractDAO;
import db.dao.user.entity.User;
import db.dao.user.impl.UserDAO;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String SESSION_ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_ERROR_LOGIN = "errorLoginPassMessage";
    private static final String MESSAGE_LOGIN_ERROR = "message.loginerror";
    private static final String NEXT_PAGE = "path.page.login";


    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        AbstractDAO<String, User> dao = new UserDAO();
        User user = dao.findByKey(login);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_ATTRIBUTE_USER, user.getLogin());
            ActionCommand command = new ProfileCommand(); //TODO forward on the same page
            page = command.execute(request);
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_LOGIN, MessageManager.getProperty(MESSAGE_LOGIN_ERROR));
            page = ConfigurationManager.getProperty(NEXT_PAGE);
        }
        return page;
    }
}
