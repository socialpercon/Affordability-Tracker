package com.mweser.affordabilitytracker.model.java_app.projection.recurring_events;

import static com.mweser.affordabilitytracker.controller.CreateBankAccountManager.AccountField.CODE;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.AccountDataColumn.AMOUNT_NEXT_STATEMENT;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.AccountDataColumn.LAST_4;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.AccountDataColumn.NAME;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.AccountDataColumn.PAYMENT_DATE;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.AccountDataColumn.STATEMENT_DATE;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.AccountDataColumn.TOTAL_BALANCE;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.AccountDataColumn.TYPE;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.FrequencyType.DAYS;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.FrequencyType.MONTHS;
import static com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema.FrequencyType.ONCE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateOperations;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateTypeUtils;
import com.mweser.affordabilitytracker.model.java_app.utils.structures.DataRow;

public class BankAccount extends RecurEvent
{
    private RecurEventSchema.AccountType accountType;
    private Date statementDate;
    private String lastFourDigits;
    private double amountNextStatement;
    protected ArrayList<Date> statementDateList;

    public BankAccount(DataRow row, Date globalStartDate, Date globalEndDate)
    {
        super(row, globalStartDate, globalEndDate, false, null);
    }

    private void print()
    {
        System.out.println("Account name: " + name);
        System.out.println("Statement date: " + statementDate.toString());
        System.out.println("Payment date: " + firstEventDate.toString());
        System.out.println();

    }

    @Override
    protected void importAndProcessDataFromRow(DataRow row)
    {
        importFields(row);
        setDebitAccountFrequencyToOne();
        setCreditAccountsToNegative();
        findFirstPaymentDate();
    }

    public String getAmountSecondStatement()
    {
        return Double.toString(amount - amountNextStatement);
    }

    private void setDebitAccountFrequencyToOne()
    {
        recurType = RecurEventSchema.RecurType.EXACT_DATE;
        freqType = MONTHS;
        frequency = 1;

        if (accountType == RecurEventSchema.AccountType.DEBIT)
        {
            freqType = ONCE;
            amountNextStatement = amount;
        }
    }

    private void setCreditAccountsToNegative()
    {
        if (isCredit())
        {
            amount = amount * -1;
            amountNextStatement = -1 * amountNextStatement;
        }

    }

    private void findFirstPaymentDate()
    {
        while (DateTypeUtils.isFirstDateBeforeSecond(firstEventDate, globalStartDate))
        {
            firstEventDate = DateOperations.incrementDate(firstEventDate, freqType, frequency);
        }

        while (DateTypeUtils.isFirstDateBeforeSecond(statementDate, globalStartDate))
        {
            statementDate = DateOperations.incrementDate(statementDate, freqType, frequency);
        }

        if (isDebit())
        {
            firstEventDate = DateOperations.decrementDate(firstEventDate, DAYS, 1);
        }
    }

    private boolean isDebit()
    {
        return accountType == RecurEventSchema.AccountType.DEBIT;
    }

    private boolean isCredit()
    {
        return accountType == RecurEventSchema.AccountType.CREDIT;
    }

    private int[] getCurrentYearMonthDate()
    {
        int[] dateArray = new int[3];

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(globalStartDate);

        dateArray[0] = calendar.get(Calendar.YEAR);
        dateArray[1] = calendar.get(Calendar.MONTH) + 1;
        dateArray[2] = calendar.get(Calendar.DAY_OF_MONTH);

        return dateArray;
    }

    private void importFields(DataRow row)
    {
        int[] dateArray = DateOperations.getCurrentDate();

        name = row.get(NAME);
        accountCode = row.get(CODE);
        accountType = RecurEventSchema.AccountType.valueOf(row.get(TYPE));
        firstEventDate = DateTypeUtils.stringToDate(dateArray[1] + "/" + row.get(PAYMENT_DATE) + "/" + dateArray[0]);     // TODO: 9/13/17 Correct issue with parsing string
        statementDate = DateTypeUtils.stringToDate(dateArray[1] + "/" + row.get(STATEMENT_DATE) + "/" + dateArray[0]);
        lastFourDigits = row.get(LAST_4);
        amountNextStatement = Double.parseDouble(row.get(AMOUNT_NEXT_STATEMENT));
        amount = Double.parseDouble(row.get(TOTAL_BALANCE));
    }

    public RecurEventSchema.AccountType getAccountType()
    {
        return accountType;
    }

    public String getStatementDateString()
    {
        return DateTypeUtils.dateToString(statementDate);
    }

    public Date getStatementDate()
    {
        return statementDate;
    }

    public String getAmountNextStatementString()
    {
        return Double.toString(amountNextStatement);
    }
}
