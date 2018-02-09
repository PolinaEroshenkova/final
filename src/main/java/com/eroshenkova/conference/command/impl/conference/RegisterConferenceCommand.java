package com.eroshenkova.conference.command.impl.conference;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.client.ObjectCreator;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.ConferenceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class RegisterConferenceCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterConferenceCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            ObjectCreator creator = new ObjectCreator();
            Conference conference = creator.formConferenceObject(request);
            ConferenceService logic = new ConferenceService();
            if (logic.register(conference)) {
                page = UrlManager.getProperty(Page.CONFERENCE);
            } else {
                request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
                page = JspRoutesManager.getProperty(Page.JSP_NEW_CONFERENCE);
            }
        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Cannot parse date to format");
        }
        return page;
    }
}
