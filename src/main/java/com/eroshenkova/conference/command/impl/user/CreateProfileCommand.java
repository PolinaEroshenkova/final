package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.client.ObjectCreator;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Participant;
import com.eroshenkova.conference.entity.User;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;

public class CreateProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        ObjectCreator creator = new ObjectCreator();
        User user = creator.formUserObject(request);
        Participant participant = creator.formParticipantObject(request);
        UserService userService = new UserService();
        long userLogicResult = userService.register(user, participant);
        if (userLogicResult < 0) {
            page = JspRoutesManager.getProperty(Page.JSP_REGISTRATION);
            request.setAttribute(Parameter.ERROR_EMAIL, Parameter.ERROR_EMAIL_MESSAGE);//email is exist
        } else if (userLogicResult == 0) {
            page = JspRoutesManager.getProperty(Page.JSP_REGISTRATION); //login is exist
            request.setAttribute(Parameter.ERROR_LOGIN, Parameter.ERROR_LOGIN_MESSAGE);
        } else {
            page = UrlManager.getProperty(Page.INDEX); //OK
        }
        return page;
    }
}
