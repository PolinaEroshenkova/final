package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.conference.ConferenceService;
import com.eroshenkova.conference.service.impl.conference.impl.ConferenceServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static org.apache.logging.log4j.LogManager.getLogger;

public class EntryCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(EntryCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String stringId = request.getParameter(Parameter.ID);
        long id = Long.parseLong(stringId);
        ConferenceService service = new ConferenceServiceImpl();
        try {
            Conference conference = service.findByKey(id);
            request.setAttribute(Parameter.CONFERENCE, conference);
            page = JspRoutesManager.getProperty(Page.JSP_ENTRY);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Id conference is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot receive conference data from database");
        }
        return page;
    }
}
