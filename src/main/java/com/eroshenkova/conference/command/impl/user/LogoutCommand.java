package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Logs user out
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class LogoutCommand implements ActionCommand {

    private static final String DELIMITER_SLASH = "/";
    /**
     * @param request is request from page
     * @return same page if user is on approved page
     *          index page if user on disapproved page
     */
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        String referer = request.getHeader(Parameter.REQUEST_REFERER);
        String[] split = referer.split(DELIMITER_SLASH);
        String address = DELIMITER_SLASH + split[split.length - 2] + DELIMITER_SLASH + split[split.length - 1];
        if (address.equals(UrlManager.getProperty(Page.MANAGEMENT)) ||
                address.equals(UrlManager.getProperty(Page.REGISTRATION)) ||
                address.equals(UrlManager.getProperty(Page.PROFILE)) ||
                address.equals(UrlManager.getProperty(Page.ENTRY))) {
            return UrlManager.getProperty(Page.INDEX);
        } else {
            return referer;
        }
    }
}
