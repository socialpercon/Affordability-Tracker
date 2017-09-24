package com.mweser.affordabilitytracker.activity.create_expense;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CreateExpenseEventActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onCreateSetup();
        setUpUiElements();
    }

    private void setUpUiElements()
    {
        CreateExpenseEventUi.initFab(R.id.fab);

        // NAME, AMOUNT, FIRST_DATE, LAST_DATE, IRRELEVANCY_DATE, FREQUENCY, FREQUENCY_UNIT, RECUR_TYPE, AMOUNT_TYPE, ACCOUNT

        CreateExpenseEventsData.initTextInputs(R.id.txtExpenseName,
                R.id.txtExpenseAmount,
                R.id.txtExpenseStartDate,
                R.id.txtExpenseEndDate,
                R.id.txtExpenseFrequency,
                R.id.txtExpenseFreqType,
                R.id.txtExpenseAccountName);

        CreateExpenseEventsData.schemaItemOrder(Schema.expense_events.NAME,
                Schema.expense_events.AMOUNT,
                Schema.expense_events.FIRST_DATE,
                Schema.expense_events.LAST_DATE,
                Schema.expense_events.FREQUENCY,
                Schema.expense_events.FREQUENCY_UNIT,
                Schema.expense_events.ACCOUNT);

        CreateExpenseEventsData.initToggleButtons(R.id.toggleExpense, R.id.toggleIncome);
    }

    private void onCreateSetup()
    {
        setContentView(R.layout.activity_create_expense_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CreateExpenseEventsData.setContexts(this, getApplicationContext(), getBaseContext());
    }
}
