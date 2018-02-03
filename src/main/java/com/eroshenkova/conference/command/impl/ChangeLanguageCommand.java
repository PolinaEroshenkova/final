package com.eroshenkova.conference.command.impl;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String lang = request.getParameter("language");
        HttpSession session = request.getSession(true);
        session.setAttribute("lang", lang);
        return UrlManager.getProperty(Page.INDEX);
    }
}
