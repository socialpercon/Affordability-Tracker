package com.mweser.affordabilitytracker.model.data.schema.dynamic_schema;

import java.util.ArrayList;

import com.mweser.affordabilitytracker.model.data.database_operations.DatabaseUtils;

public class SchemaTable
{
    private final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS ";
    private String tableName;
    private ArrayList<SchemaItem> tableMembers;

    public SchemaTable(String tableName)
    {
        this.tableName = tableName;
        tableMembers = new ArrayList<>();
    }

    public String generateCreateCommand()
    {
        String command = CREATE_COMMAND + tableName + " (";

        for (SchemaItem item : tableMembers)
        {
            command += item.getSqlIdentifier() + " " + item.getSqlTypeString() + ", ";
        }

        return DatabaseUtils.trimLastChars(command, 2) + ");";
    }

    public void addItemToTableSchema(SchemaItem item)
    {
        tableMembers.add(item);
    }

    public String getTableName()
    {
        return tableName;
    }
}
