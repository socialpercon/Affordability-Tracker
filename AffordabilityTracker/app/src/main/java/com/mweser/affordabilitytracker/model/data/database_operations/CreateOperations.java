package com.mweser.affordabilitytracker.model.data.database_operations;

import static com.mweser.affordabilitytracker.model.data.schema.CreationCommands.CreateTable.BANK_ACCOUNTS;
import static com.mweser.affordabilitytracker.model.data.schema.CreationCommands.CreateTable.CREDIT_POINTS;
import static com.mweser.affordabilitytracker.model.data.schema.CreationCommands.CreateTable.EXPENSE_EVENTS;
import static com.mweser.affordabilitytracker.model.data.schema.CreationCommands.CreateTable.PROJECTIONS;
import static com.mweser.affordabilitytracker.model.data.schema.CreationCommands.CreateTable.SETTINGS;
import static com.mweser.affordabilitytracker.model.data.schema.CreationCommands.CreateTable.THRESHOLDS;
import static com.mweser.affordabilitytracker.model.data.schema.CreationCommands.CreateTable.WISHLIST;

import android.database.sqlite.SQLiteDatabase;

public class CreateOperations
{
    public static void createAllTables(SQLiteDatabase db)
    {
        createTable(db, BANK_ACCOUNTS, EXPENSE_EVENTS, THRESHOLDS, WISHLIST, PROJECTIONS, CREDIT_POINTS, SETTINGS);
    }

    private static void createTable(SQLiteDatabase db, String... commands)
    {
        for (String command : commands)
        {
            createTable(db, command);
        }
    }

    private static void createTable(SQLiteDatabase db, String command)
    {
        db.execSQL(command);
    }

}
