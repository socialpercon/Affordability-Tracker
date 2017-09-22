package com.mweser.affordabilitytracker.model.data.schema;

import java.util.ArrayList;

import com.mweser.affordabilitytracker.model.data.database_operations.DatabaseUtils;

public class SchemaTable
{
    private final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS ";
    private final String DROP_COMMAND = "DROP TABLE IF EXISTS ";
    private final String INSERT_COMMAND = "INSERT INTO ";
//    private final String UPDATE_COMMAND = "CREATE TABLE IF NOT EXISTS ";

    private String tableName;
    private ArrayList<SchemaItem> tableMembers;

    public SchemaTable(String tableName)
    {
        this.tableName = tableName;
        tableMembers = new ArrayList<>();
    }

    public String drop()
    {
        return DROP_COMMAND + tableName + ";";
    }

    public String create()
    {
        String command = CREATE_COMMAND + tableName + " (";

        for (SchemaItem item : tableMembers)
        {
            command += item.getSqlIdentifier() + " " + item.getSqlTypeString() + ", ";
        }

        return DatabaseUtils.trimLastChars(command, 2) + ");";
    }

    public String insert()
    {
        return INSERT_COMMAND + tableName + ";";
    }

    public void addItem(SchemaItem item)
    {
        tableMembers.add(item);
    }

    public String getTableName()
    {
        return tableName;
    }
}
