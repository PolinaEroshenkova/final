package com.eroshenkova.conference.command.impl.question;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.db.dao.user.entity.User;
import com.eroshenkova.conference.logic.impl.MailLogic;
import com.eroshenkova.conference.logic.impl.UserLogic;
import com.eroshenkova.conference.mail.MailThread;
import com.eroshenkova.conference.resource.MailManager;
import com.eroshenkova.conference.resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AskQuestionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(Parameter.USER);
        String text = request.getParameter(Parameter.QUESTION);
        UserLogic userLogic = new UserLogic();
        User user = userLogic.findByKey(login);
        MailThread mail = new MailThread(user.getEmail(), MailManager.getProperty("mail.user.name"), Parameter.EMAIL_SUBJECT, text);
        MailLogic logic = new MailLogic();
        logic.sendEmail(mail);
        return UrlManager.getProperty(Page.FAQ);
    }
}
