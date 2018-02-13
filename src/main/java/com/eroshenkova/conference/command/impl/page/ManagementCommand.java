package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.entry.EntryService;
import com.eroshenkova.conference.service.impl.entry.impl.EntryServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Receives data for management page
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class ManagementCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(ManagementCommand.class);

    /**
     * @param request is request from page
     * @return management page or null if exception occurred
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            EntryService service = new EntryServiceImpl();
            List<Entry> entries = service.findByStatus();
            request.setAttribute(Parameter.ENTRIES, entries);
            page = JspRoutesManager.getProperty(Page.JSP_MANAGEMENT);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Crashing data from jsp");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database error. Cannot receive entries data from database");
        }
        return page;
    }
}
