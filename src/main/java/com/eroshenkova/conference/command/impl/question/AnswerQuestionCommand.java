package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Message;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.db.dao.user.entity.User;
import com.eroshenkova.conference.logic.impl.MailLogic;
import com.eroshenkova.conference.logic.impl.QuestionLogic;
import com.eroshenkova.conference.logic.impl.UserLogic;
import com.eroshenkova.conference.mail.MailThread;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.MailManager;
import com.eroshenkova.conference.resource.MessageManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;

public class AnswerQuestionCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String id = request.getParameter(Parameter.ID);
        String answer = request.getParameter(Parameter.ANSWER);
        String userLogin = request.getParameter(Parameter.LOGIN);
        UserLogic userLogic = new UserLogic();
        User user = userLogic.findByKey(userLogin);
        MailThread mail = new MailThread(MailManager.getProperty("mail.user.name"), user.getEmail(), Parameter.EMAIL_SUBJECT, answer);
        MailLogic logic = new MailLogic();
        logic.sendEmail(mail);
        QuestionLogic questionLogic = new QuestionLogic();
        if (questionLogic.deleteByKey(Long.parseLong(id))) {
            page = UrlManager.getProperty(Page.FAQ);
        } else {
            page = JspRoutesManager.getProperty(Page.JSP_FAQ);
            request.setAttribute(Parameter.ERROR_MESSAGE, MessageManager.getProperty(Message.SERVER_ERROR));
        }
        return page;
    }
}
