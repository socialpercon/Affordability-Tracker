package com.mweser.affordabilitytracker.view;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.controller.ActivityUtils;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class CreateBankAccountActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onCreateSetup();
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
