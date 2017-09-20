package com.mweser.affordabilitytracker.view;

import static com.mweser.affordabilitytracker.controller.CreateBankAccount.defineFab;
import static com.mweser.affordabilitytracker.controller.CreateBankAccount.defineTextFields;
import static com.mweser.affordabilitytracker.controller.CreateBankAccount.defineToggleButtons;
import static com.mweser.affordabilitytracker.controller.CreateBankAccount.setContexts;

import com.mweser.affordabilitytracker.R;

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
        defineUiElements();
    }

    private void defineUiElements()
    {
        defineFab(R.id.fab);

        defineTextFields(R.id.txtExpenseAccountName,
                R.id.txtPaymentDate,
                R.id.txtStatementDate,
                R.id.txtDueNextStatement,
                R.id.txtTotalAmount,
                R.id.txtPoints);

        defineToggleButtons(R.id.toggleCreditCard, R.id.toggleDebitCard);
    }

    private void onCreateSetup()
    {
        setContentView(R.layout.activity_create_bank_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContexts(this, getApplicationContext(), getBaseContext());
    }
}
