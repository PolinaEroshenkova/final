package command.impl.conference;

import command.ActionCommand;
import db.dao.AbstractDAO;
import db.dao.DAOCommandEnum;
import db.dao.conference.entity.Conference;
import db.dao.conference.impl.ConferenceDAO;
import db.dao.section.entity.Section;
import db.dao.section.impl.SectionDAO;
import resource.JspRoutesManager;
import resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterConferenceCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String topic = request.getParameter("topic");
        String number = request.getParameter("number");
        Integer intnumber = Integer.parseInt(number);
        String place = request.getParameter("place");
        String dateStart = request.getParameter("date-start");
        String timeStart = request.getParameter("time-start");
        String fullStartDate = dateStart + " " + timeStart;
        String dateEnd = request.getParameter("date-end");
        String timeEnd = request.getParameter("time-end");
        String fullEndDate = dateEnd + " " + timeEnd;
        String deadline = request.getParameter("deadline");
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/YYYY hh:mm", Locale.ENGLISH);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH);
        try {
            Date sqlStartDate = dateTimeFormatter.parse(fullStartDate);
            Date sqlEndDate = dateTimeFormatter.parse(fullEndDate);
            Date sqlDeadline = dateFormatter.parse(deadline);
            Conference conference = new Conference(topic, intnumber, place, sqlStartDate, sqlEndDate, sqlDeadline);
            AbstractDAO<Long, Conference> conferenceDao = new ConferenceDAO();
            long result = conferenceDao.execute(DAOCommandEnum.CREATE, conference);
            if (result > 0) {
                AbstractDAO<Long, Section> sectionDao = new SectionDAO();
                String[] sectionArray = request.getParameterValues("sections");
                for (String title : sectionArray) {
                    Section section = new Section(result, title);
                    if (sectionDao.execute(DAOCommandEnum.CREATE, section) < 0) {
                        return JspRoutesManager.getProperty("path.page.index");
                    }
                }

            }
        } catch (ParseException e) {
            //LOGGER
        }
        return UrlManager.getProperty("url.page.conference");
    }
}
