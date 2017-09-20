package com.mweser.affordabilitytracker.controller;

import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.AMOUNT_NEXT_STATEMENT;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.NAME;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.PAYMENT_DATE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.POINTS;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.STATEMENT_DATE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.TOTAL_BALANCE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.TYPE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TypeToggles.CREDIT;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TypeToggles.DEBIT;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.BANK_ACCOUNTS;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.model.data.Database;
import com.mweser.affordabilitytracker.model.data.database_operations.InsertOperations;
import com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TextFields;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateBankAccountManager
{
    private static List<String> accountFields;
    private static List<EditText> textInputs;
    private static List<ToggleButton> toggleButtons;

    public static List<EditText> setEditTextFields(Activity activity, int... textFields)
    {
        textInputs = ActivityUtils.generateListOfUiElements(activity, textFields);
        return textInputs;
    }

    public static List<ToggleButton> setToggleButtons(Activity activity, int... textFields)
    {
        toggleButtons = ActivityUtils.generateListOfUiElements(activity, textFields);
        return toggleButtons;
    }

    public static void saveAccountFieldsToDatabase(Context appContext)
    {
        accountFields = populateAccountFieldsList();

        String insertCommand = InsertOperations.newInsertCommand(BANK_ACCOUNTS,
                accountFields,
                NAME,
                TYPE,
                PAYMENT_DATE,
                STATEMENT_DATE,
                AMOUNT_NEXT_STATEMENT,
                TOTAL_BALANCE,
                POINTS);

        Database.executeSQL(appContext, insertCommand);
    }

    private static List<String> populateAccountFieldsList()
    {
        accountFields = new ArrayList<>();

        for (EditText text : textInputs)
        {
            accountFields.add(text.getText()
                    .toString());
        }

        if (toggleButtons.get(CREDIT.ordinal())
                .isChecked())
        {
            accountFields.add(TYPE.ordinal(), "CREDIT");
        }
        else
        {
            accountFields.add(TYPE.ordinal(), "DEBIT");
        }

        Log.d("CreateBankAccount", accountFields.toString());
        return accountFields;
    }

    public static List<ToggleButton> setToggleBtnProperties()
    {
        setCreditToggleValue(true);

        toggleButtons.get(CREDIT.ordinal())
                .setOnClickListener(generateCreditListener());

        toggleButtons.get(DEBIT.ordinal())
                .setOnClickListener(generateDebitListener());

        return toggleButtons;
    }

    public static void setCreditToggleValue(boolean isChecked)
    {
        toggleButtons.get(CREDIT.ordinal())
                .setChecked(isChecked);
        toggleButtons.get(DEBIT.ordinal())
                .setChecked(!isChecked);
    }

    public static void setCreditFieldVisibilty(int visibility)
    {
        textInputs.get(TextFields.STATEMENT_DATE.ordinal())
                .setVisibility(visibility);
        textInputs.get(TextFields.PAYMENT_DATE.ordinal())
                .setVisibility(visibility);
        textInputs.get(TextFields.AMOUNT_NEXT_STATEMENT.ordinal())
                .setVisibility(visibility);
        textInputs.get(TextFields.POINTS.ordinal())
                .setVisibility(visibility);
    }

    private static View.OnClickListener generateCreditListener()
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

    private static View.OnClickListener generateDebitListener()
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

    public static View.OnClickListener generateFabListener(final Context appContext,
            final Context baseContext, final Activity activity, final Class<?> nextActivity)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveAccountFieldsToDatabase(appContext);
                activity.finish();
                ActivityUtils.startActivity(baseContext, activity, nextActivity);
            }
        };
    }
}
