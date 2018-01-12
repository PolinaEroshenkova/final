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


    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        AbstractDAO<String, User> dao = new UserDAO();
        User user = dao.findByKey(login);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user.getLogin());
            String uri = request.getRequestURI().substring(1).toLowerCase();
            String address = "path.page." + uri;
            page = ConfigurationManager.getProperty(address);
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
