package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.logic.impl.ConferenceLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class EntryCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String stringId = request.getParameter(Parameter.ID);
        if (stringId != null) {
            long id = Long.parseLong(stringId);
            ConferenceLogic logic = new ConferenceLogic();
            Conference conference = logic.findByKey(id);
            page = JspRoutesManager.getProperty(Page.JSP_ENTRY);
            request.setAttribute(Parameter.CONFERENCE, conference);
        }
        return page;
    }
}
