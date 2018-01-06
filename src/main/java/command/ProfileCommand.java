package command;

import db.AbstractDAO;
import db.ConnectionPool;
import db.ParticipantDAO;
import db.UserDAO;
import entity.Participant;
import entity.User;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class ProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute("user");
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            AbstractDAO<String, User> userDAO = new UserDAO(connection);
            AbstractDAO<String, Participant> participantDAO = new ParticipantDAO(connection);
            User user = userDAO.findEntityByKey(login);
            Participant participant = participantDAO.findEntityByKey(login);
            request.setAttribute("user", user);
            request.setAttribute("participant", participant);
            page = ConfigurationManager.getProperty("path.page.profile");
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //LOGGER
        }
        return page;
    }
}
