package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Returns index page
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class IndexCommand implements ActionCommand {

    /**
     * @param request is request from page
     * @return index page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty(Page.JSP_INDEX);
    }
}
