package com.mweser.affordabilitytracker.model.data.schema;

public class CreateBankUiElementSchema
{
    public enum AccountField
    {
        NAME, TYPE, PAYMENT_DATE, STATEMENT_DATE, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE, POINTS
    }

    public enum TextFields
    {
        NAME, PAYMENT_DATE, STATEMENT_DATE, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE, POINTS
    }

    public enum TypeToggles
    {
        CREDIT, DEBIT
    }



}
