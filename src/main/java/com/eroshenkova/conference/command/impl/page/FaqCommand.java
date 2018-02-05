package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Question;
import com.eroshenkova.conference.logic.impl.QuestionLogic;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FaqCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        QuestionLogic logic = new QuestionLogic();
        List<Question> questions = logic.findWithAnswer();
        List<Question> noAnswerQuestions = logic.findWithoutAnswer();
        request.setAttribute(Parameter.QUESTIONS, questions);
        request.setAttribute(Parameter.NO_ANSWER_QUESTIONS, noAnswerQuestions);
        return JspRoutesManager.getProperty(Page.JSP_FAQ);
    }
}
