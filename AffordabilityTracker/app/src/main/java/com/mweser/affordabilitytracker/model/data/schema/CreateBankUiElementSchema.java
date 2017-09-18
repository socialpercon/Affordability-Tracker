package com.mweser.affordabilitytracker.model.data.schema;

public class CreateBankUiElementSchema
{
    public enum AccountField
    {
        NAME, CODE, TYPE, PAYMENT_DATE, STATEMENT_DATE, LAST_4, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE, POINTS
    }

    public enum TextFields
    {
        NAME, CODE, PAYMENT_DATE, STATEMENT_DATE, LAST_4, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE, POINTS
    }

    public enum TypeToggles
    {
        CREDIT, DEBIT
    }



}
