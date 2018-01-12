package command;

import db.dao.conference.IConferenceDAO;
import db.dao.conference.entity.Conference;
import db.dao.conference.impl.ConferenceDAO;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConferenceCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        IConferenceDAO dao = new ConferenceDAO();
        List<Conference> conferences = dao.findByDate();
        request.setAttribute("conferences", conferences);
        page = ConfigurationManager.getProperty("path.page.showconferences");
        return page;
    }
}
