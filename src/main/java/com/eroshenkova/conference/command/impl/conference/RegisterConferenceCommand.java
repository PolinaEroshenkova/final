package com.eroshenkova.conference.command.impl.conference;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.client.ObjectCreator;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.entity.impl.Conference;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.conference.ConferenceService;
import com.eroshenkova.conference.service.impl.conference.impl.ConferenceServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * Creates new conference
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class RegisterConferenceCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterConferenceCommand.class);

    /**
     * @param request is request from page
     * @return conference page or null if exception occurred
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            ObjectCreator creator = new ObjectCreator();
            Conference conference = creator.formConferenceObject(request);
            ConferenceService service = new ConferenceServiceImpl();
            service.register(conference);
            page = UrlManager.getProperty(Page.CONFERENCE);
        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Cannot parse date to format");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot register new conference");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Conference is not defined");
        }
        return page;
    }
}
