package com.eroshenkova.conference.command.impl.page;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.service.impl.question.QuestionService;
import com.eroshenkova.conference.service.impl.question.impl.QuestionServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class FaqCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(FaqCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            QuestionService service = new QuestionServiceImpl();
            List<Question> questions = service.findWithAnswer();
            List<Question> noAnswerQuestions = service.findWithoutAnswer();
            request.setAttribute(Parameter.QUESTIONS, questions);
            request.setAttribute(Parameter.NO_ANSWER_QUESTIONS, noAnswerQuestions);
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database error. Cannot receive question data from database");
        }
        return page;
    }
}
