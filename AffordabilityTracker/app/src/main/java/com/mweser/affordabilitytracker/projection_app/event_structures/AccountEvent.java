package com.mweser.affordabilitytracker.projection_app.event_structures;

import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountDataColumn.AMOUNT_NEXT_STATEMENT;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountDataColumn.LAST_4;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountDataColumn.NAME;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountDataColumn.PAYMENT_DATE;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountDataColumn.STATEMENT_DATE;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountDataColumn.TOTAL_BALANCE;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountDataColumn.TYPE;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.FrequencyType.DAYS;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.FrequencyType.MONTHS;
import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.FrequencyType.ONCE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mweser.affordabilitytracker.projection_app.data_operations.DateConversionUtils;
import com.mweser.affordabilitytracker.projection_app.data_operations.DateOperations;
import com.mweser.affordabilitytracker.projection_app.data_operations.datatable_structures.DataRow;

public class AccountEvent extends RecurEvent
{
    private RecurEventValueTypes.AccountType accountType;
    private Date statementDate;
    private String lastFourDigits;
    private double amountNextStatement;
    protected ArrayList<Date> statementDateList;

    public AccountEvent(DataRow row, Date globalStartDate, Date globalEndDate)
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
        recurType = RecurEventValueTypes.RecurType.EXACT_DATE;
        freqType = MONTHS;
        frequency = 1;

        if (accountType == RecurEventValueTypes.AccountType.DEBIT)
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
        while (DateConversionUtils.isFirstDateBeforeSecond(firstEventDate, globalStartDate))
        {
            firstEventDate = DateOperations.incrementDate(firstEventDate, freqType, frequency);
        }

        while (DateConversionUtils.isFirstDateBeforeSecond(statementDate, globalStartDate))
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
        return accountType == RecurEventValueTypes.AccountType.DEBIT;
    }

    private boolean isCredit()
    {
        return accountType == RecurEventValueTypes.AccountType.CREDIT;
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
        accountType = RecurEventValueTypes.AccountType.valueOf(row.get(TYPE));
        firstEventDate = DateConversionUtils.stringToDate(dateArray[1] + "/" + row.get(PAYMENT_DATE) + "/" + dateArray[0]);     // TODO: 9/13/17 Correct issue with parsing string
        statementDate = DateConversionUtils.stringToDate(dateArray[1] + "/" + row.get(STATEMENT_DATE) + "/" + dateArray[0]);
        lastFourDigits = row.get(LAST_4);
        amountNextStatement = Double.parseDouble(row.get(AMOUNT_NEXT_STATEMENT));
        amount = Double.parseDouble(row.get(TOTAL_BALANCE));
    }

    public RecurEventValueTypes.AccountType getAccountType()
    {
        return accountType;
    }

    public String getStatementDateString()
    {
        return DateConversionUtils.dateToString(statementDate);
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
