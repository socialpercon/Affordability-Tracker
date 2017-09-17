package com.mweser.affordabilitytracker.model.data;

import com.mweser.affordabilitytracker.model.data.CreationCommands.CreateTable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
    private static final String TAG = CreationCommands.class.getSimpleName();
    private static final String DATABASE_NAME = "databases.db";
    private static final int DATABASE_VERSION = 0;
    private static Cursor cursor;

    //    private static String insertDefaultSettings = "INSERT INTO settings VALUES (340, 196, 60);";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CreateTable.BANK_ACCOUNTS);
        db.execSQL(CreateTable.EXPENSE_EVENTS);
        db.execSQL(CreateTable.THRESHOLDS);
        db.execSQL(CreateTable.WISHLIST);
        db.execSQL(CreateTable.PROJECTIONS);
        db.execSQL(CreateTable.CREDIT_POINTS);
        db.execSQL(CreateTable.SETTINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO: Fix so that data isn't trashed on upgrade
        int version = oldVersion;
        if(version == 1)
        {
            // add some extra fields to the database without deleting existing data
            version = 2;

        }

        if (version != DATABASE_VERSION)
        {
            db.execSQL("DROP TABLE IF EXISTS " + Schema.Tables.BANK_ACCOUNTS);
            db.execSQL("DROP TABLE IF EXISTS " + Schema.Tables.EXPENSE_EVENTS);
            db.execSQL("DROP TABLE IF EXISTS " + Schema.Tables.THRESHOLDS);
            db.execSQL("DROP TABLE IF EXISTS " + Schema.Tables.WISHLIST);
            db.execSQL("DROP TABLE IF EXISTS " + Schema.Tables.PROJECTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + Schema.Tables.CREDIT_POINTS);
            db.execSQL("DROP TABLE IF EXISTS " + Schema.Tables.SETTINGS);
            onCreate(db);
        }
    }
}
