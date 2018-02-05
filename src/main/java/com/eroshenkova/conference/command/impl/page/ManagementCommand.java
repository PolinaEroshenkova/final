package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Entry;
import com.eroshenkova.conference.logic.impl.EntryLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagementCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        EntryLogic logic = new EntryLogic();
        List<Entry> entries = logic.findByStatus();
        request.setAttribute(Parameter.ENTRIES, entries);
        return JspRoutesManager.getProperty(Page.JSP_MANAGEMENT);
    }
}
