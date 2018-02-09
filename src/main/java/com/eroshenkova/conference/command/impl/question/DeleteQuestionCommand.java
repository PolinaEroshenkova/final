package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.QuestionService;

import javax.servlet.http.HttpServletRequest;

public class DeleteQuestionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String stringIdQuestion = request.getParameter(Parameter.ID);
        long idQuestion = Long.parseLong(stringIdQuestion);
        QuestionService logic = new QuestionService();
        if (logic.deleteByKey(idQuestion)) {
            page = UrlManager.getProperty(Page.FAQ);
        } else {
            request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
        }
        return page;
    }
}
