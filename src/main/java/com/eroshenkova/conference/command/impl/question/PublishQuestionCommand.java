package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.client.ObjectCreator;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.entity.impl.Question;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.question.QuestionService;
import com.eroshenkova.conference.service.impl.question.impl.QuestionServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Publish questions on page
 *
 * @author Palina Yerashenkava
 * @see ActionCommand
 */
public class PublishQuestionCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(DeleteQuestionCommand.class);

    /**
     * @param request is request from page
     * @return faq page or null if exception occurred
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ObjectCreator creator = new ObjectCreator();
        Question question = creator.formQuestionObject(request);
        try {
            QuestionService service = new QuestionServiceImpl();
            service.register(question);
            page = UrlManager.getProperty(Page.FAQ);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Question is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot add new question");
        }
        return page;
    }
}
