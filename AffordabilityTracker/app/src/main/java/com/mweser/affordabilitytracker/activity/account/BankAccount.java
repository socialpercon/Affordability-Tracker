package com.mweser.affordabilitytracker.activity.account;

import static com.mweser.affordabilitytracker.database.schema.Schema.Tables.accounts;

import com.mweser.affordabilitytracker.activity.utils.StringListActivity;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;

public class BankAccount extends StringListActivity
{
    public BankAccount(Activity activity, Context appContext, Context baseContext)
    {
        super(activity, appContext, baseContext);
    }

    @Override
    protected String queryAndDisplayList()
    {
        return queryTable(accounts, Schema.accounts.NAME, Schema.accounts.TOTAL_AMOUNT);
    }
}
