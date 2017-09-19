package com.mweser.affordabilitytracker.view;

import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TextFields.AMOUNT_NEXT_STATEMENT;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TextFields.POINTS;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TextFields.STATEMENT_DATE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TypeToggles.CREDIT;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TypeToggles.DEBIT;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.controller.ActivityUtils;
import com.mweser.affordabilitytracker.controller.CreateBankAccountManager;
import com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TextFields;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateBankAccountActivity extends AppCompatActivity
{
    List<ToggleButton> toggleButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onCreateSetup();

        CreateBankAccountManager.setEditTextFields(this,
                R.id.txtAccountName,
                R.id.txtPaymentDate,
                R.id.txtStatementDate,
                R.id.txtDueNextStatement,
                R.id.txtTotalAmount,
                R.id.txtPoints);

        toggleButtons = CreateBankAccountManager.setToggleButtons(this,
                R.id.toggleCreditCard,
                R.id.toggleDebitCard);

        toggleButtons = CreateBankAccountManager.setToggleBtnProperties(generateCreditListener(),
                generateDebitListener());
    }

    private View.OnClickListener generateCreditListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toggleButtons.get(CREDIT.ordinal())
                        .setChecked(true);
                toggleButtons.get(DEBIT.ordinal())
                        .setChecked(false);
                showCreditFields();
            }

            private void showCreditFields()
            {
                List<EditText> textInputs = new ArrayList<>();

                textInputs.add((EditText) findViewById(R.id.txtAccountName));
                textInputs.add((EditText) findViewById(R.id.txtPaymentDate));
                textInputs.add((EditText) findViewById(R.id.txtStatementDate));
                textInputs.add((EditText) findViewById(R.id.txtDueNextStatement));
                textInputs.add((EditText) findViewById(R.id.txtTotalAmount));
                textInputs.add((EditText) findViewById(R.id.txtPoints));

                textInputs.get(STATEMENT_DATE.ordinal())
                        .setVisibility(View.VISIBLE);
                textInputs.get(TextFields.PAYMENT_DATE.ordinal())
                        .setVisibility(View.VISIBLE);
                textInputs.get(AMOUNT_NEXT_STATEMENT.ordinal())
                        .setVisibility(View.VISIBLE);
                textInputs.get(POINTS.ordinal())
                        .setVisibility(View.VISIBLE);
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
                toggleButtons.get(DEBIT.ordinal())
                        .setChecked(true);
                toggleButtons.get(CREDIT.ordinal())
                        .setChecked(false);

                hideCreditFields();
            }

            private void hideCreditFields()
            {
                List<EditText> textInputs = new ArrayList<>();

                textInputs.add((EditText) findViewById(R.id.txtAccountName));
                textInputs.add((EditText) findViewById(R.id.txtPaymentDate));
                textInputs.add((EditText) findViewById(R.id.txtStatementDate));
                textInputs.add((EditText) findViewById(R.id.txtDueNextStatement));
                textInputs.add((EditText) findViewById(R.id.txtTotalAmount));
                textInputs.add((EditText) findViewById(R.id.txtPoints));

                textInputs.get(STATEMENT_DATE.ordinal())
                        .setVisibility(View.INVISIBLE);
                textInputs.get(TextFields.PAYMENT_DATE.ordinal())
                        .setVisibility(View.INVISIBLE);
                textInputs.get(AMOUNT_NEXT_STATEMENT.ordinal())
                        .setVisibility(View.INVISIBLE);
                textInputs.get(POINTS.ordinal())
                        .setVisibility(View.INVISIBLE);
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
