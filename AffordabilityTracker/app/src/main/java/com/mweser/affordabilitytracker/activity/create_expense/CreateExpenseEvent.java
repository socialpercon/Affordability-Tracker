package com.mweser.affordabilitytracker.activity.create_expense;

import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEvent.ToggleButtonTypes.EXPENSE;
import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEvent.ToggleButtonTypes.INCOME;
import static com.mweser.affordabilitytracker.activity.utils.ActivityUtils.generateListOfUiElements;

import java.util.List;

import com.mweser.affordabilitytracker.activity.utils.DataEntryActivity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ToggleButton;

public class CreateExpenseEvent extends DataEntryActivity
{
    List<ToggleButton> toggleButtons;

    enum ToggleButtonTypes
    {
        EXPENSE, INCOME
    }

    enum CreateExpenseUiElements
    {
        NAME, AMOUNT, START_DATE, END_DATE, FREQ, FREQ_TYPE, ACCOUNT
    }

    public CreateExpenseEvent(Activity activity, Context appContext, Context baseContext)
    {
        super(activity, appContext, baseContext);
    }

    public void populateAdditionalUiElements()
    {
        if (toggleButtons.get(EXPENSE.ordinal())
                .isChecked())
        {
            textFieldsToSave.set(CreateExpenseUiElements.AMOUNT.ordinal(),
                    "-" + textFieldsToSave.get(CreateExpenseUiElements.AMOUNT.ordinal()));
        }
    }

    public void initToggleButtons(int... toggleBtns)
    {
        toggleButtons = generateListOfUiElements(activity, toggleBtns);
        setToggleBtnProperties();
    }

    List<ToggleButton> setToggleBtnProperties()
    {
        setExpenseToggleChecked(true);

        toggleButtons.get(EXPENSE.ordinal())
                .setOnClickListener(generateExpenseListener());

        toggleButtons.get(INCOME.ordinal())
                .setOnClickListener(generateIncomeListener());

        return toggleButtons;
    }

    View.OnClickListener generateExpenseListener()
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

    View.OnClickListener generateIncomeListener()
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

    void setExpenseToggleChecked(boolean isChecked)
    {
        toggleButtons.get(EXPENSE.ordinal())
                .setChecked(isChecked);
        toggleButtons.get(INCOME.ordinal())
                .setChecked(!isChecked);
    }
}
