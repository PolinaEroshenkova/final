package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.db.dao.conference.entity.Conference;
import com.eroshenkova.conference.db.dao.section.ISectionDAO;
import com.eroshenkova.conference.db.dao.section.entity.Section;
import com.eroshenkova.conference.db.dao.section.impl.SectionDAO;
import com.eroshenkova.conference.logic.impl.ConferenceLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConferenceCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.conferences";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ConferenceLogic conferenceLogic = new ConferenceLogic();
        List<Conference> conferences = conferenceLogic.findByDate();

        ISectionDAO sectionDao = new SectionDAO();
        for (Conference conference : conferences) {
            long id = conference.getIdconference();
            List<Section> sections = sectionDao.findByConferenceId(id);
            conference.setSections(sections);
        }

        request.setAttribute(Parameter.CONFERENCES, conferences);
        page = JspRoutesManager.getProperty(NEXT_PAGE);
        return page;
    }
}
