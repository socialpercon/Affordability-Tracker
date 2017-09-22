package com.mweser.affordabilitytracker.model.data.database_operations;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils
{
    /**
     * Generate SQL structure of form:
     * " VALUES ('2939' AMOUNT, 'CHASE' bank_account, 'NULL' statement_date);"
     */
    public static String getValueListString(List<String> valuesList, List<Enum<?>> schemaEntries)
    {
        String valueExpression = " VALUES (";

        List<String> canonicalList = generateCanonicalList(valuesList, schemaEntries);

        for (int index = 0; index < canonicalList.size(); index++)
        {
            valueExpression += canonicalList.get(index) + schemaEntries.get(index) + ",";
        }

        return trimLastChars(valueExpression, 1) + ");";
    }

    private static List<String> generateCanonicalList(List<String> valuesList,
            List<Enum<?>> schemaEntries)
    {
        List<String> list = new ArrayList<>();
        int listSize = schemaEntries.getClass()
                .getEnumConstants().length;

        for (int index = 0; index < listSize; index++)
        {
            list.add("'NULL'");
        }

        for (int index = 0; index < listSize; index++)
        {
            for (Enum<?> entry : schemaEntries)
            {
                if (entry.ordinal() == index)
                {
                    list.set(index, "'" + valuesList.get(index) + "'");
                }
            }
        }
        return list;
    }

    public static String trimLastChars(String input, int charsToTrim)
    {
        return input.substring(0, input.length() - charsToTrim);
    }
}
