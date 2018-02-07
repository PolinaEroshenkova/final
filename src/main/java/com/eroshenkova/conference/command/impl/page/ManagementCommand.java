package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Entry;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.EntryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagementCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        EntryService logic = new EntryService();
        List<Entry> entries = logic.findByStatus();
        request.setAttribute(Parameter.ENTRIES, entries);
        return JspRoutesManager.getProperty(Page.JSP_MANAGEMENT);
    }
}
