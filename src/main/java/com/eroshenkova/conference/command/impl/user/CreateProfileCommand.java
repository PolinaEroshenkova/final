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
import com.eroshenkova.conference.service.impl.user.UserService;
import com.eroshenkova.conference.service.impl.user.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.servlet.http.HttpServletRequest;

import static org.apache.logging.log4j.LogManager.getLogger;

public class CreateProfileCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(CreateProfileCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ObjectCreator creator = new ObjectCreator();
        User user = creator.formUserObject(request);
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
        String encryptedPassword = encryptor.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);
        Participant participant = creator.formParticipantObject(request);
        try {
            UserService userService = new UserServiceImpl();
            long userLogicResult = userService.register(user, participant);
            if (userLogicResult < 0) {
                page = JspRoutesManager.getProperty(Page.JSP_REGISTRATION);
                request.setAttribute(Parameter.ERROR_EMAIL, Parameter.ERROR_EMAIL_MESSAGE);
            } else if (userLogicResult == 0) {
                page = JspRoutesManager.getProperty(Page.JSP_REGISTRATION);
                request.setAttribute(Parameter.ERROR_LOGIN, Parameter.ERROR_LOGIN_MESSAGE);
            } else {
                page = UrlManager.getProperty(Page.INDEX);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "User or participant is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot add profile");
        }
        return page;
    }
}
