package com.mweser.affordabilitytracker.controller;

import static com.mweser.affordabilitytracker.model.data.DatabaseOperations.addLastElement;
import static com.mweser.affordabilitytracker.model.data.DatabaseOperations.addListElement;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.AMOUNT_NEXT_STATEMENT;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.NAME;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.PAYMENT_DATE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.POINTS;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.STATEMENT_DATE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.TOTAL_BALANCE;
import static com.mweser.affordabilitytracker.model.data.schema.CreateBankUiElementSchema.AccountField.TYPE;
import static com.mweser.affordabilitytracker.model.data.schema.Schema.Tables.BANK_ACCOUNTS;

import java.util.List;

import com.mweser.affordabilitytracker.model.data.DatabaseOperations;

public class CreateBankAccountManager
{
    public static void addAccountInfoToDatabase(List<String> accountFields)
    {
        String insertCommand = DatabaseOperations.newInsertCommand(BANK_ACCOUNTS);

        insertCommand += addListElement(accountFields, NAME);
        insertCommand += addListElement(accountFields, TYPE);
        insertCommand += addListElement(accountFields, PAYMENT_DATE);
        insertCommand += addListElement(accountFields, STATEMENT_DATE);
        insertCommand += addListElement(accountFields, AMOUNT_NEXT_STATEMENT);
        insertCommand += addListElement(accountFields, TOTAL_BALANCE);
        insertCommand += addLastElement(accountFields, POINTS);

        DatabaseOperations.executeSQL(insertCommand);
    }

}
