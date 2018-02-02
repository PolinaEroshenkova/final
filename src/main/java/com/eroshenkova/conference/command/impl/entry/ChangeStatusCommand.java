package com.eroshenkova.conference.command.impl.entry;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Message;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.logic.impl.EntryLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.MessageManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeStatusCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String stringIdEntry = request.getParameter(Parameter.ID);
        long idEntry = Long.parseLong(stringIdEntry);
        String status = request.getParameter(Parameter.STATUS);
        EntryLogic logic = new EntryLogic();
        if (logic.changeStatus(idEntry, status)) {
            page = UrlManager.getProperty(Page.MANAGEMENT);
        } else {
            request.setAttribute(Parameter.ERROR_MESSAGE, MessageManager.getProperty(Message.SERVER_ERROR));
            page = JspRoutesManager.getProperty(Page.JSP_MANAGEMENT);
        }
        return page;
    }
}
