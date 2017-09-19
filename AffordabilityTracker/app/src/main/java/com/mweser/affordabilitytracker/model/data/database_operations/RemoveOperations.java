package com.mweser.affordabilitytracker.model.data.database_operations;

import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.BANK_ACCOUNTS;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.CREDIT_POINTS;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.EXPENSE_EVENTS;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.PROJECTIONS;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.SETTINGS;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.THRESHOLDS;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.WISHLIST;

import android.database.sqlite.SQLiteDatabase;

public class RemoveOperations
{
    public static void removeAllTables(SQLiteDatabase db)
    {

        dropTable(db, BANK_ACCOUNTS, EXPENSE_EVENTS, THRESHOLDS, WISHLIST, PROJECTIONS, CREDIT_POINTS, SETTINGS);
    }

    private static void dropTable(SQLiteDatabase db, String... tables)
    {
        for (String table : tables)
        {
            dropTable(db, table);
        }
    }

    private static void dropTable(SQLiteDatabase db, String table)
    {
        db.execSQL("DROP TABLE IF EXISTS " + table);
    }

}
