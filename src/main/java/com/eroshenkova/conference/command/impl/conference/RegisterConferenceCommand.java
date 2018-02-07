package com.eroshenkova.conference.command.impl.conference;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.Conference;
import com.eroshenkova.conference.entity.Section;
import com.eroshenkova.conference.locale.DateWorker;
import com.eroshenkova.conference.resource.JspRoutesManager;
import com.eroshenkova.conference.resource.UrlManager;
import com.eroshenkova.conference.service.impl.ConferenceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterConferenceCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterConferenceCommand.class);
    private static final String DELIMITER_SPACE = " ";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            Conference conference = formConferenceObject(request);
            ConferenceService logic = new ConferenceService();
            if (logic.register(conference)) {
                page = UrlManager.getProperty(Page.CONFERENCE);
            } else {
                request.setAttribute(Parameter.ERROR_MESSAGE, Parameter.SERVER_ERROR_MESSAGE);
                page = JspRoutesManager.getProperty(Page.JSP_NEW_CONFERENCE);
            }
        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Cannot parse date to format");
        }
        return page;
    }

    private Conference formConferenceObject(HttpServletRequest request) throws ParseException {
        String topic = request.getParameter(Parameter.TOPIC);
        String number = request.getParameter(Parameter.NUMBER);
        Integer intnumber = Integer.parseInt(number);
        String place = request.getParameter(Parameter.PLACE);
        String dateStart = request.getParameter(Parameter.DATE_START);
        String timeStart = request.getParameter(Parameter.TIME_START);
        String dateEnd = request.getParameter(Parameter.DATE_END);
        String timeEnd = request.getParameter(Parameter.TIME_END);
        String deadline = request.getParameter(Parameter.DEADLINE);
        HttpSession session = request.getSession(false);
        String locale = (String) session.getAttribute(Parameter.LOCALE);
        Date sqlStartDate = DateWorker.formatDateTimeByLocale(String.join(DELIMITER_SPACE, dateStart, timeStart), locale);
        Date sqlEndDate = DateWorker.formatDateTimeByLocale(String.join(DELIMITER_SPACE, dateEnd, timeEnd), locale);
        Date sqlDeadline = DateWorker.formatDateByLocale(deadline, locale);
        String[] sectionArray = request.getParameterValues(Parameter.SECTIONS);
        List<Section> sections = new ArrayList<>();
        for (String title : sectionArray) {
            Section section = new Section(title);
            sections.add(section);
        }
        return new Conference(topic, intnumber, place, sqlStartDate, sqlEndDate, sqlDeadline, sections);
    }
}
