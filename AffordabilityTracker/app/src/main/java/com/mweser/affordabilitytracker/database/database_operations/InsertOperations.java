package com.mweser.affordabilitytracker.database.database_operations;

import java.util.List;

public class InsertOperations
{
    public static String newInsertCommand(String table, List<String> dataValues, List<Enum<?>> schemaEntriesList)
    {
        return "INSERT INTO " + table + DatabaseUtils.getValueListString(dataValues, schemaEntriesList);
    }
}
