package com.nta.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    static final DateTimeFormatter YYYY_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy");
    static final DateTimeFormatter MM_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM");
    static final DateTimeFormatter DD_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd");

    public static final DateTimeFormatter YYYYMMDD_NO_SEPARATOR_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter YYYYMMDD_HYPHEN_SEPARATOR_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private DateUtils() {
    }

    public static String dateTimeToString(Date date, DateTimeFormatter fmt) {
        return fmt.format(LocalDate.ofInstant(date.toInstant(), ZoneId.of("UTC")));
    }

    public static String dateToTimeString(Date date) {
        if (date == null) {
            return "";
        }
        LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.of("UTC"));
        return localDate.toString();
    }

    public static String dateToDetailTimeString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static Date increaseDate(Date startDate, int total) {

        Calendar c = Calendar.getInstance();
        c.setTime(startDate);

        c.add(Calendar.DATE, total);

        return c.getTime();
    }

    public static Date decreaseDate(Date endDate, int total) {
        total = Math.abs(total);
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, -total);
        return c.getTime();
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    public static Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static int calculateTotalDaysBetweenDates(Date startDate, Date endDate) {
        return (int) ((DateUtils.atStartOfDay(endDate).getTime() - DateUtils.atStartOfDay(startDate).getTime()) / (1000 * 60 * 60 * 24));
    }

    public static String getCurrentYear() {
        return YYYY_DATE_FORMATTER.format(LocalDate.now());
    }

    public static String getCurrentMonth() {
        return MM_DATE_FORMATTER.format(LocalDate.now());
    }

    public static String getCurrentDate() {
        return DD_DATE_FORMATTER.format(LocalDate.now());
    }


}
