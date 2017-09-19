package com.mweser.affordabilitytracker.controller;

import com.mweser.affordabilitytracker.model.data.DatabaseOperations;
import com.mweser.affordabilitytracker.model.data.schema.Schema;

import android.database.Cursor;

public class BankAccountManager
{
    public static String getBankAccountListing()
    {
        String bankAccountListing = "";
        String[] queryColumns = new String[]{Schema.BankAccountColumns.NAME};


        Cursor cursor = DatabaseOperations.performQuery(Schema.Tables.BANK_ACCOUNTS, queryColumns);

        for (int index = 0; index < cursor.getCount(); index++)
        {
            bankAccountListing += cursor.getString(0) + "\n";
            cursor.moveToNext();
        }

        return bankAccountListing;
    }

}
