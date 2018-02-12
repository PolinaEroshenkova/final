package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.question.QuestionService;
import com.eroshenkova.conference.service.impl.question.impl.QuestionServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static org.apache.logging.log4j.LogManager.getLogger;

public class DeleteQuestionCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(DeleteQuestionCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String stringIdQuestion = request.getParameter(Parameter.ID);
        long idQuestion = Long.parseLong(stringIdQuestion);
        try {
            QuestionService logic = new QuestionServiceImpl();
            logic.delete(idQuestion);
            page = UrlManager.getProperty(Page.FAQ);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Id question is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database error. Cannot delete question");
        }
        return page;
    }
}
