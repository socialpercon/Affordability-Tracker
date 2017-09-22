package com.mweser.affordabilitytracker.model.data.database_operations;

import java.util.List;

public class DatabaseUtils
{
    /**
     * Generates SQL structure of form:
     * " VALUES ('2939' AMOUNT, 'CHASE' bank_account, 'NULL' statement_date);"
     *
     * @param list
     * @param values
     * @return
     */
    public static String generateValueList(List<String> list, Enum<?>... values)
    {
        String valueExpression = " VALUES (";

        for (Enum<?> value : values)
        {
            valueExpression += getFieldValue(list, value) + ",";
        }

        return trimLastChars(valueExpression, 1) + ");";
    }

    private static String getFieldValue(List<String> list, Enum<?> value)
    {
        String fieldValue = list.get(value.ordinal());

        if (fieldValue == "")
        {
            return "'NULL'";
        }

        return "'" + fieldValue + "'";
    }

    public static String trimLastChars(String input, int charsToTrim)
    {
        return input.substring(0, input.length() - charsToTrim);
    }
}
