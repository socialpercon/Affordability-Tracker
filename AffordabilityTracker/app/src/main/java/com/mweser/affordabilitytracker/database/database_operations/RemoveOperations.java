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
}
