package com.mweser.affordabilitytracker.model.database;

public class Schema
{

    public interface BankAccountColumn
    {
        String NAME = "account_name";
        String CODE = "account_code";
        String TYPE = "account_type";
        String PAYMENT_DATE = "payment_date";
        String STATEMENT_DATE = "statement_date";
        String LAST_4 = "last_4";
        String AMOUNT_NEXT_STATEMENT = "amount_next_statement";
        String TOTAL_AMOUNT = "total_amount";
        String POINTS_BALANCE = "points_balance";
    }

    public interface ExpenseEventColumn
    {
        String NAME = "event_name";
        String AMOUNT = "amount";
        String ACCOUNT_CODE = "account_code";
        String FIRST_DATE = "first_date";
        String LAST_DATE = "last_date";
        String FREQUENCY = "frequency_value";
        String FREQUENCY_TYPE = "frequency_type";
        String RECUR_TYPE = "recur_type";
        String AMOUNT_TYPE = "amount_type";
    }

    public interface ThresholdColumns
    {
        String MINIMUM_AMOUNT = "min_amount";
        String MONTHS_SAVED = "months_saved";
        String FIRST_DATE = "first_date";
        String END_DATE = "end_date";
    }

    public interface SettingsColumn
    {
        String ACCENT_HUE = "accent_hue";
    }

}
