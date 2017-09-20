package com.mweser.affordabilitytracker.controller;

import static com.mweser.affordabilitytracker.model.data.schema.Schema.BankAccountColumns.NAME;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.BankAccountColumns.TOTAL_AMOUNT;

import com.mweser.affordabilitytracker.model.data.database_operations.QueryOperations;
import com.mweser.affordabilitytracker.model.data.schema.Schema;
import com.mweser.affordabilitytracker.view.CreateBankAccountActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.TextView;

public class BankAccountController
{
    private static Activity activity;
    private static Context appContext;
    private static Context baseContext;

    public static void defineAccountListText(int id)
    {
        ((TextView) activity.findViewById(id)).setText(queryBankAccountListing(appContext));
    }

    private static String queryBankAccountListing(Context appContext)
    {
        String[] queryColumns = new String[] {NAME, TOTAL_AMOUNT};

        return generateBankAccountListing(QueryOperations.query(appContext,
                Schema.Tables.BANK_ACCOUNTS,
                queryColumns));
    }

    private static String generateBankAccountListing(Cursor cursor)
    {
        // TODO: 9/19/17 Genericize column printing for more flexibility (use enum)
        String bankAccountListString = "";

        for (int index = 0; index < cursor.getCount(); index++)
        {
            bankAccountListString += cursor.getString(0) + ":\t\t$" + cursor.getString(1) + "\n";
            cursor.moveToNext();
        }

        return bankAccountListString;
    }

    public static void defineFab(int id)
    {
        ActivityUtils.defineActivityTransitionFab(id,
                baseContext,
                activity,
                CreateBankAccountActivity.class);
    }

    public static void setContexts(Activity activity, Context applicationContext,
            Context baseContext)
    {
        BankAccountController.activity = activity;
        appContext = applicationContext;
        BankAccountController.baseContext = baseContext;
    }
}
