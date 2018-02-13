package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Logs user out
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class LogoutCommand implements ActionCommand {

    /**
     * @param request is request from page
     * @return same page if user is on approved page
     *          index page if user on disapproved page
     */
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        String referer = request.getHeader(Parameter.REQUEST_REFERER);
        String address = request.getRemoteAddr();
        if (address.equals(Page.MANAGEMENT) || address.equals(Page.REGISTRATION) ||
                address.equals(Page.PROFILE) || address.equals(Page.ENTRY)) {
            return Page.INDEX;
        } else {
            return referer;
        }
    }
}
