package com.mweser.affordabilitytracker.model.data.database_operations;

import com.mweser.affordabilitytracker.model.data.schema.Schema;
import com.mweser.affordabilitytracker.model.data.schema.SchemaTable;

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
