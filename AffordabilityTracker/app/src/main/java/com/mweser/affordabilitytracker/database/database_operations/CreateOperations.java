package com.mweser.affordabilitytracker.database.database_operations;

import com.mweser.affordabilitytracker.database.schema.Schema;
import com.mweser.affordabilitytracker.database.schema.SchemaTable;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CreateOperations
{
    public static void createAllTables(SQLiteDatabase db)
    {
        for (SchemaTable table : Schema.getSchema())
        {
            db.execSQL(table.create());
            Log.d("CreateOperations", table.create());
        }
    }
}
