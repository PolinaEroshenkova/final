package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.db.DAO;
import com.eroshenkova.conference.db.dao.conference.entity.Conference;
import com.eroshenkova.conference.db.dao.conference.impl.ConferenceDAO;
import com.eroshenkova.conference.db.dao.section.ISectionDAO;
import com.eroshenkova.conference.db.dao.section.entity.Section;
import com.eroshenkova.conference.db.dao.section.impl.SectionDAO;
import com.eroshenkova.conference.resource.JspRoutesManager;

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
