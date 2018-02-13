package com.eroshenkova.conference.command.impl.entry;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.entry.EntryService;
import com.eroshenkova.conference.service.impl.entry.impl.EntryServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Creates new entry
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class SignUpCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(SignUpCommand.class);

    /**
     * @param request is request from page
     * @return conference page or null if exception occurred
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute(Parameter.USER);
        String[] sectionIds = request.getParameterValues(Parameter.SECTIONS);
        EntryService service = new EntryServiceImpl();
        try {
            service.register(login, sectionIds);
            page = UrlManager.getProperty(Page.CONFERENCE);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Login and sectionIds is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot register user on conference");
        }
        return page;
    }
}
