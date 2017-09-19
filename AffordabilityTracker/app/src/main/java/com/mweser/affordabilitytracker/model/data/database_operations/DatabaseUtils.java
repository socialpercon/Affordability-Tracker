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

        return trimLastChar(valueExpression) + ");";
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

    private static String trimLastChar(String input)
    {
        return input.substring(0, input.length() - 1);
    }
}
