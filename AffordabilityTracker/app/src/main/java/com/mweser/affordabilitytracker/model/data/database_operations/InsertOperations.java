package com.mweser.affordabilitytracker.model.data.database_operations;

import java.util.List;

public class InsertOperations
{
    public static String newInsertCommand(String table, List<String> list, Enum<?>... values) {
        return "INSERT INTO " + table + DatabaseUtils.generateValueList(list, values);
    }


}
