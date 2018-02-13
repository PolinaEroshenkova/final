package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Returns registration command
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class RegistrationCommand implements ActionCommand {

    /**
     * @param request is request from page
     * @return registration command
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty(Page.JSP_REGISTRATION);
    }
}
