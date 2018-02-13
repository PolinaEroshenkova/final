package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.client.ObjectCreator;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.Participant;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.user.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.logging.log4j.LogManager.getLogger;

public class UpdateUserCommand implements ActionCommand {

    private static final Logger LOGGER = getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ObjectCreator creator = new ObjectCreator();
        User user = creator.formUserObject(request);
        Participant participant = creator.formParticipantObject(request);
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute(Parameter.USER);
        user.setParticipant(participant);
        try {
            UserServiceImpl service = new UserServiceImpl();
            User returnUser = service.updateProfile(user, login);
            if (returnUser.equals(user)) {
                session.setAttribute(Parameter.USER, user.getLogin());
                page = UrlManager.getProperty(Page.PROFILE);
            } else {
                request.setAttribute(Parameter.ERROR_EMAIL, Parameter.ERROR_EMAIL_MESSAGE);
                request.setAttribute(Parameter.USER, returnUser);
                page = JspRoutesManager.getProperty(Page.JSP_PROFILE);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Login is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database error. Cannot update user info");
        }
        return page;
    }

}
