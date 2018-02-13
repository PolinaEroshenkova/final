package com.eroshenkova.conference.command.client;

import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.entity.impl.*;
import com.eroshenkova.conference.locale.DateWorker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Used to create entity objects
 *
 * @author Palina Yerashenkava
 */
public class ObjectCreator {
    /**
     * Space delimiter
     */
    private static final String DELIMITER_SPACE = " ";

    /**
     * Creates conference object
     * @param request is page request
     * @return conference object from parsed request
     * @throws ParseException if date cannot be parsed
     */
    public Conference formConferenceObject(HttpServletRequest request) throws ParseException {
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
        Date sqlStartDate = DateWorker.parseDateTimeByLocale(String.join(DELIMITER_SPACE, dateStart, timeStart), locale, true);
        Date sqlEndDate = DateWorker.parseDateTimeByLocale(String.join(DELIMITER_SPACE, dateEnd, timeEnd), locale, true);
        Date sqlDeadline = DateWorker.parseDateTimeByLocale(deadline, locale, false);
        String[] sectionArray = request.getParameterValues(Parameter.SECTIONS);
        List<Section> sections = new ArrayList<>();
        for (String title : sectionArray) {
            Section section = new Section(title);
            sections.add(section);
        }
        return new Conference(topic, intnumber, place, sqlStartDate, sqlEndDate, sqlDeadline, sections);
    }

    /**
     * Creates user object
     * @param request is page request
     * @return user object from parsed request
     */
    public User formUserObject(HttpServletRequest request) {
        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);
        String email = request.getParameter(Parameter.EMAIL);
        return new User(login, password, email);
    }

    /**
     * Creates participant object
     * @param request is page request
     * @return participant object from parsed request
     */
    public Participant formParticipantObject(HttpServletRequest request) {
        String login = request.getParameter(Parameter.LOGIN);
        String surname = request.getParameter(Parameter.SURNAME);
        String name = request.getParameter(Parameter.NAME);
        String scope = request.getParameter(Parameter.SCOPE);
        String position = request.getParameter(Parameter.POSITION);
        String company = request.getParameter(Parameter.COMPANY);
        return new Participant(login, surname, name, scope, position, company);
    }

    /**
     * Creates entry object
     * @param request is page request
     * @return entry object from parsed request
     */
    public Entry formEntryObject(HttpServletRequest request) {
        String stringIdEntry = request.getParameter(Parameter.ID);
        long idEntry = Long.parseLong(stringIdEntry);
        String status = request.getParameter(Parameter.STATUS);
        String login = request.getParameter(Parameter.LOGIN);
        return new Entry(idEntry, login, status);
    }

    /**
     * Creates question object
     * @param request is page request
     * @return question object from parsed request
     */
    public Question formQuestionObject(HttpServletRequest request) {
        String questionText = request.getParameter(Parameter.QUESTION_ADMIN);
        String answer = request.getParameter(Parameter.ANSWER_ADMIN);
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute(Parameter.USER);
        return new Question(login, questionText, answer);
    }
}
