package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.db.dao.question.entity.Question;
import com.eroshenkova.conference.logic.impl.QuestionLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FaqCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        QuestionLogic logic = new QuestionLogic();
        List<Question> questions = logic.findAll();
        if (questions == null) {
            page = UrlManager.getProperty(Page.ERROR);
        } else {
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
            request.setAttribute(Parameter.QUESTIONS, questions);
        }
        return page;
    }
}
