package com.eroshenkova.conference.command.impl.conference;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class DeleteConferenceCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String stringIdConference = request.getParameter(Parameter.ID);
        Long idConference = Long.parseLong(stringIdConference);
        ConferenceService logic = new ConferenceService();
        if (logic.deleteConference(idConference)) {
            page = UrlManager.getProperty(Page.CONFERENCE);
        } else {
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
            page = JspRoutesManager.getProperty(Page.JSP_ENTRY);
        }
        return page;
    }
}
