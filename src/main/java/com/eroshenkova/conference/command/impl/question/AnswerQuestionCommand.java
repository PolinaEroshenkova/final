package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.User;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.MailService;
import com.eroshenkova.conference.service.impl.QuestionService;
import com.eroshenkova.conference.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;

public class AnswerQuestionCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String id = request.getParameter(Parameter.ID);
        String answer = request.getParameter(Parameter.ANSWER);
        String userLogin = request.getParameter(Parameter.LOGIN);
        UserService userService = new UserService();
        User user = userService.findByKey(userLogin);
        MailService logic = new MailService();
        logic.sendEmail(answer, user.getEmail());
        QuestionService questionService = new QuestionService();
        if (questionService.deleteByKey(Long.parseLong(id))) {
            page = UrlManager.getProperty(Page.FAQ);
        } else {
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
        }
        return page;
    }
}
