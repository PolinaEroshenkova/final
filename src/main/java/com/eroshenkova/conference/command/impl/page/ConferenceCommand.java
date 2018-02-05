package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.logic.impl.ConferenceLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConferenceCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        ConferenceLogic conferenceLogic = new ConferenceLogic();
        List<Conference> conferences = conferenceLogic.findByDate();
        request.setAttribute(Parameter.CONFERENCES, conferences);
        return JspRoutesManager.getProperty(Page.JSP_CONFERENCE);
    }
}
