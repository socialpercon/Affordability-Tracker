package com.mweser.affordabilitytracker.model.data.schema.dynamic_schema;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DynamicSchema
{
    private static final String SQL_TYPE = "TEXT";

    public static List<SchemaTable> populateSchema()
    {
        List<SchemaTable> schemaList = new ArrayList<>();

        schemaList.add(addTable(ACCOUNTS.class));
        schemaList.add(addTable(EXPENSE_EVENTS.class));
        schemaList.add(addTable(THRESHOLDS.class));
        schemaList.add(addTable(WISHLIST.class));
        schemaList.add(addTable(PROJECTION.class));
        schemaList.add(addTable(POINTS.class));
        schemaList.add(addTable(SETTINGS.class));

        return schemaList;
    }

    public enum SchemaTables
    {
        ACCOUNTS, EXPENSE_EVENTS, THRESHOLDS, WISHLIST, PROJECTIONS, CREDIT_POINTS, SETTINGS
    }

    public enum ACCOUNTS
    {
        NAME, TYPE, PAYMENT_DATE, STATEMENT_DATE, AMOUNT_NEXT_STATEMENT, TOTAL_AMOUNT, POINTS_BALANCE
    }

    public enum EXPENSE_EVENTS
    {
        NAME, AMOUNT, FIRST_DATE, LAST_DATE, IRRELEVANCY_DATE, FREQUENCY, FREQUENCY_TYPE, RECUR_TYPE, AMOUNT_TYPE
    }

    public enum THRESHOLDS
    {
        MINIMUM_AMOUNT, MONTHS_SAVED, FIRST_DATE, END_DATE
    }

    public enum WISHLIST
    {
        NAME, AMOUNT, PRIORITY, DESIRED_DATE, CALCULATED_DATE, IRRELEVANCY_DATE, FREQUENCY, FREQUENCY_TYPE, RECUR_TYPE, AMOUNT_TYPE
    }

    public enum PROJECTION
    {
        DATE, VALUE, EVENT_NAME, EVENT_VALUE, MIN_VALUE, DAYS_TO_NEXT_MIN
    }

    public enum POINTS
    {
        DATE, VALUE, ACCOUNT_NAME
    }

    public enum SETTINGS
    {
        ACCENT_HUE, CREDIT_ALIGNED
    }

    public static <E extends Enum<?>> SchemaTable addTable(Class<E> tableEnum)
    {
        SchemaTable table = new SchemaTable(tableEnum.toString());

        for (E element : tableEnum.getEnumConstants())
        {
            table.addItemToTableSchema(new SchemaItem(
                    tableEnum.toString(),
                    StringUtils.lowerCase(element.toString()),
                    SQL_TYPE));
        }
        return table;
    }
}
