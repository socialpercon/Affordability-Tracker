package com.mweser.affordabilitytracker.activity.create_expense;

import static com.mweser.affordabilitytracker.activity.ActivityUtils.generateEnumArrayList;
import static com.mweser.affordabilitytracker.activity.ActivityUtils.generateListOfUiElements;
import static com.mweser.affordabilitytracker.activity.ActivityUtils.insertUiFieldsToDatabase;
import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEventUi.setToggleBtnProperties;
import static com.mweser.affordabilitytracker.activity.create_expense.CreateExpenseEventsData.ToggleButtonTypes.EXPENSE;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateExpenseEventsData
{
    static Activity activity;
    static Context appContext;
    static Context baseContext;
    static List<Enum<?>> schemaElementsAddedOrder;
    static List<String> textDataEntries;
    static List<EditText> textInputs;
    static List<ToggleButton> toggleButtons;

    enum ToggleButtonTypes
    {
        EXPENSE, INCOME
    }

    enum CreateExpenseUiElements
    {
        NAME, AMOUNT, START_DATE, END_DATE, FREQ, FREQ_TYPE, ACCOUNT
    }

    static void saveFieldsToDatabase()
    {
        insertUiFieldsToDatabase(appContext,
                Schema.Tables.expense_events,
                populateExpensesFieldList(),
                schemaElementsAddedOrder);
    }

    static List<String> populateExpensesFieldList()
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
            textDataEntries.set(CreateExpenseUiElements.AMOUNT.ordinal(),
                    "-" + textDataEntries.get(CreateExpenseUiElements.AMOUNT.ordinal()));
        }

        return textDataEntries;
    }

    public static void schemaItemOrder(Schema.expense_events... expenseSchemaIndices)
    {
        schemaElementsAddedOrder = generateEnumArrayList(expenseSchemaIndices);
        verifyCorrectEnumOrder();
    }

    private static void verifyCorrectEnumOrder()
    {
        for (int index = 0; index < schemaElementsAddedOrder.size(); index++)
        {
            if (!schemaElementsAddedOrder.get(index)
                    .toString()
                    .equals(CreateExpenseUiElements.values()[index].toString()))
            {
                Log.e("CreateExpense", "CreateExpense: Mismatch in Text Field columns");
            }
        }
    }

    public static void initTextInputs(int... textFields)
    {
        textInputs = generateListOfUiElements(activity, textFields);
    }

    public static void initToggleButtons(int... toggleBtns)
    {
        toggleButtons = generateListOfUiElements(activity, toggleBtns);
        setToggleBtnProperties();
    }

    public static void setContexts(Activity activity, Context appContext, Context baseContext)
    {
        CreateExpenseEventsData.activity = activity;
        CreateExpenseEventsData.appContext = appContext;
        CreateExpenseEventsData.baseContext = baseContext;
    }
}
