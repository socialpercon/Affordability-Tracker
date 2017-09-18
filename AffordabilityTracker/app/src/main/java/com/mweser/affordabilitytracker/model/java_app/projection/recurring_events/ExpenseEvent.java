package com.mweser.affordabilitytracker.model.java_app.projection.recurring_events;

import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.ExpenseDataColumn.ACCOUNT_USED;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.ExpenseDataColumn.AMOUNT;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.ExpenseDataColumn.AMOUNT_TYPE;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.ExpenseDataColumn.FREQ;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.ExpenseDataColumn.FREQ_TYPE;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.ExpenseDataColumn.NAME;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.ExpenseDataColumn.RECUR_TYPE;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.ExpenseDataColumn.START_DATE;

import java.util.ArrayList;
import java.util.Date;

import com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateOperations;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateTypeUtils;
import com.mweser.affordabilitytracker.model.java_app.utils.structures.DataRow;

public class ExpenseEvent extends RecurEvent
{
    public ExpenseEvent(DataRow row, Date globalStartDate, Date globalEndDate,
            boolean isCreditAligned, ArrayList<BankAccount> accountsList)
    {
        super(row, globalStartDate, globalEndDate, isCreditAligned, accountsList);
    }

    protected void importAndProcessDataFromRow(DataRow row)
    {
        importFields(row);
        advanceFirstPaymentDateToAfterGlobalStart();
        alignStartDateWithCredit();
    }

    private void findFirstPaymentDate(BankAccount account)
    {
        Date nextStatementDate = account.getStatementDate();
        Date nextPaymentDate = account.getFirstEventDate();

        while (!DateTypeUtils.isFirstDateBeforeSecond(firstEventDate, nextStatementDate))
        {
            nextStatementDate = DateOperations.incrementDate(nextStatementDate,
                    account.freqType,
                    account.frequency);
        }

        firstEventDate = nextStatementDate;

        while (!DateTypeUtils.isFirstDateBeforeSecond(firstEventDate, nextPaymentDate))
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
            for (BankAccount account : accountsList)
            {
                if (account.getAccountCode()
                        .equals(accountCode) && account.getAccountType()
                        .equals(RecurEventSchema.AccountType.CREDIT))
                {
                    outputEventStatusLog(account);
                    findFirstPaymentDate(account);
                }
            }
        }
    }

    private void advanceFirstPaymentDateToAfterGlobalStart()
    {
        while (DateTypeUtils.isFirstDateBeforeSecond(firstEventDate, globalStartDate))
        {
            firstEventDate = DateOperations.incrementDate(firstEventDate, freqType, frequency);
        }
    }

    private void importFields(DataRow row)
    {
        firstEventDate = DateTypeUtils.stringToDate(row.get(START_DATE));
        name = row.get(NAME);
        amount = Double.parseDouble(row.get(AMOUNT));
        frequency = Integer.parseInt(row.get(FREQ));
        freqType = RecurEventSchema.FrequencyType.valueOf(row.get(FREQ_TYPE));
        recurType = RecurEventSchema.RecurType.valueOf(row.get(RECUR_TYPE));
        amountType = RecurEventSchema.AmountType.valueOf(row.get(AMOUNT_TYPE));
        accountCode = row.get(ACCOUNT_USED);
    }

    private void outputEventStatusLog(BankAccount account)
    {
        // TODO: 9/13/17 Remove when done debugging
        // TODO: 9/14/17 Write unit tests to cover statement/payment dates
        
        //        System.out.println("\nExpense event: " + name);
        //        System.out.println("Account for expense event: " + accountCode);
        //        System.out.println(
        //                "Original first date for expense: " + DateTypeUtils.dateToString(firstEventDate));
        //        System.out.println("Account statement date: " + account.getStatementDateString());
        //        System.out.println("Account payment date: " + account.getFirstDateString());
    }
}
