package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Question;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PublishQuestionCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String questionText = request.getParameter(Parameter.QUESTION_ADMIN);
        String answer = request.getParameter(Parameter.ANSWER_ADMIN);
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(Parameter.USER);
        Question question = new Question(login, questionText, answer);
        QuestionService logic = new QuestionService();
        if (logic.create(question) == 0) {
            page = UrlManager.getProperty(Page.FAQ);
        } else {
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
        }
        return page;
    }
}
