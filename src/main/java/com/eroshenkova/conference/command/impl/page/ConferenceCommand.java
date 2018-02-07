package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConferenceCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        ConferenceService conferenceLogic = new ConferenceService();
        List<Conference> conferences = conferenceLogic.findByDate();
        request.setAttribute(Parameter.CONFERENCES, conferences);
        return JspRoutesManager.getProperty(Page.JSP_CONFERENCE);
    }
}
