package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.db.dao.conference.entity.Conference;
import com.eroshenkova.conference.logic.impl.ConferenceLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConferenceCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        ConferenceLogic conferenceLogic = new ConferenceLogic();
        List<Conference> conferences = conferenceLogic.findByDate();
        if (conferences != null) {
            request.setAttribute(Parameter.CONFERENCES, conferences);
            page = JspRoutesManager.getProperty(Page.CONFERENCE);
        } else {
            page = UrlManager.getProperty(Page.ERROR);
        }
        return page;
    }
}
