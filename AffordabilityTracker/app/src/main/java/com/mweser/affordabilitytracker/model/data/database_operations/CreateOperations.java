package com.mweser.affordabilitytracker.model.data.database_operations;

import com.mweser.affordabilitytracker.model.data.schema.dynamic_schema.DynamicSchema;
import com.mweser.affordabilitytracker.model.data.schema.dynamic_schema.SchemaTable;

import android.database.sqlite.SQLiteDatabase;

public class CreateOperations
{
    public static void createAllTables(SQLiteDatabase db)
    {
        for (SchemaTable table : DynamicSchema.getSchema())
        {
            db.execSQL(table.create());
        }
    }
}
