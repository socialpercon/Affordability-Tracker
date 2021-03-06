package com.mweser.affordabilitytracker.activity.account;

import com.mweser.affordabilitytracker.activity.ActivityUtils;
import com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountActivity;
import com.mweser.affordabilitytracker.database.database_operations.QueryOperations;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.TextView;

public class BankAccount
{
    private static Activity activity;
    private static Context appContext;
    private static Context baseContext;

    public static void setUpAccountListText(int id)
    {
        ((TextView) activity.findViewById(id)).setText(queryBankAccountListing(appContext));
    }

    private static String queryBankAccountListing(Context appContext)
    {
        String[] queryColumns = new String[] {Schema.accounts.NAME.toString(),
                Schema.accounts.TOTAL_AMOUNT.toString()};

        return generateBankAccountListing(QueryOperations.query(appContext, Schema.Tables.accounts.toString(),
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

    public static void setUpFab(int id)
    {
        ActivityUtils.setUpActivityTransitionFab(id,
                baseContext,
                activity,
                CreateBankAccountActivity.class);
    }

    public static void setContexts(Activity activity, Context applicationContext,
            Context baseContext)
    {
        BankAccount.activity = activity;
        appContext = applicationContext;
        BankAccount.baseContext = baseContext;
    }
}
