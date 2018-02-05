package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.User;
import com.eroshenkova.conference.logic.impl.MailLogic;
import com.eroshenkova.conference.logic.impl.QuestionLogic;
import com.eroshenkova.conference.logic.impl.UserLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;

public class AnswerQuestionCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String id = request.getParameter(Parameter.ID);
        String answer = request.getParameter(Parameter.ANSWER);
        String userLogin = request.getParameter(Parameter.LOGIN);
        UserLogic userLogic = new UserLogic();
        User user = userLogic.findByKey(userLogin);
        MailLogic logic = new MailLogic();
        logic.sendEmail(answer, user.getEmail());
        QuestionLogic questionLogic = new QuestionLogic();
        if (questionLogic.deleteByKey(Long.parseLong(id))) {
            page = UrlManager.getProperty(Page.FAQ);
        } else {
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
        }
        return page;
    }
}
