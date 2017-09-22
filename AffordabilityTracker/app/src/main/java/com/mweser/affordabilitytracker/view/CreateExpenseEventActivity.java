package com.mweser.affordabilitytracker.view;

import static com.mweser.affordabilitytracker.controller.CreateExpenseEvents.setUpFab;
import static com.mweser.affordabilitytracker.controller.CreateExpenseEvents.setUpTextInputs;
import static com.mweser.affordabilitytracker.controller.CreateExpenseEvents.setUpToggleButtons;
import static com.mweser.affordabilitytracker.controller.CreateExpenseEvents.setContexts;

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

        setUpTextInputs(
                R.id.txtExpenseName,
                R.id.txtExpenseAmount,
                R.id.txtExpenseStartDate,
                R.id.txtExpenseEndDate,
                R.id.txtExpenseFrequency,
                R.id.txtExpenseFreqType,
                R.id.txtExpenseAccountName);

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
