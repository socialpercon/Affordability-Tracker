package com.mweser.affordabilitytracker.activity.expense;

import static com.mweser.affordabilitytracker.database.schema.Schema.Tables.expense_events;

import com.mweser.affordabilitytracker.activity.utils.StringListActivity;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;

public class ExpenseEvents extends StringListActivity
{
    public ExpenseEvents(Activity activity, Context appContext, Context baseContext)
    {
        super(activity, appContext, baseContext);
    }

    @Override
    protected String queryAndDisplayList()
    {
        return queryTable(expense_events,
                Schema.expense_events.NAME,
                Schema.expense_events.AMOUNT,
                Schema.expense_events.ACCOUNT);
    }
}
