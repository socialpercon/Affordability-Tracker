package com.mweser.affordabilitytracker.view;

import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.defineFab;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.defineTextFields;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.defineToggleButtons;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setContexts;

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

        defineFab(R.id.fab);

        defineTextFields(R.id.txtAccountName,
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
