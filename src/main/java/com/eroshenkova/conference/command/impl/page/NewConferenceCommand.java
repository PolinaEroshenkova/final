package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class NewConferenceCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.newconference";

    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty(NEXT_PAGE);
    }
}
