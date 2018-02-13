package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.entry.EntryService;
import com.eroshenkova.conference.service.impl.entry.impl.EntryServiceImpl;
import com.eroshenkova.conference.service.impl.user.UserService;
import com.eroshenkova.conference.service.impl.user.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Receive data for profile
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class ProfileCommand implements ActionCommand {

    private static final Logger LOGGER = getLogger(ProfileCommand.class);

    /**
     * @param request is request from page
     * @return profile page or null if exception occurred
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute(Parameter.USER);
        try {
            UserService userService = new UserServiceImpl();
            User user = userService.formProfile(login);
            EntryService entryServiceImpl = new EntryServiceImpl();
            List<Entry> entries = entryServiceImpl.findByLogin(login);
            request.setAttribute(Parameter.USER, user);
            request.setAttribute(Parameter.ENTRIES, entries);
            page = JspRoutesManager.getProperty(Page.JSP_PROFILE);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Login is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "database error. Cannot receive profile data from database");
        }
        return page;
    }
}
