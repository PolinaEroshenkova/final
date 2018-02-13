package com.eroshenkova.conference.command.impl.util;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Defines whether command is not defined
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class EmptyCommand implements ActionCommand {

    /**
     * @param request is request from page
     * @return error page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty(Page.JSP_ERROR);
    }
}
