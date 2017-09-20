package com.mweser.affordabilitytracker.controller;

import static com.mweser.affordabilitytracker.controller.ActivityUtils.generateListOfUiElements;
import static com.mweser.affordabilitytracker.controller.CreateExpenseEvents.ToggleButtonType.INCOME;
import static com.mweser.affordabilitytracker.controller.CreateExpenseEvents.ToggleButtonTypes.EXPENSE;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.model.data.Database;
import com.mweser.affordabilitytracker.model.data.database_operations.InsertOperations;
import com.mweser.affordabilitytracker.model.data.schema.Schema;
import com.mweser.affordabilitytracker.view.ExpensesActivity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateExpenseEvents
{
    private static Activity activity;
    private static Context appContext;
    private static Context baseContext;
    private static List<String> textDataEntries;
    private static List<EditText> textInputs;
    private static List<ToggleButton> toggleButtons;

    private enum ToggleButtonTypes
    {
        EXPENSE, INCOME
    }

    private enum CreateExpenseUiElements
    {
        NAME, AMOUNT, START_DATE, END_DATE, FREQ, FREQ_TYPE, ACCOUNT
    }

    private static void saveAccountFieldsToDatabase()
    {
        textDataEntries = populateAccountFieldsList();

                String insertCommand = InsertOperations.newInsertCommand(Schema.Tables.EXPENSE_EVENTS,
                        textDataEntries,


                        );

                Database.executeSQL(appContext, insertCommand);
    }

    private static List<String> populateAccountFieldsList()
    {
        textDataEntries = new ArrayList<>();

        for (EditText text : textInputs)
        {
            textDataEntries.add(text.getText()
                    .toString());
        }

        if (toggleButtons.get(EXPENSE.ordinal())
                .isChecked())
        {
            textDataEntries.set(CreateExpenseUiElements.AMOUNT.ordinal(), "-" + textDataEntries.get(CreateExpenseUiElements.AMOUNT.ordinal()));
        }

        return textDataEntries;
    }

    public static void defineTextInputs(int... textFields)
    {
        textInputs = generateListOfUiElements(activity, textFields);
    }

    public static void defineToggleButtons(int... toggleBtns)
    {
        toggleButtons = generateListOfUiElements(activity, toggleBtns);
        setToggleBtnProperties();
    }

    private static void setExpenseToggleChecked(boolean isChecked)
    {
        toggleButtons.get(EXPENSE.ordinal())
                .setChecked(isChecked);
        toggleButtons.get(INCOME.ordinal())
                .setChecked(!isChecked);
    }

    public static List<ToggleButton> setToggleBtnProperties()
    {
        setExpenseToggleChecked(true);

        toggleButtons.get(EXPENSE.ordinal())
                .setOnClickListener(generateExpenseListener());

        toggleButtons.get(INCOME.ordinal())
                .setOnClickListener(generateIncomeListener());

        return toggleButtons;
    }

    private static View.OnClickListener generateExpenseListener()
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

    private static View.OnClickListener generateIncomeListener()
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

    public static void defineFab(int id)
    {
        activity.findViewById(id)
                .setOnClickListener(generateFabListener(ExpensesActivity.class));
    }

    private static View.OnClickListener generateFabListener(final Class<?> nextActivityClass)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveAccountFieldsToDatabase();
                activity.finish();
                ActivityUtils.startActivity(baseContext, activity, nextActivityClass);
            }
        };
    }

    public static void setContexts(Activity activity, Context appContext, Context baseContext)
    {
        CreateExpenseEvents.activity = activity;
        CreateExpenseEvents.appContext = appContext;
        CreateExpenseEvents.baseContext = baseContext;
    }
}
