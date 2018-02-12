package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        String referer = request.getHeader(Parameter.REQUEST_REFERER);
        if (referer.equals(Page.MANAGEMENT) || referer.equals(Page.REGISTRATION) ||
                referer.equals(Page.PROFILE) || referer.equals(Page.ENTRY)) {
            return Page.INDEX;
        } else {
            return referer;
        }
    }
}
