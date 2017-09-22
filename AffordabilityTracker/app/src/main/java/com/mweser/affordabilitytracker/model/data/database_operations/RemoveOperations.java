package com.mweser.affordabilitytracker.model.data.database_operations;

import com.mweser.affordabilitytracker.model.data.schema.Schema;
import com.mweser.affordabilitytracker.model.data.schema.SchemaTable;

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
