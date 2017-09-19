package com.mweser.affordabilitytracker.model.data;

import com.mweser.affordabilitytracker.model.data.schema.CreationCommands.CreateTable;
import com.mweser.affordabilitytracker.model.data.schema.Schema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper
{

    private static final String TAG = Database.class.getSimpleName();

    private static Database database;

    private static final String DATABASE_NAME = "databases.db";
    private static final int DATABASE_VERSION = 1;

    public static synchronized Database getInstance(Context context)
    {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (database == null)
        {
            database = new Database(context, null);
        }
        return database;
    }

    public static void executeSQL(Context appContext, String command) {
        SQLiteDatabase db = getInstance(appContext).getWritableDatabase();
        db.execSQL(command);
    }

    private Database(Context context, SQLiteDatabase.CursorFactory factory)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(TAG, "On create called");
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
        if (version == 1)
        {
            // add some extra fields to the database without deleting existing data
            version = 2;
        }

        if (version != version)
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
