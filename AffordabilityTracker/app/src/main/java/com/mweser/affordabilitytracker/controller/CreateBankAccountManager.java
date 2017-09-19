package com.mweser.affordabilitytracker.controller;

import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.AMOUNT_NEXT_STATEMENT;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.NAME;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.PAYMENT_DATE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.POINTS;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.STATEMENT_DATE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.TOTAL_BALANCE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.TYPE;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.BANK_ACCOUNTS;

import java.util.List;

import com.mweser.affordabilitytracker.model.data.Database;
import com.mweser.affordabilitytracker.model.data.database_operations.InsertOperations;

import android.content.Context;

public class CreateBankAccountManager
{

    public static void addAccountInfoToDatabase(Context appContext, List<String> accountFields)
    {

        String insertCommand = InsertOperations.newInsertCommand(
                BANK_ACCOUNTS,
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
}
