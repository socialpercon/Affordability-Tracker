package com.mweser.affordabilitytracker.controller;

import static com.mweser.affordabilitytracker.controller.ActivityUtils.generateListOfUiElements;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.TYPE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TypeToggles.CREDIT;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TypeToggles.DEBIT;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.model.data.Database;
import com.mweser.affordabilitytracker.model.data.database_operations.InsertOperations;
import com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.TextFields;
import com.mweser.affordabilitytracker.model.data.schema.DynamicSchema;
import com.mweser.affordabilitytracker.view.BankAccountActivity;

import android.app.Activity;
import android.content.Context;
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

    private static void saveAccountFieldsToDatabase()
    {
        accountFields = populateAccountFieldsList();

        String insertCommand = InsertOperations.newInsertCommand(
                DynamicSchema.Tables.ACCOUNTS,
                accountFields,
                accountSchemaIndicesPopulated);

        Database.executeSQL(appContext, insertCommand);
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

    public static void defineFab(int id)
    {
        activity.findViewById(id)
                .setOnClickListener(generateFabListener(BankAccountActivity.class));
    }

    public static void defineTextFields(int... textFields)
    {
        textInputs = generateListOfUiElements(activity, textFields);
    }

    public static void defineAccountSchemaIndicesPopulated(DynamicSchema.ACCOUNTS... accountSchemaIndices)
    {
        accountSchemaIndicesPopulated = new ArrayList<>();

        for (DynamicSchema.ACCOUNTS index : accountSchemaIndices)
        {
            accountSchemaIndicesPopulated.add(index);
        }
    }

    public static void defineToggleButtons(int... toggleBtns)
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
            // TODO: 9/21/17 Evaluate what this does to data integrity
            accountFields.add(TYPE.ordinal(), "CREDIT");
        } else
        {
            accountFields.add(TYPE.ordinal(), "DEBIT");
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
        textInputs.get(TextFields.STATEMENT_DATE.ordinal())
                .setVisibility(visibility);
        textInputs.get(TextFields.PAYMENT_DATE.ordinal())
                .setVisibility(visibility);
        textInputs.get(TextFields.AMOUNT_NEXT_STATEMENT.ordinal())
                .setVisibility(visibility);
        textInputs.get(TextFields.POINTS.ordinal())
                .setVisibility(visibility);
    }

    public static void setContexts(Activity activity, Context appContext, Context baseContext)
    {
        CreateBankAccount.activity = activity;
        CreateBankAccount.appContext = appContext;
        CreateBankAccount.baseContext = baseContext;
    }
}
