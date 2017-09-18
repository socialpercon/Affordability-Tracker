package com.mweser.affordabilitytracker.view;

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
                ActivityUtils.startActivity(getBaseContext(), CreateBankAccountActivity.this, BankAccountActivity.class);

            }
        });
    }
}
