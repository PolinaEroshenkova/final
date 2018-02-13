package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Returns new conference page
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class NewConferenceCommand implements ActionCommand {

    /**
     * @param request is request from page
     * @return new conference page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty(Page.JSP_NEW_CONFERENCE);
    }
}
