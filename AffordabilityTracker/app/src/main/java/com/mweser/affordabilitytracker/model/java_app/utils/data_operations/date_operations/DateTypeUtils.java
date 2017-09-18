package com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;

public class DateTypeUtils extends Date
{
    public static Calendar dateToCalendar(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Date stringToDate(String dateString)
    {
        return formattedStringToDate(dateString, "MM/dd/yyyy");
    }

    public static Date formattedStringToDate(String dateString, String format)
    {
        Date date = new Date();

        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
        }

        return date;
    }

    public static String dateToString(Date date)
    {
        return dateToFormattedString(date, "MM/dd/yyyy");
    }

    public static String dateToFormattedString(Date date, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String convertDateStringFormats(String dateString, String originalFormat,
            String newFormat)
    {
        String output = "";

        try
        {
            SimpleDateFormat inputFormat = new SimpleDateFormat(originalFormat);
            SimpleDateFormat outputFormat = new SimpleDateFormat(newFormat);

            Date date = inputFormat.parse(dateString);
            output = outputFormat.format(date);

        }
        catch (ParseException e)
        {
        }

        return output;

    }

    public static int compareDates(Date date1, Date date2) {
        return DateUtils.truncatedCompareTo(date1, date2, Calendar.HOUR_OF_DAY);
    }

    public static int compareDates(String dateString1, String dateString2) {

        Date date1 = DateTypeUtils.stringToDate(dateString1);
        Date date2 = DateTypeUtils.stringToDate(dateString2);

        return compareDates(date1, date2);
    }

    public static boolean isFirstDateBeforeSecond(Date date1, Date date2)
    {
        return compareDates(date1, date2) < 0;
    }

    public static boolean isFirstDateAfterSecond(Date date1, Date date2)
    {
        return compareDates(date1, date2) > 0;
    }

    public static boolean isFirstDateSameAsSecond(Date date1, Date date2)
    {
        return compareDates(date1, date2) == 0;
    }

    public static boolean isFirstDateBeforeSecond(String date1, String date2)
    {
        return compareDates(date1, date2) < 0;
    }

    public static boolean isFirstDateAfterSecond(String date1, String date2)
    {
        return compareDates(date1, date2) > 0;
    }

    public static boolean isFirstDateSameAsSecond(String date1, String date2)
    {
        return compareDates(date1, date2) == 0;
    }
}
