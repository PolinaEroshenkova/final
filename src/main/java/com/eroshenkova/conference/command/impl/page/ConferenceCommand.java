package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.conference.ConferenceService;
import com.eroshenkova.conference.service.impl.conference.impl.ConferenceServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ConferenceCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(ConferenceCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ConferenceService service = new ConferenceServiceImpl();
        try {
            List<Conference> conferences = service.findByDate();
            request.setAttribute(Parameter.CONFERENCES, conferences);
            page = JspRoutesManager.getProperty(Page.JSP_CONFERENCE);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database error. Cannot receive conference data from database");
        }
        return page;
    }
}
