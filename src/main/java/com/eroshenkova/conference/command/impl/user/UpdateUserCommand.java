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
import javax.servlet.http.HttpSession;

public class UpdateUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        ObjectCreator creator = new ObjectCreator();
        User user = creator.formUserObject(request);
        Participant participant = creator.formParticipantObject(request);
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute(Parameter.USER);
        UserService service = new UserService();
        user.setParticipant(participant);
        if (service.updateProfile(user, participant, login)) {
            session.setAttribute(Parameter.USER, user.getLogin());
            page = UrlManager.getProperty(Page.PROFILE);
            request.setAttribute(Parameter.USER, user);
        } else {
            page = JspRoutesManager.getProperty(Page.JSP_PROFILE);
            request.setAttribute(Parameter.USER, user);
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
        }
        return page;
    }

}
