package com.mweser.affordabilitytracker.model.data.database_operations;

import com.mweser.affordabilitytracker.model.data.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryOperations
{

    public static Cursor query(Context appContext, String table, String[] queryColumns) {

        SQLiteDatabase db = Database.getInstance(appContext).getReadableDatabase();

        Cursor cursor = db.query(table,
                queryColumns,
                null,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        return cursor;
    }
}
