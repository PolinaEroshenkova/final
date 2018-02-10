package com.eroshenkova.conference.locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateWorker {
    private static final String DATETIME_FORMAT_SQL = "YYYY-MM-dd HH:mm";
    private static final String DATE_FORMAT_SQL = "yyyy-MM-dd";
    private static final String DATE_FORMAT_SQL_NOW = "YYYY-MM-dd";
    private static final String DATETIME_EN_FORMAT = "MM/dd/yyyy hh:mm a";
    private static final String DATETIME_RU_FORMAT = "dd.MM.yyyy HH:mm";
    private static final String DATE_EN_FORMAT = "MM/dd/yyyy";
    private static final String DATE_RU_FORMAT = "dd.MM.yyyy";

    public static String receiveNow() {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT_SQL_NOW);
        return dateTimeFormatter.format(new Date());
    }

    public static String formatToSQL(Date date) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATETIME_FORMAT_SQL);
        return dateTimeFormatter.format(date);
    }

    public static Date formatFromSQL(String date) throws ParseException {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT_SQL);
        return dateTimeFormatter.parse(date);
    }

    public static Date parseDateTimeByLocale(String date, String sessionLocale) throws ParseException {
        SimpleDateFormat dateTimeFormatter = null;
        if (sessionLocale == null || sessionLocale.equals("en_EN")) {
            dateTimeFormatter = new SimpleDateFormat(DATETIME_EN_FORMAT);
        } else if (sessionLocale.equals("ru_RU") || sessionLocale.equals("be_BE")) {
            dateTimeFormatter = new SimpleDateFormat(DATETIME_RU_FORMAT);
        }
        return dateTimeFormatter != null ? dateTimeFormatter.parse(date) : null;
    }

    public static Date parseDateByLocale(String date, String sessionLocale) throws ParseException {
        SimpleDateFormat dateTimeFormatter = null;
        if (sessionLocale == null || sessionLocale.equals("en_EN")) {
            dateTimeFormatter = new SimpleDateFormat(DATE_EN_FORMAT);
        } else if (sessionLocale.equals("ru_RU") || sessionLocale.equals("be_BE")) {
            dateTimeFormatter = new SimpleDateFormat(DATE_RU_FORMAT);
        }
        return dateTimeFormatter != null ? dateTimeFormatter.parse(date) : null;
    }

    public static String formatDateByLocale(Date date, String sessionLocale) {
        SimpleDateFormat dateTimeFormatter = null;
        if (sessionLocale == null || sessionLocale.equals("en_EN")) {
            dateTimeFormatter = new SimpleDateFormat(DATE_EN_FORMAT);
        } else if (sessionLocale.equals("ru_RU") || sessionLocale.equals("be_BE")) {
            dateTimeFormatter = new SimpleDateFormat(DATE_RU_FORMAT);
        }
        return dateTimeFormatter != null ? dateTimeFormatter.format(date) : null;
    }

    public static String formatDateTimeByLocale(Date date, String sessionLocale) {
        SimpleDateFormat dateTimeFormatter = null;
        if (sessionLocale == null || sessionLocale.equals("en_EN")) {
            dateTimeFormatter = new SimpleDateFormat(DATETIME_EN_FORMAT);
        } else if (sessionLocale.equals("ru_RU") || sessionLocale.equals("be_BE")) {
            dateTimeFormatter = new SimpleDateFormat(DATETIME_RU_FORMAT);
        }
        return dateTimeFormatter != null ? dateTimeFormatter.format(date) : null;
    }
}
