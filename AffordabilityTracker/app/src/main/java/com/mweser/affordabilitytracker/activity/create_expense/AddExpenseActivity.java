package com.mweser.affordabilitytracker.activity.create_expense;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.activity.expense.ExpensesActivity;
import com.mweser.affordabilitytracker.activity.utils.ActivityUtils;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AddExpenseActivity extends AppCompatActivity
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
        AddExpense addExpense = new AddExpense(this, getApplicationContext(), getBaseContext());

        addExpense.initFab(R.id.fab, ExpensesActivity.class);

        // NAME, AMOUNT, FIRST_DATE, LAST_DATE, IRRELEVANCY_DATE, FREQUENCY, FREQUENCY_UNIT, RECUR_TYPE, AMOUNT_TYPE, ACCOUNT

        addExpense.initTextFields(
                R.id.txtExpenseName,
                R.id.txtExpenseAmount,
                R.id.txtExpenseStartDate,
                R.id.txtExpenseEndDate,
                R.id.txtExpenseFrequency,
                R.id.txtExpenseFreqType,
                R.id.txtExpenseAccountName);

        addExpense.schemaItemOrder(Schema.Tables.expense_events,
                Schema.expense_events.NAME,
                Schema.expense_events.AMOUNT,
                Schema.expense_events.FIRST_DATE,
                Schema.expense_events.LAST_DATE,
                Schema.expense_events.FREQUENCY,
                Schema.expense_events.FREQUENCY_UNIT,
                Schema.expense_events.ACCOUNT);

        addExpense.initToggleButtons(R.id.toggleExpense, R.id.toggleIncome);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        ActivityUtils.startActivity(getBaseContext(), this, ExpensesActivity.class);
    }

    private void onCreateSetup()
    {
        setContentView(R.layout.activity_create_expense_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
