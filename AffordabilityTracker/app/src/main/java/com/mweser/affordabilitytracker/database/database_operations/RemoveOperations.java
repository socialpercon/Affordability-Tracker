package com.mweser.affordabilitytracker.database.database_operations;

import com.mweser.affordabilitytracker.database.schema.Schema;
import com.mweser.affordabilitytracker.database.schema.SchemaTable;

import android.database.sqlite.SQLiteDatabase;

public class RemoveOperations
{
    public static void dropAllTables(SQLiteDatabase db)
    {
        for (SchemaTable table : Schema.getSchema())
        {
            db.execSQL(table.drop());
        }
    }

    public static void dropTable(SQLiteDatabase db, Schema.Tables... tables)
    {
        for (SchemaTable schemaTable : Schema.getSchema())
        {
            if (tableInCollection(schemaTable, tables))
            {
                db.execSQL(schemaTable.drop());
            }
        }
    }

    private static boolean tableInCollection(SchemaTable schemaTable, Schema.Tables... tables)
    {
        for (Schema.Tables table : tables)
        {
            if (table.toString()
                    .equalsIgnoreCase(schemaTable.getTableName()))
            {
                return true;
            }
        }
        return false;
    }
}
