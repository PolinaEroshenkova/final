package com.eroshenkova.conference.command.impl.util;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty(Page.JSP_ERROR);
    }
}
