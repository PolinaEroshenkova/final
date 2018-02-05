package com.eroshenkova.conference.command.impl.entry;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.logic.impl.EntryLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteEntryCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String idEntryString = request.getParameter(Parameter.ID);
        Long idEntry = Long.parseLong(idEntryString);
        EntryLogic logic = new EntryLogic();
        if (logic.delete(idEntry)) {
            page = UrlManager.getProperty(Page.PROFILE);
        } else {
            page = JspRoutesManager.getProperty(Page.PROFILE);
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
        }
        return page;
    }
}
