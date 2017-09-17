package com.mweser.affordabilitytracker.model.data;

import com.mweser.affordabilitytracker.model.data.Schema.BankAccountColumns;
import com.mweser.affordabilitytracker.model.data.Schema.CreditPointsProjectionColumns;
import com.mweser.affordabilitytracker.model.data.Schema.ExpenseEventColumns;
import com.mweser.affordabilitytracker.model.data.Schema.ProjectionColumns;
import com.mweser.affordabilitytracker.model.data.Schema.ThresholdColumns;
import com.mweser.affordabilitytracker.model.data.Schema.WishlistColumns;

public class CreationCommands
{
    public interface CreateTable
    {
        // @formatter:off
        String BANK_ACCOUNTS = "CREATE TABLE IF NOT EXISTS " +
                Schema.Tables.BANK_ACCOUNTS                  + " (" +
                BankAccountColumns.NAME                      + " TEXT, "+
                BankAccountColumns.CODE                      + " TEXT, "+
                BankAccountColumns.TYPE                      + " TEXT, "+
                BankAccountColumns.PAYMENT_DATE              + " TEXT, "+
                BankAccountColumns.STATEMENT_DATE            + " TEXT, "+
                BankAccountColumns.LAST_4                    + " TEXT, "+
                BankAccountColumns.AMOUNT_NEXT_STATEMENT     + " TEXT, "+
                BankAccountColumns.TOTAL_AMOUNT              + " TEXT, "+
                BankAccountColumns.POINTS_BALANCE            + " TEXT);";

        String EXPENSE_EVENTS = "CREATE TABLE IF NOT EXISTS " +
                Schema.Tables.EXPENSE_EVENTS                  + " (" +
                ExpenseEventColumns.NAME                      + " TEXT, "+
                ExpenseEventColumns.AMOUNT                    + " TEXT, "+
                ExpenseEventColumns.ACCOUNT_CODE              + " TEXT, "+
                ExpenseEventColumns.FIRST_DATE                + " TEXT, "+
                ExpenseEventColumns.LAST_DATE                 + " TEXT, "+
                ExpenseEventColumns.IRRELEVANCY_DATE          + " TEXT, "+
                ExpenseEventColumns.FREQUENCY                 + " TEXT, "+
                ExpenseEventColumns.FREQUENCY_TYPE            + " TEXT, "+
                ExpenseEventColumns.RECUR_TYPE                + " TEXT, "+
                ExpenseEventColumns.AMOUNT_TYPE               + " TEXT);";


        String THRESHOLDS = "CREATE TABLE IF NOT EXISTS " +
                Schema.Tables.THRESHOLDS                  + " (" +
                ThresholdColumns.MINIMUM_AMOUNT           + " TEXT, "+
                ThresholdColumns.MONTHS_SAVED             + " TEXT, "+
                ThresholdColumns.FIRST_DATE               + " TEXT, "+
                ThresholdColumns.END_DATE                 + " TEXT);";


        String WISHLIST = "CREATE TABLE IF NOT EXISTS " +
                Schema.Tables.WISHLIST                  + " (" +
                WishlistColumns.NAME                    + " TEXT, "+
                WishlistColumns.AMOUNT                  + " TEXT, "+
                WishlistColumns.PRIORITY                + " TEXT, "+
                WishlistColumns.ACCOUNT_CODE            + " TEXT, "+
                WishlistColumns.DESIRED_DATE            + " TEXT, "+
                WishlistColumns.CALCULATED_DATE         + " TEXT, "+
                WishlistColumns.IRRELEVANCY_DATE        + " TEXT, "+
                WishlistColumns.FREQUENCY               + " TEXT, "+
                WishlistColumns.FREQUENCY_TYPE          + " TEXT, "+
                WishlistColumns.RECUR_TYPE              + " TEXT, "+
                WishlistColumns.AMOUNT_TYPE             + " TEXT);";


        String PROJECTIONS = "CREATE TABLE IF NOT EXISTS " +
                Schema.Tables.PROJECTIONS                  + " (" +
                ProjectionColumns.DATE                     + " TEXT, "+
                ProjectionColumns.VALUE                    + " TEXT, "+
                ProjectionColumns.EVENT_NAME               + " TEXT, "+
                ProjectionColumns.EVENT_VALUE              + " TEXT, "+
                ProjectionColumns.MIN_VALUE                + " TEXT, "+
                ProjectionColumns.DAYS_TO_NEXT_MIN         + " TEXT);";


        String CREDIT_POINTS = "CREATE TABLE IF NOT EXISTS " +
                Schema.Tables.CREDIT_POINTS                  + " (" +
                CreditPointsProjectionColumns.DATE           + " TEXT, "+
                CreditPointsProjectionColumns.VALUE          + " TEXT, "+
                CreditPointsProjectionColumns.ACCOUNT_NAME   + " TEXT);";

        String SETTINGS = "CREATE TABLE IF NOT EXISTS " +
                Schema.Tables.SETTINGS                  + " (" +
                Schema.SettingsColumns.ACCENT_HUE       + " TEXT, "+
                Schema.SettingsColumns.CREDIT_ALIGNED   + " TEXT);";

        // @formatter:on
    }

}
