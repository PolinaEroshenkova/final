package com.eroshenkova.conference.locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util class that transform and modify date
 *
 * @author Palina Yerashenkava
 */
public class DateWorker {
    /**
     * Format of date and time that used by database
     */
    private static final String DATETIME_FORMAT_SQL = "yyyy-MM-dd HH:mm";

    /**
     * Format of date that used by database
     */
    private static final String DATE_FORMAT_SQL = "yyyy-MM-dd";

    /**
     * Format of date and time that used in english locale
     */
    private static final String DATETIME_EN_FORMAT = "MM/dd/yyyy hh:mm a";

    /**
     * Format of date and time that used in russian locale
     */
    private static final String DATETIME_RU_FORMAT = "dd.MM.yyyy HH:mm";

    /**
     * Format of date that used in english locale
     */
    private static final String DATE_EN_FORMAT = "MM/dd/yyyy";

    /**
     * Format of date that used in russian locale
     */
    private static final String DATE_RU_FORMAT = "dd.MM.yyyy";

    /**
     * Used to receive present date in sql format
     * @return string representation of present time
     */
    public static String receiveNow() {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT_SQL);
        return dateTimeFormatter.format(new Date());
    }

    /**
     * Used for format date and time to sql format
     * @param date is date object
     * @return string representation of date in sql format
     */
    public static String formatDateTimeToSQL(Date date) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATETIME_FORMAT_SQL);
        return dateTimeFormatter.format(date);
    }

    /**
     * Used for parsing String to date and time
     * @param date is string representation of date
     * @return date parsed from string
     * @throws ParseException if pattern is not correct for string
     */
    public static Date parseDateTimeToSQL(String date) throws ParseException {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATETIME_FORMAT_SQL);
        return dateTimeFormatter.parse(date);
    }

    /**
     * Used for parsing string to date from sql format
     * @param date is string representation of date
     * @return date parsed from string
     * @throws ParseException if pattern is not correct for string
     */
    public static Date parseDateFromSQL(String date) throws ParseException {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT_SQL);
        return dateTimeFormatter.parse(date);
    }

    /**
     * @param date is string representation of date
     * @param sessionLocale defines locale
     * @param isTimed defines whether string has time value or not
     * @return date object formatted from string
     * @throws ParseException if pattern is not correct for string
     */
    public static Date parseDateTimeByLocale(String date, String sessionLocale, boolean isTimed) throws ParseException {
        SimpleDateFormat dateTimeFormatter = defineFormat(sessionLocale, isTimed);
        return dateTimeFormatter != null ? dateTimeFormatter.parse(date) : null;
    }

    /**
     * @param date is string representation of date
     * @param sessionLocale defines locale
     * @param isTimed defines whether string has time value or not
     * @return string representation of date by locale
     */
    public static String formatDateTimeByLocale(Date date, String sessionLocale, boolean isTimed) {
        SimpleDateFormat dateTimeFormatter = defineFormat(sessionLocale, isTimed);
        return dateTimeFormatter != null ? dateTimeFormatter.format(date) : null;
    }

    /**
     * @param sessionLocale defines locale
     * @param isTimed defines whether string has time value or not
     * @return format string by locale
     */
    private static SimpleDateFormat defineFormat(String sessionLocale, boolean isTimed) {
        SimpleDateFormat dateTimeFormatter = null;
        if (isTimed) {
            if (sessionLocale == null || sessionLocale.equals("en_EN")) {
                dateTimeFormatter = new SimpleDateFormat(DATETIME_EN_FORMAT);
            } else if (sessionLocale.equals("ru_RU") || sessionLocale.equals("be_BE")) {
                dateTimeFormatter = new SimpleDateFormat(DATETIME_RU_FORMAT);
            }
        } else {
            if (sessionLocale == null || sessionLocale.equals("en_EN")) {
                dateTimeFormatter = new SimpleDateFormat(DATE_EN_FORMAT);
            } else if (sessionLocale.equals("ru_RU") || sessionLocale.equals("be_BE")) {
                dateTimeFormatter = new SimpleDateFormat(DATE_RU_FORMAT);
            }
        }
        return dateTimeFormatter;
    }
}
