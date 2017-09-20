package com.mweser.affordabilitytracker.model.java_app.projection.recurring_events;

import java.util.ArrayList;
import java.util.Date;

import com.mweser.affordabilitytracker.model.java_app.projection.RecurEventSchema;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateOperations;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateTypeUtils;
import com.mweser.affordabilitytracker.model.java_app.utils.structures.DataRow;

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
    protected RecurEventSchema.RecurType recurType;
    protected RecurEventSchema.FrequencyType freqType;
    protected RecurEventSchema.AmountType amountType;
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

        if (freqType != RecurEventSchema.FrequencyType.ONCE)
        {
            populateList();
        }
    }

    private void populateList()
    {
        Date datePointer = DateOperations.incrementDate(firstEventDate, freqType, frequency);

        while (DateTypeUtils.isFirstDateBeforeSecond(datePointer, globalEndDate))
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
        return DateTypeUtils.dateToString(firstEventDate);
    }
}
