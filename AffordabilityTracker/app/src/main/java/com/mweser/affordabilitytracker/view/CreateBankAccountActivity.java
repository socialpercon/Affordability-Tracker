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

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateBankAccountActivity extends AppCompatActivity
{
    List<String> accountFields;
    List<EditText> textInputs;
    List<ToggleButton> toggleButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onCreateSetup();
        instantiateClassFields();

        defineUiElements();
        convertFieldsToStringArrayList();
        sendStringsToManagerForDatabase();
    }

    private void sendStringsToManagerForDatabase()
    {
    }

    private void convertFieldsToStringArrayList()
    {
    }

    private void defineUiElements()
    {
        // TODO: 9/17/17 Shift to binding technique when java app fully imported
        populateEditTextList();
        defineToggleButtons();
    }

    private void defineToggleButtons()
    {
        toggleButtons.add((ToggleButton) findViewById(R.id.toggleCreditCard));
        toggleButtons.add((ToggleButton) findViewById(R.id.toggleDebitCard));

        toggleButtons.get(CREDIT.ordinal()).setChecked(true);
        toggleButtons.get(DEBIT.ordinal()).setChecked(false);

        toggleButtons.get(CREDIT.ordinal()).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toggleButtons.get(CREDIT.ordinal()).setChecked(true);
                toggleButtons.get(DEBIT.ordinal()).setChecked(false);
                showCreditFields();
            }

            private void showCreditFields()
            {
                List<EditText> textInputs = new ArrayList<>();

                textInputs.add((EditText) findViewById(R.id.txtAccountName));
                textInputs.add((EditText) findViewById(R.id.txtAccountCode));
                textInputs.add((EditText) findViewById(R.id.txtPaymentDate));
                textInputs.add((EditText) findViewById(R.id.txtStatementDate));
                textInputs.add((EditText) findViewById(R.id.txtLast4));
                textInputs.add((EditText) findViewById(R.id.txtDueNextStatement));
                textInputs.add((EditText) findViewById(R.id.txtTotalAmount));
                textInputs.add((EditText) findViewById(R.id.txtPoints));

                textInputs.get(STATEMENT_DATE.ordinal()).setVisibility(View.VISIBLE);
                textInputs.get(AMOUNT_NEXT_STATEMENT.ordinal()).setVisibility(View.VISIBLE);
                textInputs.get(POINTS.ordinal()).setVisibility(View.VISIBLE);

            }
        });

        toggleButtons.get(DEBIT.ordinal()).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toggleButtons.get(DEBIT.ordinal()).setChecked(true);
                toggleButtons.get(CREDIT.ordinal()).setChecked(false);

                hideCreditFields();
            }

            private void hideCreditFields()
            {
                List<EditText> textInputs = new ArrayList<>();

                textInputs.add((EditText) findViewById(R.id.txtAccountName));
                textInputs.add((EditText) findViewById(R.id.txtAccountCode));
                textInputs.add((EditText) findViewById(R.id.txtPaymentDate));
                textInputs.add((EditText) findViewById(R.id.txtStatementDate));
                textInputs.add((EditText) findViewById(R.id.txtLast4));
                textInputs.add((EditText) findViewById(R.id.txtDueNextStatement));
                textInputs.add((EditText) findViewById(R.id.txtTotalAmount));
                textInputs.add((EditText) findViewById(R.id.txtPoints));

                textInputs.get(STATEMENT_DATE.ordinal()).setVisibility(View.INVISIBLE);
                textInputs.get(AMOUNT_NEXT_STATEMENT.ordinal()).setVisibility(View.INVISIBLE);
                textInputs.get(POINTS.ordinal()).setVisibility(View.INVISIBLE);

            }
        });
    }



    private void populateEditTextList()
    {
        textInputs.add((EditText) findViewById(R.id.txtAccountName));
        textInputs.add((EditText) findViewById(R.id.txtAccountCode));
        textInputs.add((EditText) findViewById(R.id.txtPaymentDate));
        textInputs.add((EditText) findViewById(R.id.txtStatementDate));
        textInputs.add((EditText) findViewById(R.id.txtLast4));
        textInputs.add((EditText) findViewById(R.id.txtDueNextStatement));
        textInputs.add((EditText) findViewById(R.id.txtTotalAmount));
        textInputs.add((EditText) findViewById(R.id.txtPoints));
    }

    private void instantiateClassFields()
    {
        textInputs = new ArrayList<>();
        toggleButtons = new ArrayList<>();
        accountFields = new ArrayList<>();
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
                CreateBankAccountActivity.this.finish();
                ActivityUtils.startActivity(getBaseContext(),
                        CreateBankAccountActivity.this,
                        BankAccountActivity.class);
            }
        });
    }
}
