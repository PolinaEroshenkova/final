package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.question.QuestionService;
import com.eroshenkova.conference.service.impl.question.impl.QuestionServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Creates user's question
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class AskQuestionCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(AskQuestionCommand.class);

    /**
     * @param request is request from page
     * @return faq page or null if exception occurred
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute(Parameter.USER);
        String text = request.getParameter(Parameter.QUESTION);
        Question question = new Question(login, text);
        try {
            QuestionService logic = new QuestionServiceImpl();
            logic.register(question);
            page = UrlManager.getProperty(Page.FAQ);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Question is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database error. Cannot add question data");
        }
        return page;
    }
}
