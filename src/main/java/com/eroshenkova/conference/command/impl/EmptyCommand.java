package com.eroshenkova.conference.command.impl;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.index";

    @Override
    public String execute(HttpServletRequest request) {
        String page = JspRoutesManager.getProperty(NEXT_PAGE);
        return page;
    }
}
