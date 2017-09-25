package com.mweser.affordabilitytracker.activity.create_expense;

import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEventsData.ToggleButtonTypes.EXPENSE;
import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEventsData.ToggleButtonTypes.INCOME;
import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEventsData.activity;
import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEventsData.baseContext;
import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEventsData.saveFieldsToDatabase;
import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEventsData.toggleButtons;

import java.util.List;

import com.mweser.affordabilitytracker.activity.utils.ActivityUtils;
import com.mweser.affordabilitytracker.activity.expense.ExpensesActivity;

import android.view.View;
import android.widget.ToggleButton;

public class CreateExpenseEventUi
{
    static List<ToggleButton> setToggleBtnProperties()
    {
        setExpenseToggleChecked(true);

        toggleButtons.get(EXPENSE.ordinal())
                .setOnClickListener(generateExpenseListener());

        toggleButtons.get(INCOME.ordinal())
                .setOnClickListener(generateIncomeListener());

        return toggleButtons;
    }

    static void initFab(int id)
    {
        activity.findViewById(id)
                .setOnClickListener(generateFabListener(ExpensesActivity.class));
    }

    static View.OnClickListener generateExpenseListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setExpenseToggleChecked(true);
            }
        };
    }

    static View.OnClickListener generateIncomeListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setExpenseToggleChecked(false);
            }
        };
    }

    static View.OnClickListener generateFabListener(final Class<?> nextActivityClass)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveFieldsToDatabase();
                activity.finish();
                ActivityUtils.startActivity(baseContext, activity, nextActivityClass);
            }
        };
    }

    static void setExpenseToggleChecked(boolean isChecked)
    {
        toggleButtons.get(EXPENSE.ordinal())
                .setChecked(isChecked);
        toggleButtons.get(INCOME.ordinal())
                .setChecked(!isChecked);
    }
}
