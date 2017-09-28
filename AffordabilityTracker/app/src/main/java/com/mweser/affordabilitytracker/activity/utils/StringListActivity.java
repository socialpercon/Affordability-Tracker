package com.mweser.affordabilitytracker.activity.utils;

import com.mweser.affordabilitytracker.database.database_operations.QueryOperations;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

public abstract class StringListActivity
{
    protected Activity activity;
    protected Context appContext;
    protected Context baseContext;

    public StringListActivity(Activity activity, Context appContext, Context baseContext)
    {
        this.activity = activity;
        this.appContext = appContext;
        this.baseContext = baseContext;
    }

    protected abstract String queryAndDisplayList();

    public void setUpAccountListText(int id)
    {
        ((TextView) activity.findViewById(id)).setText(queryAndDisplayList());
    }

    protected String queryTable(Schema.Tables table, Enum<?>... columns)
    {
        String[] queryColumns = generateStringArrayFromEnums(columns);

        return generateStringListing(QueryOperations.query(appContext,
                table.toString(),
                queryColumns));
    }

    protected String[] generateStringArrayFromEnums(Enum<?>... columns)
    {
        String[] strArray = new String[columns.length];

        for (int index = 0; index < strArray.length; index++)
        {
            strArray[index] = columns[index].toString();
        }

        return strArray;
    }

    protected String generateStringListing(Cursor cursor)
    {
        String outputListString = "";
        int cursorColumns = cursor.getColumnCount();

        for (int index = 0; index < cursor.getCount(); index++)
        {
            for (int col = 0; col < cursorColumns - 1; col++)
            {
                outputListString += cursor.getString(col)+ ",\t\t";
            }

            outputListString += cursor.getString(cursorColumns - 1) + "\n";
            cursor.moveToNext();
        }

        return outputListString;
    }

    public void initFab(int id, Class<?> nextClass)
    {
        activity.findViewById(id)
                .setOnClickListener(generateFabListener(nextClass));
    }

    protected View.OnClickListener generateFabListener(final Class<?> nextActivityClass)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                activity.finish();
                ActivityUtils.startActivity(baseContext, activity, nextActivityClass);
            }
        };
    }
}
