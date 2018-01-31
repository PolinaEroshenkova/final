package com.eroshenkova.conference.command.impl.user;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.db.dao.participant.entity.Participant;
import com.eroshenkova.conference.db.dao.user.entity.User;
import com.eroshenkova.conference.logic.impl.UserLogic;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;

public class CreateProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = formUserObject(request);
        Participant participant = formParticipantObject(request);
        UserLogic userLogic = new UserLogic();
        int userLogicResult = userLogic.register(user, participant);
        if (userLogicResult < 0) {
            page = UrlManager.getProperty(Page.ERROR); //email is exist
        } else if (userLogicResult == 0) {
            page = UrlManager.getProperty(Page.ERROR); //login is exist
        } else {
            page = UrlManager.getProperty(Page.REGISTRATION); //OK
        }
        return page;
    }

    private User formUserObject(HttpServletRequest request) {
        String login = request.getParameter(Parameter.REGISTRATION_LOGIN);
        String password = request.getParameter(Parameter.REGISTRATION_PASSWORD);
        String email = request.getParameter(Parameter.EMAIL);
        return new User(login, password, email);
    }

    private Participant formParticipantObject(HttpServletRequest request) {
        String login = request.getParameter(Parameter.REGISTRATION_LOGIN);
        String surname = request.getParameter(Parameter.SURNAME);
        String name = request.getParameter(Parameter.NAME);
        String scope = request.getParameter(Parameter.SCOPE);
        return new Participant(login, surname, name, scope);
    }
}
