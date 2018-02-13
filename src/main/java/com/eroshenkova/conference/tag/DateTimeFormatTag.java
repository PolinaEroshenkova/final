package com.eroshenkova.conference.tag;

import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.locale.DateWorker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Custom tag used to reformat date by locale
 *
 * @author Palina Yerashenkava
 * @see BodyTagSupport
 */
public class DateTimeFormatTag extends BodyTagSupport {
    private static final Logger LOGGER = LogManager.getLogger(DateTimeFormatTag.class);

    /**
     * Defines attribute identifier
     */
    private AttributeType type;

    /**
     * @param type is used to define attribute tag value
     */
    public void setType(String type) {
        this.type = AttributeType.valueOf(type.toUpperCase());
    }

    /**
     * @return int value that identifies next attribute work
     * @throws JspException if data is not match the jsp
     */
    @Override
    public int doAfterBody() throws JspException {
        JspWriter out = bodyContent.getEnclosingWriter();
        BodyContent bodyContent = super.getBodyContent();
        String bodyDate = bodyContent.getString();
        try {
            Date date = DateWorker.parseDateTimeToSQL(bodyDate);
            HttpSession session = pageContext.getSession();
            String locale = (String) session.getAttribute(Parameter.LOCALE);
            String result;
            switch (type) {
                case DATE:
                    result = DateWorker.formatDateTimeByLocale(date, locale, false);
                    break;
                case DATETIME:
                    result = DateWorker.formatDateTimeByLocale(date, locale, true);
                    break;
                default:
                    LOGGER.log(Level.ERROR, "Date parsing error");
                    result = "error";
            }
            out.write(result);
        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Cannot format date");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Exception writing date on jsp ");
        }
        return SKIP_BODY;
    }
}
