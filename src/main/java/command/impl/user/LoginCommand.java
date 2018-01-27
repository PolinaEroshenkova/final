package command.impl.user;

import command.ActionCommand;
import db.DAO;
import db.dao.user.entity.User;
import db.dao.user.impl.UserDAO;
import resource.JspRoutesManager;
import resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String SESSION_ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_ERROR_LOGIN = "loginError";
    private static final String SESSION_ATTRIBUTE_TYPE = "type";
    private static final String NEXT_PAGE = "url.page.profile";
    private static final String NEXT_ERROR_PAGE = "path.page.index";


    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        DAO<String, User> dao = new UserDAO();
        User user = dao.findByKey(login);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_ATTRIBUTE_USER, user.getLogin());
            session.setAttribute(SESSION_ATTRIBUTE_TYPE, user.getType());
            page = UrlManager.getProperty(NEXT_PAGE);
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_LOGIN, "Неверный ввод логина или пароля");
            page = JspRoutesManager.getProperty(NEXT_ERROR_PAGE);
        }
        return page;
    }
}
