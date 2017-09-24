package com.mweser.affordabilitytracker.projection_app.event_structures;

public class RecurEventValueTypes
{
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
