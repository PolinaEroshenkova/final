package command;

import db.ConnectionPool;
import db.dao.ConferenceDAO;
import entity.Conference;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ConferenceCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            ConferenceDAO dao = new ConferenceDAO(connection);
            List<Conference> conferences = dao.findByDate();
            request.setAttribute("conferences", conferences);
            page = ConfigurationManager.getProperty("path.page.conference");
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //LOGGER
        }
        return page;
    }
}
