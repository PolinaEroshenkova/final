package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.user.UserService;
import com.eroshenkova.conference.service.impl.user.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.logging.log4j.LogManager.getLogger;

public class UpdatePasswordCommand implements ActionCommand {

    private static final Logger LOGGER = getLogger(UpdatePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        UserService service = new UserServiceImpl();
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
        String encryptedPassword = encryptor.encryptPassword(request.getParameter(Parameter.UPDATE_PASSWORD));
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute(Parameter.USER);
        try {
            service.updatePassword(encryptedPassword, login);
            page = UrlManager.getProperty(Page.PROFILE);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot update user");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Login or password is not defined");
        }
        return page;
    }
}
