package com.mweser.affordabilitytracker.model.data;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseOperations {
    private static final String DATABASE_NAME = "databases.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = DatabaseOperations.class.getSimpleName();
    private static Database database;

    public static Cursor performQuery(String table, String[] queryColumns) {
        SQLiteDatabase db = database.getWritableDatabase();
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

    public static void executeSQL(String command) {
        SQLiteDatabase db = database.getWritableDatabase();
        db.execSQL(command);
    }

    public static Database getDatabase(Context appContext) {
        if (database == null) {
            Log.d(TAG, "Instantiating new database");
            database = new Database(appContext, DATABASE_NAME, null, DATABASE_VERSION);
            database.getWritableDatabase();
        }

        return database;
    }

    public static String newInsertCommand(String table) {
        return "INSERT INTO " + table + " VALUES (";
    }

    public static String addLastElement(List<String> list, Enum<?> value) {
        return getFieldValue(list, value) + ");";
    }

    public static String addListElement(List<String> list, Enum<?> value) {
        return getFieldValue(list, value) + ",";
    }

    private static String getFieldValue(List<String> list, Enum<?> value) {
        String fieldValue = list.get(value.ordinal());

        if (fieldValue == "") {
            return "'NULL'";
        }

        return "'" + fieldValue + "'";
    }
}
