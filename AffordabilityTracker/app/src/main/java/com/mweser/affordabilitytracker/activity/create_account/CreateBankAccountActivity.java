package com.mweser.affordabilitytracker.activity.create_account;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.activity.account.BankAccountActivity;
import com.mweser.affordabilitytracker.activity.utils.ActivityUtils;
import com.mweser.affordabilitytracker.database.schema.Schema;
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
        CreateBankAccount accountData = new CreateBankAccount(this,
                getApplicationContext(),
                getBaseContext());

        accountData.initFab(R.id.fab, BankAccountActivity.class);

        accountData.initTextFields(R.id.txtExpenseAccountName,
                R.id.txtPaymentDate,
                R.id.txtStatementDate,
                R.id.txtDueNextStatement,
                R.id.txtTotalAmount,
                R.id.txtPoints);

        accountData.schemaItemOrder(Schema.Tables.accounts,
                accounts.NAME,
                accounts.PAYMENT_DATE,
                accounts.STATEMENT_DATE,
                accounts.AMOUNT_NEXT_STATEMENT,
                accounts.TOTAL_AMOUNT,
                accounts.POINTS_BALANCE);

        accountData.initToggleButtons(R.id.toggleCreditCard, R.id.toggleDebitCard);
    }

    private void onCreateSetup()
    {
        setContentView(R.layout.activity_create_bank_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        ActivityUtils.startActivity(getBaseContext(), this, BankAccountActivity.class);

    }
}
