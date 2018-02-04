package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Message;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Question;
import com.eroshenkova.conference.logic.impl.QuestionLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.MessageManager;
import com.eroshenkova.conference.resource.UrlManager;

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
        QuestionLogic logic = new QuestionLogic();
        if (logic.create(question)) {
            page = UrlManager.getProperty(Page.FAQ);
        } else {
            request.setAttribute(Parameter.ERROR_MESSAGE, MessageManager.getProperty(Message.SERVER_ERROR));
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
        }
        return page;
    }
}
