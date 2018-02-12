package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.User;
import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.question.QuestionService;
import com.eroshenkova.conference.service.impl.question.impl.QuestionServiceImpl;
import com.eroshenkova.conference.service.impl.user.UserService;
import com.eroshenkova.conference.service.impl.user.impl.UserServiceImpl;
import com.eroshenkova.conference.service.util.mail.MailService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static org.apache.logging.log4j.LogManager.getLogger;

public class AnswerQuestionCommand implements ActionCommand {
    private static final Logger LOGGER = getLogger(AnswerQuestionCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id = request.getParameter(Parameter.ID);
        String answer = request.getParameter(Parameter.ANSWER);
        String userLogin = request.getParameter(Parameter.LOGIN);
        UserService userService = new UserServiceImpl();
        try {
            User user = userService.findByKey(userLogin);
            MailService logic = new MailService();
            logic.sendEmail(answer, user.getEmail());
            QuestionService questionService = new QuestionServiceImpl();
            questionService.delete(Long.parseLong(id));
            page = UrlManager.getProperty(Page.FAQ);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Answer or user email is not defined");
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, "Database exception. Cannot add answer to question");
        }
        return page;
    }
}
