package com.mweser.affordabilitytracker.model.data.schema;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Schema
{
    private static final String SQL_TYPE = "TEXT";
    private static List<SchemaTable> schema;

    public static List<SchemaTable> populateSchema()
    {
        List<SchemaTable> schemaList = new ArrayList<>();

        schemaList.add(addTable(accounts.class));
        schemaList.add(addTable(expense_events.class));
        schemaList.add(addTable(thresholds.class));
        schemaList.add(addTable(wishlist.class));
        schemaList.add(addTable(projection.class));
        schemaList.add(addTable(points.class));
        schemaList.add(addTable(settings.class));

        return schemaList;
    }

    public enum Tables
    {
        accounts, expense_events, thresholds, wishlist, projections, points, settings
    }

    public enum accounts
    {
        NAME, TYPE, PAYMENT_DATE, STATEMENT_DATE, AMOUNT_NEXT_STATEMENT, TOTAL_AMOUNT, POINTS_BALANCE
    }

    public enum expense_events
    {
        NAME, AMOUNT, FIRST_DATE, LAST_DATE, IRRELEVANCY_DATE, FREQUENCY, FREQUENCY_TYPE, RECUR_TYPE, AMOUNT_TYPE, ACCOUNT
    }

    public enum thresholds
    {
        MINIMUM_AMOUNT, MONTHS_SAVED, FIRST_DATE, END_DATE
    }

    public enum wishlist
    {
        NAME, AMOUNT, PRIORITY, DESIRED_DATE, CALCULATED_DATE, IRRELEVANCY_DATE, FREQUENCY, FREQUENCY_TYPE, RECUR_TYPE, AMOUNT_TYPE
    }

    public enum projection
    {
        DATE, VALUE, EVENT_NAME, EVENT_VALUE, MIN_VALUE, DAYS_TO_NEXT_MIN
    }

    public enum points
    {
        DATE, VALUE, ACCOUNT_NAME
    }

    public enum settings
    {
        ACCENT_HUE, CREDIT_ALIGNED
    }

    public static List<SchemaTable> getSchema()
    {
        if (schema == null)
        {
            schema = populateSchema();
        }
        return schema;
    }

    public static <E extends Enum<?>> SchemaTable addTable(Class<E> tableEnum)
    {
        SchemaTable table = new SchemaTable(tableEnum.getSimpleName());

        for (E element : tableEnum.getEnumConstants())
        {
            table.addItem(new SchemaItem(
                    tableEnum.toString(),
                    StringUtils.lowerCase(element.toString()),
                    SQL_TYPE));
        }
        return table;
    }
}
