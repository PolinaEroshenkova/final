package com.eroshenkova.conference.command.impl.entry;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.logic.impl.EntryLogic;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(Parameter.USER);
        String[] sectionIds = request.getParameterValues(Parameter.SECTIONS);
        EntryLogic logic = new EntryLogic();
        if (logic.register(login, sectionIds)) {
            page = UrlManager.getProperty(Page.ENTRY);
        } else {
            page = UrlManager.getProperty(Page.ERROR);
        }
        return page;
    }
}
