package com.mweser.affordabilitytracker.model.data;

import android.content.Context;
import android.util.Log;

public class DatabaseOperations
{
    private static final String DATABASE_NAME = "databases.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TAG = DatabaseOperations.class.getSimpleName();;

    private static Database database;

    public static Database getDatabase(Context appContext)
    {
        if (database == null)
        {
            Log.d(TAG, "Instantiating new database");
            database = new Database(appContext, DATABASE_NAME, null, DATABASE_VERSION);
            database.getWritableDatabase();
        }

        return database;
    }
}
