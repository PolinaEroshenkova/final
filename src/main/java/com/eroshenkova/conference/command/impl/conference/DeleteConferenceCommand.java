package com.eroshenkova.conference.command.impl.conference;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.conference.ConferenceService;
import com.eroshenkova.conference.service.impl.conference.impl.ConferenceServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteConferenceCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteConferenceCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String stringIdConference = request.getParameter(Parameter.ID);
        Long idConference = Long.parseLong(stringIdConference);
        ConferenceService service = new ConferenceServiceImpl();
        try {
            service.delete(idConference);
            page = UrlManager.getProperty(Page.CONFERENCE);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Id conference is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot delete conference");
        }
        return page;
    }
}
