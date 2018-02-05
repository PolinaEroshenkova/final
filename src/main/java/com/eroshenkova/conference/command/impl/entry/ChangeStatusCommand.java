package com.eroshenkova.conference.command.impl.entry;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Entry;
import com.eroshenkova.conference.logic.impl.EntryLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeStatusCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String stringIdEntry = request.getParameter(Parameter.ID);
        long idEntry = Long.parseLong(stringIdEntry);
        String status = request.getParameter(Parameter.STATUS);
        String login = request.getParameter(Parameter.LOGIN);
        Entry entry = new Entry(idEntry, login, status);
        EntryLogic logic = new EntryLogic();
        if (logic.changeStatus(entry)) {
            page = UrlManager.getProperty(Page.MANAGEMENT);
        } else {
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
            page = JspRoutesManager.getProperty(Page.JSP_MANAGEMENT);
        }
        return page;
    }
}
