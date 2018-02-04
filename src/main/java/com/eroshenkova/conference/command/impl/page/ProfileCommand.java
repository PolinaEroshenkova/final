package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Entry;
import com.eroshenkova.conference.entity.User;
import com.eroshenkova.conference.logic.impl.EntryLogic;
import com.eroshenkova.conference.logic.impl.UserLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(Parameter.USER);
        UserLogic userLogic = new UserLogic();
        User user = userLogic.formProfile(login);
        EntryLogic entryLogic = new EntryLogic();
        List<Entry> entries = entryLogic.findByLogin(login);
        if (user != null && entries != null) {
            request.setAttribute(Parameter.USER, user);
            request.setAttribute(Parameter.ENTRIES, entries);
            page = JspRoutesManager.getProperty(Page.JSP_PROFILE);
        }
        return page;
    }
}
