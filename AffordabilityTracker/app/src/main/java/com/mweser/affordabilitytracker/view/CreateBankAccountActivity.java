package com.mweser.affordabilitytracker.view;

import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.generateFabListener;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setEditTextFields;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setToggleBtnProperties;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setToggleButtons;

import java.util.List;

import com.mweser.affordabilitytracker.R;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ToggleButton;

public class CreateBankAccountActivity extends AppCompatActivity
{
    List<ToggleButton> toggleButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onCreateSetup();

        setEditTextFields(this,
                R.id.txtAccountName,
                R.id.txtPaymentDate,
                R.id.txtStatementDate,
                R.id.txtDueNextStatement,
                R.id.txtTotalAmount,
                R.id.txtPoints);

        toggleButtons = setToggleButtons(this, R.id.toggleCreditCard, R.id.toggleDebitCard);

        toggleButtons = setToggleBtnProperties();
    }

    private void onCreateSetup()
    {
        setContentView(R.layout.activity_create_bank_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(generateFabListener(getApplicationContext(),
                getBaseContext(),
                this,
                BankAccountActivity.class));
    }
}
