package com.mweser.affordabilitytracker.model.java_app.projection;

public class RecurEventSchema
{
    public enum ExpenseDataColumn
    {
        START_DATE, NAME, AMOUNT, FREQ, FREQ_TYPE, RECUR_TYPE, AMOUNT_TYPE, ACCOUNT_USED
    }

    public enum AccountDataColumn
    {
        NAME, CODE, TYPE, PAYMENT_DATE, STATEMENT_DATE, LAST_4, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE
    }

    public enum FrequencyType
    {
        ONCE, DAYS, WEEKS, MONTHS, YEARS;
    }

    public enum RecurType
    {
        EXACT_DATE, DAY_OF_WEEK, WEEKDAY_BEFORE, NEXT_WEEKDAY, FLEXIBLE
    }

    public enum AmountType
    {
        EXACT, MAX, ESTIMATE
    }

    public enum AccountType
    {
        CREDIT, DEBIT, CHECKING, SAVING, RETIREMENT, HSA, LOAN, INVESTMENT
    }

}
