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

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Deletes entry
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class DeleteEntryCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(DeleteEntryCommand.class);

    /**
     * @param request is request from page
     * @return profile page or null if exception occurred
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String idEntryString = request.getParameter(Parameter.ID);
        Long idEntry = Long.parseLong(idEntryString);
        EntryService service = new EntryServiceImpl();
        try {
            service.delete(idEntry);
            page = UrlManager.getProperty(Page.PROFILE);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Id entry is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot delete entry");
        }
        return page;
    }
}
