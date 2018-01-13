package command;

import db.dao.AbstractDAO;
import db.dao.conference.entity.Conference;
import db.dao.conference.impl.ConferenceDAO;
import db.dao.section.ISectionDAO;
import db.dao.section.entity.Section;
import db.dao.section.impl.SectionDAO;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SignUpCommand implements ActionCommand {
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_CONFERENCE = "conference";
    private static final String ATTRIBUTE_SECTIONS = "sections";
    private static final String NEXT_PAGE = "path.page.entry";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        AbstractDAO<Integer, Conference> conferenceDao = new ConferenceDAO();
        String stringId = request.getParameter(PARAM_ID);
        int id = Integer.parseInt(stringId);
        Conference conference = conferenceDao.findByKey(id);
        request.setAttribute(ATTRIBUTE_CONFERENCE, conference);
        ISectionDAO sectionDao = new SectionDAO();
        List<Section> sections = sectionDao.findByConferenceId(id);
        request.setAttribute(ATTRIBUTE_SECTIONS, sections);
        page = ConfigurationManager.getProperty(NEXT_PAGE);
        return page;
    }
}
