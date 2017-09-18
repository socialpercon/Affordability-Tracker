package com.mweser.affordabilitytracker.controller;

import com.mweser.affordabilitytracker.model.data.DatabaseOperations;
import com.mweser.affordabilitytracker.model.data.schema.Schema;

public class BankAccountManager
{
    public static String getBankAccountListing()
    {
        String bankAccountListing = "";

        String queryString = "";
        String[] strArray = new String[4];

        DatabaseOperations.performQuery(Schema.Tables.BANK_ACCOUNTS, queryString, strArray);

        return bankAccountListing;
    }

}
