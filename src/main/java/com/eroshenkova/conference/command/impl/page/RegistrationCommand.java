package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty("path.page.registration");
    }
}
