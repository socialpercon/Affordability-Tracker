package com.mweser.affordabilitytracker.view;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.controller.ActivityUtils;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class CreateExpenseEventActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: 9/19/17 Don't show FAB until all data fields filled in
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CreateExpenseEventActivity.this.finish();
                ActivityUtils.startActivity(getBaseContext(), CreateExpenseEventActivity.this, ExpensesActivity.class);
            }
        });
    }
}
