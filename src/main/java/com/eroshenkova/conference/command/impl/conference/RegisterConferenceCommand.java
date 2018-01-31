package com.eroshenkova.conference.command.impl.conference;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.db.dao.conference.entity.Conference;
import com.eroshenkova.conference.db.dao.section.entity.Section;
import com.eroshenkova.conference.locale.DateWorker;
import com.eroshenkova.conference.logic.impl.ConferenceLogic;
import com.eroshenkova.conference.resource.UrlManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
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
            ConferenceLogic logic = new ConferenceLogic();
            if (logic.register(conference)) {
                page = UrlManager.getProperty(Page.CONFERENCE);
            } else {
                page = UrlManager.getProperty(Page.ERROR);
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
        Date sqlStartDate = DateWorker.formatToSqlDate(String.join(DELIMITER_SPACE, dateStart, timeStart));
        Date sqlEndDate = DateWorker.formatToSqlDate(String.join(DELIMITER_SPACE, dateEnd, timeEnd));
        Date sqlDeadline = DateWorker.formatToSqlDate(deadline);
        String[] sectionArray = request.getParameterValues(Parameter.SECTIONS);
        List<Section> sections = new ArrayList<>();
        for (String title : sectionArray) {
            Section section = new Section(title);
            sections.add(section);
        }
        return new Conference(topic, intnumber, place, sqlStartDate, sqlEndDate, sqlDeadline, sections);
    }
}
