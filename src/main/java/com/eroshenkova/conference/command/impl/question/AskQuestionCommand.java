package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Question;
import com.eroshenkova.conference.logic.impl.QuestionLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AskQuestionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(Parameter.USER);
        String text = request.getParameter(Parameter.QUESTION);
        Question question = new Question(login, text);
        QuestionLogic logic = new QuestionLogic();
        if (logic.create(question)) {
            page = UrlManager.getProperty(Page.FAQ);
        } else {
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
        }
        return page;
    }
}
