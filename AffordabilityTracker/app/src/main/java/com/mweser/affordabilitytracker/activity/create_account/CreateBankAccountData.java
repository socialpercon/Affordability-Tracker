package com.mweser.affordabilitytracker.activity.create_account;

import static com.mweser.affordabilitytracker.activity.ActivityUtils.generateEnumArrayList;
import static com.mweser.affordabilitytracker.activity.ActivityUtils.generateListOfUiElements;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TypeToggles.CREDIT;
import static com.mweser.affordabilitytracker.activity.create_account.CreateAccountUi.generateFabListener;
import static com.mweser.affordabilitytracker.activity.create_account.CreateAccountUi.setToggleBtnProperties;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.activity.account.BankAccountActivity;
import com.mweser.affordabilitytracker.database.Database;
import com.mweser.affordabilitytracker.database.database_operations.InsertOperations;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateBankAccountData
{
    static Activity activity;
    static Context appContext;
    static Context baseContext;
    static List<Enum<?>> schemaElementsAddedOrder;
    static List<String> accountFields;
    static List<EditText> textInputs;
    static List<ToggleButton> toggleButtons;

    enum TypeToggles
    {
        CREDIT, DEBIT
    }

    enum TextFields
    {
        NAME, PAYMENT_DATE, STATEMENT_DATE, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE, POINTS
    }

    static void saveAccountFieldsToDatabase()
    {
        accountFields = populateAccountFieldsList();

        String insertCommand = InsertOperations.newInsertCommand(Schema.Tables.accounts.toString(),
                accountFields,
                schemaElementsAddedOrder);

        Database.executeSQL(appContext, insertCommand);
    }

    static List<String> populateAccountFieldsList()
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
            schemaElementsAddedOrder.add(Schema.accounts.TYPE);
        }
        else
        {
            accountFields.add("DEBIT");
            schemaElementsAddedOrder.add(Schema.accounts.TYPE);
        }

        return accountFields;
    }

    public static void schemaItemOrder(Schema.accounts... accountSchemaIndices)
    {
        schemaElementsAddedOrder = generateEnumArrayList(accountSchemaIndices);
        verifyCorrectEnumOrder();
    }

    private static void verifyCorrectEnumOrder()
    {
        for (int index = 0; index < schemaElementsAddedOrder.size(); index++)
        {
            if (!schemaElementsAddedOrder.get(index)
                    .toString()
                    .equals(TextFields.values()[index].toString()))
            {
                Log.e("CreateBankAccountData", "CreateBankAccountData: Mismatch in Text Field columns");
            }
        }
    }

    static void initTextFields(int... textFields)
    {
        textInputs = generateListOfUiElements(activity, textFields);
    }

    static void initToggleButtons(int... toggleBtns)
    {
        toggleButtons = generateListOfUiElements(activity, toggleBtns);
        setToggleBtnProperties();
    }

    static void initFab(int id)
    {
        activity.findViewById(id)
                .setOnClickListener(generateFabListener(BankAccountActivity.class));
    }

    static void setContexts(Activity activity, Context appContext, Context baseContext)
    {
        CreateBankAccountData.activity = activity;
        CreateBankAccountData.appContext = appContext;
        CreateBankAccountData.baseContext = baseContext;
    }
}
