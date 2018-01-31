package com.eroshenkova.conference.locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateWorker {
    private static final String DATE_FORMAT = "MM/dd/YYYY hh:mm";

    public static Date formatToSqlDate(String date) throws ParseException {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateTimeFormatter.parse(date);
    }

    public static String formatToString(Date date) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateTimeFormatter.format(date);
    }

    public static String receiveNow() {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateTimeFormatter.format(new Date());
    }
}
