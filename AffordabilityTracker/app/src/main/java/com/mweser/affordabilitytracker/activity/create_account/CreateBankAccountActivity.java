package com.mweser.affordabilitytracker.activity.create_account;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.database.schema.Schema.accounts;

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
        CreateBankAccountData.initFab(R.id.fab);

        CreateBankAccountData.initTextFields(
                R.id.txtExpenseAccountName,
                R.id.txtPaymentDate,
                R.id.txtStatementDate,
                R.id.txtDueNextStatement,
                R.id.txtTotalAmount,
                R.id.txtPoints);

        CreateBankAccountData.schemaItemOrder(
                accounts.NAME,
                accounts.PAYMENT_DATE,
                accounts.STATEMENT_DATE,
                accounts.AMOUNT_NEXT_STATEMENT,
                accounts.TOTAL_AMOUNT,
                accounts.POINTS_BALANCE);

        CreateBankAccountData.initToggleButtons(R.id.toggleCreditCard, R.id.toggleDebitCard);
    }

    private void onCreateSetup()
    {
        CreateBankAccountData.setContexts(this, getApplicationContext(), getBaseContext());

        setContentView(R.layout.activity_create_bank_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
