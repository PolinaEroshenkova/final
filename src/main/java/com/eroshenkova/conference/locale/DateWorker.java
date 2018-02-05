package com.eroshenkova.conference.locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateWorker {
    private static final String DATE_FORMAT = "MM/dd/YYYY hh:mm";
    private static final String DATABASE_DATE_FORMAT = "YYYY-MM-dd hh:mm";
    private static final String DATE_FORMAT_NOW = "YYYY-MM-dd";

    public static Date formatToDate(String date) throws ParseException {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATABASE_DATE_FORMAT);
        return dateTimeFormatter.parse(date);
    }

    public static String formatToString(Date date) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT);
        return dateTimeFormatter.format(date);
    }

    public static String receiveNow() {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT_NOW);
        return dateTimeFormatter.format(new Date());
    }
}
