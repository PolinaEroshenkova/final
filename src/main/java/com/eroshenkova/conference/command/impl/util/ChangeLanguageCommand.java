package com.eroshenkova.conference.command.impl.util;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(Parameter.LANGUAGE);
        String locale = request.getParameter(Parameter.LOCALE);
        HttpSession session = request.getSession(true);
        session.setAttribute(Parameter.LANGUAGE, language);
        session.setAttribute(Parameter.LOCALE, locale);
        return UrlManager.getProperty(Page.INDEX);
    }
}
