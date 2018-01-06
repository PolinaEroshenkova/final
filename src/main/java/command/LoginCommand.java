package command;

import db.AbstractDAO;
import db.ConnectionPool;
import db.UserDAO;
import entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";


    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            AbstractDAO<String, User> dao = new UserDAO(connection);
            User user = dao.findEntityByKey(login);
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user.getLogin());
                page = ConfigurationManager.getProperty("path.page.index");
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //LOGGER
        }
        return page;
    }
}
