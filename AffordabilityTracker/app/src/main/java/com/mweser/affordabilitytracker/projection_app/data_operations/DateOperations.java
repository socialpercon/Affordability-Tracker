package com.mweser.affordabilitytracker.projection_app.data_operations;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;

import com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes;
import com.mweser.affordabilitytracker.projection_app.data_operations.datatable_structures.Data;

public class DateOperations {

    public static Data convertDateArrayToLongArray(Data data, int dateColumn) {
        String startDate = data.getValueAt(0, dateColumn);
        data.setValueAt(0, dateColumn, "0");

        for (int i = 1; i < data.size(); i++) {
            data.setValueAt(i,
                    dateColumn,
                    daysBetweenDates(startDate, data.getValueAt(i, dateColumn)));
        }

        return data;
    }

    public static Data convertLongArrayToDateArray(Data data, int dateColumn, String dateString) {
        data.setValueAt(0, dateColumn, dateString);
        Date date = DateConversionUtils.stringToDate(dateString);

        for (int rowIndex = 1; rowIndex < data.size(); rowIndex++) {
            date = incrementDate(date, RecurEventValueTypes.FrequencyType.DAYS, 1);

            data.setValueAt(rowIndex, dateColumn, DateConversionUtils.dateToString(date));
        }

        return data;
    }

    public static Data convertMonthYearArrayToLongArray(Data monthYearArray, String startDate,
            int dateColumn) {
        String monthYearDate;

        for (int i = 1; i < monthYearArray.size(); i++) {
            monthYearDate = monthYearArray.getValueAt(i, dateColumn);
            monthYearDate = DateConversionUtils.convertDateStringFormats(monthYearDate,
                    "MMM yyyy",
                    "MM/dd/yyyy");
            monthYearDate = daysBetweenDates(startDate, monthYearDate);

            monthYearArray.setValueAt(i, dateColumn, monthYearDate);
        }

        monthYearArray.remove(0);
        return monthYearArray;
    }

    public static int numDaysBetweenDates(String startDateString, String endDateString) {
        return Integer.parseInt(daysBetweenDates(startDateString, endDateString));
    }

    public static String daysBetweenDatesFormatted(String startDateString, String endDateString,
            String format) {
        Date startDate = DateConversionUtils.formattedStringToDate(startDateString, format);
        Date endDate = DateConversionUtils.formattedStringToDate(endDateString, format);

        long diff = endDate.getTime() - startDate.getTime();

        return Long.toString(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

    public static String daysBetweenDates(String startDateString, String endDateString) {
        return daysBetweenDatesFormatted(startDateString, endDateString, "MM/dd/yyyy");
    }

    public static Date incrementDate(Date date, RecurEventValueTypes.FrequencyType frequencyType,
            int frequency) {
        switch (frequencyType) {
        case DAYS: {
            return DateUtils.addDays(date, frequency);
        }
        case WEEKS: {
            return DateUtils.addWeeks(date, frequency);
        }
        case MONTHS: {
            return DateUtils.addMonths(date, frequency);
        }
        case YEARS: {
            return DateUtils.addYears(date, frequency);
        }
        default:
            return DateUtils.addDays(date, 1);
        }
    }

    public static Date decrementDate(Date date, RecurEventValueTypes.FrequencyType frequencyType,
            int frequency) {
        frequency = -1 * frequency;

        switch (frequencyType) {
        case DAYS: {
            return DateUtils.addDays(date, frequency);
        }
        case WEEKS: {
            return DateUtils.addWeeks(date, frequency);
        }
        case MONTHS: {
            return DateUtils.addMonths(date, frequency);
        }
        case YEARS: {
            return DateUtils.addYears(date, frequency);
        }
        default:
            return DateUtils.addDays(date, 1);
        }
    }

    public static int[] getCurrentDate() {
        int[] dateArray = new int[3];

        Calendar calendar = Calendar.getInstance();

        dateArray[0] = calendar.get(Calendar.YEAR);
        dateArray[1] = calendar.get(Calendar.MONTH) + 1;
        dateArray[2] = calendar.get(Calendar.DAY_OF_MONTH);

        return dateArray;
    }

    public static String getCurrentDateString() {
        int[] dateArray = getCurrentDate();
        return dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
    }

}