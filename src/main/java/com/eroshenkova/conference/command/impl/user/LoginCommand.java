package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.client.ObjectCreator;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.user.UserService;
import com.eroshenkova.conference.service.impl.user.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Logs in user
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class LoginCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(LoginCommand.class);

    /**
     * @param request is request from page
     * @return same page if user is logged in successfully or not
     *         null if exception occurred
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ObjectCreator creator = new ObjectCreator();
        User user = creator.formUserObject(request);
        try {
            UserService userService = new UserServiceImpl();
            User dbUser = userService.logIn(user);
            if (dbUser != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute(Parameter.USER, dbUser.getLogin());
                session.setAttribute(Parameter.TYPE, dbUser.getType());
                page = request.getHeader(Parameter.REQUEST_REFERER);
            } else {
                page = JspRoutesManager.getProperty(Page.JSP_INDEX);
                request.setAttribute(Parameter.ERROR_LOGIN, Parameter.ERROR_SIGNIN_MESSAGE);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "User is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot find user info");
        }
        return page;
    }
}
