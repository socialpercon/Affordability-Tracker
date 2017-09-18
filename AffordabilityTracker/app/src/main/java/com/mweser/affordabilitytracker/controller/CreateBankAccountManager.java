package com.mweser.affordabilitytracker.controller;

public class CreateBankAccountManager
{
    public enum AccountField
    {
        NAME, CODE, TYPE, PAYMENT_DATE, STATEMENT_DATE, LAST_4, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE, POINTS
    }

    public static void addAccountInfoToDatabase()
    {

    }

    public static boolean validateDataFormats()
    {
        return true;
    }
}
