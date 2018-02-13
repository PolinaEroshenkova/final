package com.eroshenkova.conference.command.impl.util;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Changes language of pages
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class ChangeLanguageCommand implements ActionCommand {

    /**
     * @param request is request from page
     * @return next same page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(Parameter.LANGUAGE);
        String locale = request.getParameter(Parameter.LOCALE);
        HttpSession session = request.getSession(true);
        session.setAttribute(Parameter.LANGUAGE, language);
        session.setAttribute(Parameter.LOCALE, locale);
        return request.getHeader(Parameter.REQUEST_REFERER);
    }
}
