package com.mweser.affordabilitytracker.database.database_operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DatabaseUtils
{
    /**
     * Generate SQL structure of form:
     * " VALUES ('2939' AMOUNT, 'CHASE' bank_account, 'NULL' statement_date);"
     */
    public static String getValueListString(List<String> valuesList, List<Enum<?>> schemaEntries)
    {
        String valueExpression = generateListOfEntryHeaders(schemaEntries);

        valueExpression += "\nVALUES (";

        List<String> canonicalList = generateCanonicalList(valuesList, schemaEntries);

        for (int index = 0; index < canonicalList.size(); index++)
        {
            valueExpression += canonicalList.get(index) + ", ";
        }

        return trimLastChars(valueExpression, 2) + ");";
    }

    private static String generateListOfEntryHeaders(List<Enum<?>> schemaEntries)
    {
        String headerList = " (";

        Enum<?>[] enumType = schemaEntries.get(0).getClass()
                .getEnumConstants();

        for (Enum<?> element : enumType)
        {
            headerList += StringUtils.lowerCase(element.name()) + ",";
        }

        return trimLastChars(headerList, 1) + ")";
    }

    private static List<String> generateCanonicalList(List<String> valuesList,
            List<Enum<?>> schemaEntries)
    {
        List<String> list = new ArrayList<>();
        int listSize = schemaEntries.get(0).getClass()
                .getEnumConstants().length;

        for (int index = 0; index < listSize; index++)
        {
            list.add("'NULL'");
        }

        for (int index = 0; index < valuesList.size(); index++)
        {
            String incomingValue = valuesList.get(index);
            Enum<?> incomingEnumValue = schemaEntries.get(index);

            incomingValue = "'" + incomingValue + "'";

            list.set(incomingEnumValue.ordinal(), incomingValue);
        }
        return list;
    }

    public static String trimLastChars(String input, int charsToTrim)
    {
        return input.substring(0, input.length() - charsToTrim);
    }
}
