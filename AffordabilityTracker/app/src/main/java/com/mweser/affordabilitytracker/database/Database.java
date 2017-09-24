package com.mweser.affordabilitytracker.database;

import static com.mweser.affordabilitytracker.database.database_operations.CreateOperations.createAllTables;
import static com.mweser.affordabilitytracker.database.database_operations.RemoveOperations.dropAllTables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper
{
    private static Database database;

    private static final String TAG = Database.class.getSimpleName();
    private static final String DATABASE_NAME = "databases.db";
    private static final int DATABASE_VERSION = 1;

    public static synchronized Database getInstance(Context context)
    {
        if (database == null)
        {
            database = new Database(context, null);
        }
        return database;
    }

    public static void executeSQL(Context appContext, String command)
    {
        SQLiteDatabase db = getInstance(appContext).getWritableDatabase();
        db.execSQL(command);
        Log.d(TAG, "Executed: " + command);
    }

    private Database(Context context, SQLiteDatabase.CursorFactory factory)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        createAllTables(db);
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
            dropAllTables(db);
            onCreate(db);
        }
    }
}
