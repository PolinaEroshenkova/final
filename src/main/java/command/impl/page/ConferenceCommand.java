package command.impl.page;

import command.ActionCommand;
import db.dao.conference.IConferenceDAO;
import db.dao.conference.entity.Conference;
import db.dao.conference.impl.ConferenceDAO;
import db.dao.section.ISectionDAO;
import db.dao.section.entity.Section;
import db.dao.section.impl.SectionDAO;
import resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConferenceCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.conferences";
    private static final String ATTRIBUTE_CONFERENCES = "conferences";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        IConferenceDAO dao = new ConferenceDAO();
        List<Conference> conferences = dao.findByDate();
        ISectionDAO sectionDao = new SectionDAO();
        for (Conference conference : conferences) {
            long id = conference.getIdconference();
            List<Section> sections = sectionDao.findByConferenceId(id);
            conference.setSections(sections);
        }
        request.setAttribute(ATTRIBUTE_CONFERENCES, conferences);
        page = JspRoutesManager.getProperty(NEXT_PAGE);
        return page;
    }
}
