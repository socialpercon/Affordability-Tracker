package com.mweser.affordabilitytracker.model.data.schema;

import com.mweser.affordabilitytracker.model.data.schema.dynamic_schema.DynamicSchema;

public class CreationCommands
{
    public interface CreateTable
    {
        // @formatter:off
        String BANK_ACCOUNTS = "CREATE TABLE IF NOT EXISTS " +
                DynamicSchema.Tables.BANK_ACCOUNTS                  + " (" +
                Schema.BankAccountColumns.NAME                      + " TEXT, "+
                Schema.BankAccountColumns.TYPE                      + " TEXT, "+
                Schema.BankAccountColumns.PAYMENT_DATE              + " TEXT, "+
                Schema.BankAccountColumns.STATEMENT_DATE            + " TEXT, "+
                Schema.BankAccountColumns.AMOUNT_NEXT_STATEMENT     + " TEXT, "+
                Schema.BankAccountColumns.TOTAL_AMOUNT              + " TEXT, "+
                Schema.BankAccountColumns.POINTS_BALANCE            + " TEXT);";

        String EXPENSE_EVENTS = "CREATE TABLE IF NOT EXISTS " +
                DynamicSchema.Tables.EXPENSE_EVENTS                  + " (" +
                Schema.ExpenseEventColumns.NAME                      + " TEXT, "+
                Schema.ExpenseEventColumns.AMOUNT                    + " TEXT, "+
                Schema.ExpenseEventColumns.FIRST_DATE                + " TEXT, "+
                Schema.ExpenseEventColumns.LAST_DATE                 + " TEXT, "+
                Schema.ExpenseEventColumns.IRRELEVANCY_DATE          + " TEXT, "+
                Schema.ExpenseEventColumns.FREQUENCY                 + " TEXT, "+
                Schema.ExpenseEventColumns.FREQUENCY_TYPE            + " TEXT, "+
                Schema.ExpenseEventColumns.RECUR_TYPE                + " TEXT, "+
                Schema.ExpenseEventColumns.AMOUNT_TYPE               + " TEXT);";


        String THRESHOLDS = "CREATE TABLE IF NOT EXISTS " +
                DynamicSchema.Tables.THRESHOLDS                  + " (" +
                Schema.ThresholdColumns.MINIMUM_AMOUNT           + " TEXT, "+
                Schema.ThresholdColumns.MONTHS_SAVED             + " TEXT, "+
                Schema.ThresholdColumns.FIRST_DATE               + " TEXT, "+
                Schema.ThresholdColumns.END_DATE                 + " TEXT);";


        String WISHLIST = "CREATE TABLE IF NOT EXISTS " +
                DynamicSchema.Tables.WISHLIST                  + " (" +
                Schema.WishlistColumns.NAME                    + " TEXT, "+
                Schema.WishlistColumns.AMOUNT                  + " TEXT, "+
                Schema.WishlistColumns.PRIORITY                + " TEXT, "+
                Schema.WishlistColumns.DESIRED_DATE            + " TEXT, "+
                Schema.WishlistColumns.CALCULATED_DATE         + " TEXT, "+
                Schema.WishlistColumns.IRRELEVANCY_DATE        + " TEXT, "+
                Schema.WishlistColumns.FREQUENCY               + " TEXT, "+
                Schema.WishlistColumns.FREQUENCY_TYPE          + " TEXT, "+
                Schema.WishlistColumns.RECUR_TYPE              + " TEXT, "+
                Schema.WishlistColumns.AMOUNT_TYPE             + " TEXT);";


        String PROJECTIONS = "CREATE TABLE IF NOT EXISTS " +
                DynamicSchema.Tables.PROJECTIONS                  + " (" +
                Schema.ProjectionColumns.DATE                     + " TEXT, "+
                Schema.ProjectionColumns.VALUE                    + " TEXT, "+
                Schema.ProjectionColumns.EVENT_NAME               + " TEXT, "+
                Schema.ProjectionColumns.EVENT_VALUE              + " TEXT, "+
                Schema.ProjectionColumns.MIN_VALUE                + " TEXT, "+
                Schema.ProjectionColumns.DAYS_TO_NEXT_MIN         + " TEXT);";


        String CREDIT_POINTS = "CREATE TABLE IF NOT EXISTS " +
                DynamicSchema.Tables.CREDIT_POINTS                  + " (" +
                Schema.CreditPointsProjectionColumns.DATE           + " TEXT, "+
                Schema.CreditPointsProjectionColumns.VALUE          + " TEXT, "+
                Schema.CreditPointsProjectionColumns.ACCOUNT_NAME   + " TEXT);";

        String SETTINGS = "CREATE TABLE IF NOT EXISTS " +
                DynamicSchema.Tables.SETTINGS                  + " (" +
                Schema.SettingsColumns.ACCENT_HUE       + " TEXT, "+
                Schema.SettingsColumns.CREDIT_ALIGNED   + " TEXT);";

        // @formatter:on
    }

}
