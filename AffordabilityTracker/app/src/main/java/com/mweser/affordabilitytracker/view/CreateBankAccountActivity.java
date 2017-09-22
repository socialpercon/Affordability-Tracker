package com.mweser.affordabilitytracker.view;

import static com.mweser.affordabilitytracker.controller.CreateBankAccount.setUpAccountSchemaIndicesPopulated;
import static com.mweser.affordabilitytracker.controller.CreateBankAccount.setUpFab;
import static com.mweser.affordabilitytracker.controller.CreateBankAccount.setUpTextFields;
import static com.mweser.affordabilitytracker.controller.CreateBankAccount.setUpToggleButtons;
import static com.mweser.affordabilitytracker.controller.CreateBankAccount.setContexts;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.model.data.schema.Schema.accounts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CreateBankAccountActivity extends AppCompatActivity
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

        setUpTextFields(R.id.txtExpenseAccountName,
                R.id.txtPaymentDate,
                R.id.txtStatementDate,
                R.id.txtDueNextStatement,
                R.id.txtTotalAmount,
                R.id.txtPoints);

        setUpAccountSchemaIndicesPopulated(accounts.NAME,
                accounts.PAYMENT_DATE,
                accounts.STATEMENT_DATE,
                accounts.AMOUNT_NEXT_STATEMENT,
                accounts.TOTAL_AMOUNT,
                accounts.POINTS_BALANCE);

        setUpToggleButtons(R.id.toggleCreditCard, R.id.toggleDebitCard);
    }

    private void onCreateSetup()
    {
        setContexts(this, getApplicationContext(), getBaseContext());

        setContentView(R.layout.activity_create_bank_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
