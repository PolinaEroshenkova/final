package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.db.dao.entry.entity.Entry;
import com.eroshenkova.conference.logic.impl.EntryLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagementCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        EntryLogic logic = new EntryLogic();
        List<Entry> entries = logic.findByStatus();
        if (entries != null) {
            request.setAttribute(Parameter.ENTRIES, entries);
            page = JspRoutesManager.getProperty(Page.JSP_MANAGEMENT);
        } else {
            page = UrlManager.getProperty(Page.ERROR);
        }
        return page;
    }
}
