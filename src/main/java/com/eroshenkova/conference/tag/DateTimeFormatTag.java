package com.eroshenkova.conference.tag;

import com.eroshenkova.conference.constant.Parameter;
import com.eroshenkova.conference.locale.DateWorker;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class DateTimeFormatTag extends BodyTagSupport {
    private AttributeType type;

    public void setType(String type) {
        this.type = AttributeType.valueOf(type.toUpperCase());
    }

    @Override
    public int doAfterBody() throws JspException {
        JspWriter out = bodyContent.getEnclosingWriter();
        BodyContent bodyContent = super.getBodyContent();
        String bodyDate = bodyContent.getString();
        try {
            Date date = DateWorker.formatFromSQL(bodyDate);
            HttpSession session = pageContext.getSession();
            String locale = (String) session.getAttribute(Parameter.LOCALE);
            String result = "";
            switch (type) {
                case DATE:
                    result = DateWorker.formatDateByLocale(date, locale);
                    break;
                case DATETIME:
                    result = DateWorker.formatDateTimeByLocale(date, locale);
                    break;
                default: //BLA
            }
            out.write(result);
        } catch (ParseException e) {
            //BLA_BLA
        } catch (IOException e) {
            //BLA-BLA
        }
        return SKIP_BODY;
    }

//    @Override
//    public int doStartTag() throws JspException {
//        GregorianCalendar gc = new GregorianCalendar();
//        String time = "<hr/>Time : <b> " + gc.getTime() + " </b><hr/>";
//        String locale = "Locale : <b> " + Locale.getDefault() + " </b><hr/> ";
//        try {
//            JspWriter out = pageContext.getOut();
//            out.write(time + locale);
//        } catch (IOException e) {
//            throw new JspException(e.getMessage());
//        }
//        return SKIP_BODY;
//    }
//
//    @Override
//    public int doEndTag() throws JspException {
//        return EVAL_PAGE;
//    }
}
