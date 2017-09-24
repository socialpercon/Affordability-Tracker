package com.mweser.affordabilitytracker.activity;

import static com.mweser.affordabilitytracker.activity_controller.CreateExpenseEvents.setUpFab;
import static com.mweser.affordabilitytracker.activity_controller.CreateExpenseEvents.setUpTextInputs;
import static com.mweser.affordabilitytracker.activity_controller.CreateExpenseEvents.setUpToggleButtons;
import static com.mweser.affordabilitytracker.activity_controller.CreateExpenseEvents.setContexts;

import com.mweser.affordabilitytracker.R;

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
        setUpFab(R.id.fab);

        // NAME, AMOUNT, FIRST_DATE, LAST_DATE, IRRELEVANCY_DATE, FREQUENCY, FREQUENCY_TYPE, RECUR_TYPE, AMOUNT_TYPE, ACCOUNT

        setUpTextInputs(
                R.id.txtExpenseName,
                R.id.txtExpenseAmount,
                R.id.txtExpenseStartDate,
                R.id.txtExpenseEndDate,
                R.id.txtExpenseFrequency,
                R.id.txtExpenseFreqType,
                R.id.txtExpenseAccountName);

//        setUpAccountSchemaIndicesPopulated(accounts.NAME,
//                accounts.PAYMENT_DATE,
//                accounts.STATEMENT_DATE,
//                accounts.AMOUNT_NEXT_STATEMENT,
//                accounts.TOTAL_AMOUNT,
//                accounts.POINTS_BALANCE);

        setUpToggleButtons(R.id.toggleExpense, R.id.toggleIncome);

    }

    private void onCreateSetup()
    {
        setContentView(R.layout.activity_create_expense_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContexts(this, getApplicationContext(), getBaseContext());
    }
}
