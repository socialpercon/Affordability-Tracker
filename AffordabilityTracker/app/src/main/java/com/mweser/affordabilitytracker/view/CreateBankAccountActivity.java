package com.mweser.affordabilitytracker.view;

import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setCreditFieldVisibilty;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setCreditToggleValue;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setEditTextFields;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setToggleBtnProperties;
import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.setToggleButtons;

import java.util.List;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.controller.ActivityUtils;
import com.mweser.affordabilitytracker.controller.CreateBankAccountManager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

        toggleButtons = setToggleButtons(this,
                R.id.toggleCreditCard,
                R.id.toggleDebitCard);

        toggleButtons = setToggleBtnProperties(generateCreditListener(),
                generateDebitListener());
    }

    private View.OnClickListener generateCreditListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setCreditToggleValue(true);
                setCreditFieldVisibilty(View.VISIBLE);
            }
        };
    }

    private View.OnClickListener generateDebitListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setCreditToggleValue(false);
                setCreditFieldVisibilty(View.INVISIBLE);
            }
        };
    }

    private void onCreateSetup()
    {
        setContentView(R.layout.activity_create_bank_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                CreateBankAccountManager.saveAccountFieldsToDatabase(getApplicationContext());

                CreateBankAccountActivity.this.finish();
                ActivityUtils.startActivity(getBaseContext(),
                        CreateBankAccountActivity.this,
                        BankAccountActivity.class);
            }
        });
    }
}
