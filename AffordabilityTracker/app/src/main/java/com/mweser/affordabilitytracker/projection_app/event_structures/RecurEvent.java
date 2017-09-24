package com.mweser.affordabilitytracker.projection_app.event_structures;

import java.util.ArrayList;
import java.util.Date;

import com.mweser.affordabilitytracker.projection_app.data_operations.DateOperations;
import com.mweser.affordabilitytracker.projection_app.data_operations.DateConversionUtils;
import com.mweser.affordabilitytracker.projection_app.data_operations.datatable_structures.DataRow;

public abstract class RecurEvent
{
    protected boolean isCreditAligned;
    protected ArrayList<AccountEvent> accountsList;

    protected String name;
    protected double amount;
    protected Date firstEventDate;
    protected Date globalStartDate;
    protected Date globalEndDate;
    protected String accountCode;
    protected int frequency;
    protected RecurEventValueTypes.RecurType recurType;
    protected RecurEventValueTypes.FrequencyType freqType;
    protected RecurEventValueTypes.AmountType amountType;
    protected ArrayList<Date> paymentDateList;

    public RecurEvent(DataRow row, Date globalStartDate, Date globalEndDate, boolean isCreditAligned, ArrayList<AccountEvent> accountsList)
    {
        this.globalStartDate = globalStartDate;
        this.globalEndDate = globalEndDate;

        this.isCreditAligned = isCreditAligned;
        this.accountsList = accountsList;

        importAndProcessDataFromRow(row);
        generateDateList();
    }

    private void generateDateList()
    {
        addToPaymentDatesList(firstEventDate);

        if (freqType != RecurEventValueTypes.FrequencyType.ONCE)
        {
            populateList();
        }
    }

    private void populateList()
    {
        Date datePointer = DateOperations.incrementDate(firstEventDate, freqType, frequency);

        while (DateConversionUtils.isFirstDateBeforeSecond(datePointer, globalEndDate))
        {
            addToPaymentDatesList(datePointer);
            datePointer = DateOperations.incrementDate(datePointer, freqType, frequency);
        }
    }

    private void addToPaymentDatesList(Date date)
    {
        if (paymentDateList == null)
        {
            paymentDateList = new ArrayList<>();
        }

        paymentDateList.add(date);
    }

    abstract void importAndProcessDataFromRow(DataRow row);

    public String getName()
    {
        return name;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getAccountCode()
    {
        return accountCode;
    }

    public ArrayList<Date> getPaymentDateList()
    {
        return paymentDateList;
    }

    public Date getFirstEventDate()
    {
        return firstEventDate;
    }

    public String getFirstDateString()
    {
        return DateConversionUtils.dateToString(firstEventDate);
    }
}
