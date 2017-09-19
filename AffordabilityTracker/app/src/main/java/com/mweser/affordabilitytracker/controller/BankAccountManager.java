package com.mweser.affordabilitytracker.controller;

import com.mweser.affordabilitytracker.model.data.database_operations.QueryOperations;
import com.mweser.affordabilitytracker.model.data.schema.Schema;
import com.mweser.affordabilitytracker.model.data.schema.Schema.BankAccountColumns;

import android.content.Context;
import android.database.Cursor;

public class BankAccountManager
{
    public static String getBankAccountListing(Context appContext)
    {
        String bankAccountListing = "";
        String[] queryColumns = new String[]{BankAccountColumns.NAME, BankAccountColumns.TOTAL_AMOUNT};


        Cursor cursor = QueryOperations.performQuery(appContext, Schema.Tables.BANK_ACCOUNTS, queryColumns);

        for (int index = 0; index < cursor.getCount(); index++)
        {
            bankAccountListing += cursor.getString(0) + ":\t\t$" + cursor.getString(1) + "\n";
            cursor.moveToNext();
        }

        return bankAccountListing;
    }

}
