package command.impl.page;

import command.ActionCommand;
import db.DAO;
import db.dao.conference.entity.Conference;
import db.dao.conference.impl.ConferenceDAO;
import db.dao.section.ISectionDAO;
import db.dao.section.entity.Section;
import db.dao.section.impl.SectionDAO;
import resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EntryCommand implements ActionCommand {
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_CONFERENCE = "conference";
    private static final String ATTRIBUTE_SECTIONS = "sections";
    private static final String NEXT_PAGE = "path.page.entry";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        DAO<Long, Conference> conferenceDao = new ConferenceDAO();
        String stringId = request.getParameter(PARAM_ID);
        long id = Long.parseLong(stringId);
        Conference conference = conferenceDao.findByKey(id);
        request.setAttribute(ATTRIBUTE_CONFERENCE, conference);
        ISectionDAO sectionDao = new SectionDAO();
        List<Section> sections = sectionDao.findByConferenceId(id);
        request.setAttribute(ATTRIBUTE_SECTIONS, sections);
        page = JspRoutesManager.getProperty(NEXT_PAGE);
        return page;
    }
}
