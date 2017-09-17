package com.mweser.affordabilitytracker.model.data;

public class Schema
{
    // TODO: 9/17/17 Consider adding blob to accounts/expenses as list of all future events (calculated)

    public interface Tables
    {
        String BANK_ACCOUNTS = "bank_accounts";
        String EXPENSE_EVENTS = "expense_events";
        String THRESHOLDS = "thresholds";
        String WISHLIST = "wishlist";
        String PROJECTIONS = "projections";
        String CREDIT_POINTS = "credit_points";
        String SETTINGS = "settings";
    }

    public interface BankAccountColumns
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

    public interface ExpenseEventColumns
    {
        String NAME = "event_name";
        String AMOUNT = "amount";
        String ACCOUNT_CODE = "account_code";
        String FIRST_DATE = "first_date";
        String LAST_DATE = "last_date";
        String IRRELEVANCY_DATE = "irrelevancy_date";
        String FREQUENCY = "frequency_value";
        String FREQUENCY_TYPE = "frequency_type";
        String RECUR_TYPE = "recur_type";
        String AMOUNT_TYPE = "amount_type";
    }

    // TODO: 9/17/17 Allow certain recurring items to be considered "thresholds" (e.g. emergency car repair expense every 6 months)
    public interface ThresholdColumns
    {
        String MINIMUM_AMOUNT = "min_amount";
        String MONTHS_SAVED = "months_saved";
        String FIRST_DATE = "first_date";
        String END_DATE = "end_date";
    }

    public interface WishlistColumns
    {
        String NAME = "event_name";
        String AMOUNT = "amount";
        String PRIORITY = "priority";
        String ACCOUNT_CODE = "account_code";
        String DESIRED_DATE = "desired_date";
        String CALCULATED_DATE = "calculated_date";
        String IRRELEVANCY_DATE = "last_date";
        String FREQUENCY = "frequency_value";
        String FREQUENCY_TYPE = "frequency_type";
        String RECUR_TYPE = "recur_type";
        String AMOUNT_TYPE = "amount_type";
    }

    public interface ProjectionColumns
    {
        String DATE = "date";
        String VALUE = "value";
        String EVENT_NAME = "event_name";
        String EVENT_VALUE = "event_value";
        String MIN_VALUE = "min_value";
        String DAYS_TO_NEXT_MIN = "days_to_next_min";
    }

    public interface CreditPointsProjectionColumns
    {
        String DATE = "date";
        String VALUE = "value";
        String ACCOUNT_NAME = "account_name";
    }

    public interface SettingsColumns
    {
        String ACCENT_HUE = "accent_hue";
        String CREDIT_ALIGNED = "credit_aligned";
    }
}
