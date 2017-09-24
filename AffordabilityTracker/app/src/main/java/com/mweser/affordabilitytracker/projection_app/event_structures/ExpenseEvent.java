package com.mweser.affordabilitytracker.projection_app.event_structures;

import java.util.ArrayList;
import java.util.Date;

import com.mweser.affordabilitytracker.database.schema.Schema;
import com.mweser.affordabilitytracker.projection_app.data_operations.DateConversionUtils;
import com.mweser.affordabilitytracker.projection_app.data_operations.DateOperations;
import com.mweser.affordabilitytracker.projection_app.data_operations.datatable_structures.DataRow;

public class ExpenseEvent extends RecurEvent
{
    public ExpenseEvent(DataRow row, Date globalStartDate, Date globalEndDate,
            boolean isCreditAligned, ArrayList<AccountEvent> accountsList)
    {
        super(row, globalStartDate, globalEndDate, isCreditAligned, accountsList);
    }

    protected void importAndProcessDataFromRow(DataRow row)
    {
        importFields(row);
        advanceFirstPaymentDateToAfterGlobalStart();
        alignStartDateWithCredit();
    }

    private void findFirstPaymentDate(AccountEvent account)
    {
        Date nextStatementDate = account.getStatementDate();
        Date nextPaymentDate = account.getFirstEventDate();

        while (!DateConversionUtils.isFirstDateBeforeSecond(firstEventDate, nextStatementDate))
        {
            nextStatementDate = DateOperations.incrementDate(nextStatementDate,
                    account.freqType,
                    account.frequency);
        }

        firstEventDate = nextStatementDate;

        while (!DateConversionUtils.isFirstDateBeforeSecond(firstEventDate, nextPaymentDate))
        {
            nextPaymentDate = DateOperations.incrementDate(nextPaymentDate,
                    account.freqType,
                    account.frequency);
        }

        firstEventDate = nextPaymentDate;
    }

    private void alignStartDateWithCredit()
    {
        if (isCreditAligned)
        {
            for (AccountEvent account : accountsList)
            {
                if (account.getAccountCode()
                        .equals(accountCode) && account.getAccountType()
                        .equals(RecurEventValueTypes.AccountType.CREDIT))
                {
                    outputEventStatusLog(account);
                    findFirstPaymentDate(account);
                }
            }
        }
    }

    private void advanceFirstPaymentDateToAfterGlobalStart()
    {
        while (DateConversionUtils.isFirstDateBeforeSecond(firstEventDate, globalStartDate))
        {
            firstEventDate = DateOperations.incrementDate(firstEventDate, freqType, frequency);
        }
    }

    // TODO: 9/24/17 Will change with new way of inputting data
    private void importFields(DataRow row)
    {
        firstEventDate = DateConversionUtils.stringToDate(row.get(Schema.expense_events.FIRST_DATE));
        name = row.get(Schema.expense_events.NAME);
        amount = Double.parseDouble(row.get(Schema.expense_events.AMOUNT));
        frequency = Integer.parseInt(row.get(Schema.expense_events.FREQUENCY));
        freqType = RecurEventValueTypes.FrequencyType.valueOf(row.get(Schema.expense_events.FREQUENCY_UNIT));
        recurType = RecurEventValueTypes.RecurType.valueOf(row.get(Schema.expense_events.RECUR_TYPE));
        amountType = RecurEventValueTypes.AmountType.valueOf(row.get(Schema.expense_events.AMOUNT_TYPE));
        accountCode = row.get(Schema.expense_events.ACCOUNT);
    }

    private void outputEventStatusLog(AccountEvent account)
    {
        // TODO: 9/13/17 Remove when done debugging
        // TODO: 9/14/17 Write unit tests to cover statement/payment dates
        
        //        System.out.println("\nExpense event: " + name);
        //        System.out.println("Account for expense event: " + accountCode);
        //        System.out.println(
        //                "Original first date for expense: " + DateConversionUtils.dateToString(firstEventDate));
        //        System.out.println("Account statement date: " + account.getStatementDateString());
        //        System.out.println("Account payment date: " + account.getFirstDateString());
    }
}
