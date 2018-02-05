package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.User;
import com.eroshenkova.conference.logic.impl.UserLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);
        User user = new User(login, password);
        UserLogic userLogic = new UserLogic();
        User dbUser = userLogic.logIn(user);
        if (dbUser != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(Parameter.USER, dbUser.getLogin());
            session.setAttribute(Parameter.TYPE, dbUser.getType());
            page = UrlManager.getProperty(Page.PROFILE);
        } else {
            page = JspRoutesManager.getProperty(Page.JSP_INDEX);
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
        }
        return page;
    }
}
