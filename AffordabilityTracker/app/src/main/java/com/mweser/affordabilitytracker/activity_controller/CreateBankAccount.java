package com.mweser.affordabilitytracker.activity_controller;

import static com.mweser.affordabilitytracker.activity_controller.ActivityUtils.generateListOfUiElements;
import static com.mweser.affordabilitytracker.activity_controller.CreateBankAccount.TextFields.*;
import static com.mweser.affordabilitytracker.activity_controller.CreateBankAccount.TypeToggles.CREDIT;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountType.DEBIT;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.database.Database;
import com.mweser.affordabilitytracker.database.database_operations.InsertOperations;
import com.mweser.affordabilitytracker.database.schema.Schema;
import com.mweser.affordabilitytracker.activity.BankAccountActivity;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateBankAccount
{
    private static Activity activity;
    private static Context appContext;
    private static Context baseContext;
    private static List<Enum<?>> accountSchemaIndicesPopulated;
    private static List<String> accountFields;
    private static List<EditText> textInputs;
    private static List<ToggleButton> toggleButtons;

    enum TypeToggles
    {
        CREDIT, DEBIT
    }

    enum TextFields
    {
        NAME, PAYMENT_DATE, STATEMENT_DATE, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE, POINTS
    }

    private static void saveAccountFieldsToDatabase()
    {
        accountFields = populateAccountFieldsList();

        String insertCommand = InsertOperations.newInsertCommand(Schema.Tables.accounts.toString(),
                accountFields,
                accountSchemaIndicesPopulated);

        Database.executeSQL(appContext, insertCommand);
    }

    public static void setUpTextFields(int... textFields)
    {
        textInputs = generateListOfUiElements(activity, textFields);
    }

    public static void setUpAccountSchemaIndicesPopulated(Schema.accounts... accountSchemaIndices)
    {
        accountSchemaIndicesPopulated = new ArrayList<>();

        for (Schema.accounts index : accountSchemaIndices)
        {
            accountSchemaIndicesPopulated.add(index);
        }

        verifyCorrectEnumOrder();
    }

    private static void verifyCorrectEnumOrder()
    {
        for (int index = 0; index < accountSchemaIndicesPopulated.size(); index++)
        {
            if (!accountSchemaIndicesPopulated.get(index)
                    .toString()
                    .equals(values()[index].toString()))
            {
                Log.e("CreateBankAccount", "CreateBankAccount: Mismatch in Text Field columns");
            }
        }
    }

    public static void setUpToggleButtons(int... toggleBtns)
    {
        toggleButtons = generateListOfUiElements(activity, toggleBtns);
        setToggleBtnProperties();
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
            accountFields.add("CREDIT");
            accountSchemaIndicesPopulated.add(Schema.accounts.TYPE);
        }
        else
        {
            accountFields.add("DEBIT");
            accountSchemaIndicesPopulated.add(Schema.accounts.TYPE);
        }

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

    private static void setCreditToggleValue(boolean isChecked)
    {
        toggleButtons.get(CREDIT.ordinal())
                .setChecked(isChecked);
        toggleButtons.get(DEBIT.ordinal())
                .setChecked(!isChecked);
    }

    private static void setCreditFieldVisibilty(int visibility)
    {
        setFieldVisibility(textInputs, visibility, STATEMENT_DATE, PAYMENT_DATE, AMOUNT_NEXT_STATEMENT, POINTS);
    }

    private static void setFieldVisibility(List<EditText> list, int visibility, Enum<?>... fieldEnums)
    {
        for (Enum<?> field : fieldEnums)
        {
            list.get(field.ordinal())
                    .setVisibility(visibility);
        }
    }

    public static void setUpFab(int id)
    {
        activity.findViewById(id)
                .setOnClickListener(generateFabListener(BankAccountActivity.class));
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

    private static View.OnClickListener generateFabListener(final Class<?> nextActivityClass)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveAccountFieldsToDatabase();
                activity.finish();
                ActivityUtils.startActivity(baseContext, activity, nextActivityClass);
            }
        };
    }

    public static void setContexts(Activity activity, Context appContext, Context baseContext)
    {
        CreateBankAccount.activity = activity;
        CreateBankAccount.appContext = appContext;
        CreateBankAccount.baseContext = baseContext;
    }
}
