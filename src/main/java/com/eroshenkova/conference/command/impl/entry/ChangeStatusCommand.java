package com.eroshenkova.conference.command.impl.entry;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.client.ObjectCreator;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.entity.impl.Entry;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.entry.EntryService;
import com.eroshenkova.conference.service.impl.entry.impl.EntryServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeStatusCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(ChangeStatusCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ObjectCreator creator = new ObjectCreator();
        Entry entry = creator.formEntryObject(request);
        EntryService service = new EntryServiceImpl();
        try {
            service.changeStatus(entry);
            page = UrlManager.getProperty(Page.MANAGEMENT);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Entry is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database error. Can't change status of entry");
        }
        return page;
    }
}
