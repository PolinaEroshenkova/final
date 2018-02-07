package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Entry;
import com.eroshenkova.conference.entity.User;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.EntryService;
import com.eroshenkova.conference.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(Parameter.USER);
        UserService userService = new UserService();
        User user = userService.formProfile(login);
        EntryService entryService = new EntryService();
        List<Entry> entries = entryService.findByLogin(login);
        request.setAttribute(Parameter.USER, user);
        request.setAttribute(Parameter.ENTRIES, entries);
        return JspRoutesManager.getProperty(Page.JSP_PROFILE);
    }
}
