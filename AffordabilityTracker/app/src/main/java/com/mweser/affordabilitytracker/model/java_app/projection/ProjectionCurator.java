package com.mweser.affordabilitytracker.model.java_app.projection;

import java.util.ArrayList;
import java.util.Date;

import com.mweser.affordabilitytracker.model.java_app.projection.recurring_events.BankAccount;
import com.mweser.affordabilitytracker.model.java_app.projection.recurring_events.ExpenseEvent;
import com.mweser.affordabilitytracker.model.java_app.projection.recurring_events.RecurEvent;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.ColumnOps;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.RowOps;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateOperations;
import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateTypeUtils;
import com.mweser.affordabilitytracker.model.java_app.utils.structures.Data;
import com.mweser.affordabilitytracker.model.java_app.utils.structures.DataRow;

public class ProjectionCurator
{
    public boolean CREDIT_ALIGNED = true;
    public int numDates;
    public String START_DATE;
    public String END_DATE = "3/14/2018";
    private Date globalStartDate;
    private Date globalEndDate;
    public final String ACCOUNTS_FILENAME = "account_list_real";
    public final String EXPENSE_EVENTS_FILENAME = "recur_events_real";
    private Data printTable;
    private Data affordabilityTable;
    private Data combinedEventsData;
    private ArrayList<ExpenseEvent> expenseEventList;
    private ArrayList<BankAccount> bankAccountList;

    private void generateProjectionList()
    {
        // TODO: 9/13/17 Determine if combinedEventsData table is necessary

        int dateColumn = 0;
        int valueColumn = 2;
        double startValue = Double.parseDouble(combinedEventsData.getValueAt(0, valueColumn));

        printTable = ColumnOps.addRunningTotalColumn(combinedEventsData, startValue, valueColumn);
        printTable.print();

        combinedEventsData = DateOperations.convertDateArrayToLongArray(combinedEventsData,
                dateColumn);
        combinedEventsData = RowOps.squashDateEntries(combinedEventsData, dateColumn, valueColumn);
        combinedEventsData = ColumnOps.addRunningTotalColumn(combinedEventsData,
                startValue,
                valueColumn);

        // TODO: 9/13/17 Fix to reduce redundancy with filling and trimming
        printTable = combinedEventsData.keepColumns(0, 3);
        printTable = RowOps.fillMissingDays(printTable, dateColumn, 1);
        printTable = RowOps.trimExtraRows(printTable, numDates);

        affordabilityTable.addAll(printTable);

        combinedEventsData = RowOps.fillMissingDays(combinedEventsData, dateColumn, 1);
        combinedEventsData = RowOps.trimExtraRows(combinedEventsData, numDates);

        combinedEventsData = DateOperations.convertLongArrayToDateArray(combinedEventsData,
                dateColumn,
                START_DATE);
    }

    public ProjectionCurator()
    {
        declareClassFields();
    }

    public void curateProjection()
    {
        importBankAccountData(ACCOUNTS_FILENAME);
        importExpenseEventData(EXPENSE_EVENTS_FILENAME);

        addDataToTableAndSortByDate(bankAccountList, expenseEventList);

        generateProjectionList();
        generateAffordabilityList();
        tryOutputData();
    }

    /**
     * Steps:
     * 1. Sort table by dollar value
     * 2. Remove rows for dates that are out of order (follow chronology)
     * 3. Re-sort based on date
     * 4. Fill in missing rows with lowest future value
     */
    private void generateAffordabilityList()
    {

        DataRow row;
        DataRow nextRow;
        int rowDateInt;
        int nextRowDateInt;

        affordabilityTable = RowOps.sortBySecondIndex(affordabilityTable);

        for (int rowIndex = 0; rowIndex < affordabilityTable.size() - 1; rowIndex++)
        {
            row = affordabilityTable.get(rowIndex);
            nextRow = affordabilityTable.get(rowIndex + 1);

            rowDateInt = Integer.parseInt(row.get(0));
            nextRowDateInt = Integer.parseInt(nextRow.get(0));

            while (rowDateInt >= nextRowDateInt)
            {
                affordabilityTable.remove(rowIndex + 1);

                if (rowIndex + 1 < affordabilityTable.size() - 1)
                {

                    nextRow = affordabilityTable.get(rowIndex + 1);
                    nextRowDateInt = Integer.parseInt(nextRow.get(0));
                }
                else
                {
                    break;
                }
            }
        }

        fillAffordabilityTable();
    }

    /**
     * Fill missing index values and set value to next existing value
     */
    private void fillAffordabilityTable()
    {
        Data newTable = new Data();
        DataRow row;

        for (int i = 0; i < numDates; i++)
        {
            row = new DataRow();
            row.add(Integer.toString(i));
            row.add("0.0");
            newTable.add(row);
        }

        int originalTableIndex = 0;

        for (int rowIndex = 0; rowIndex < newTable.size(); rowIndex++)
        {
            if (originalTableIndex >= affordabilityTable.size())
            {
                newTable.setValueAt(rowIndex,
                        1,
                        affordabilityTable.get(originalTableIndex - 1)
                                .get(1));
            }
            else
            {
                if (newTable.get(rowIndex)
                        .get(0)
                        .equals(affordabilityTable.get(originalTableIndex)
                                .get(0)))
                {
                    newTable.setValueAt(rowIndex,
                            1,
                            affordabilityTable.get(originalTableIndex)
                                    .get(1));
                    originalTableIndex++;
                }
                else
                {
                    newTable.setValueAt(rowIndex,
                            1,
                            affordabilityTable.get(originalTableIndex)
                                    .get(1));
                }
            }
        }

        affordabilityTable = newTable;
    }

    private void tryOutputData()
    {
//        if (DISPLAY_CHART)
//        {
//            displayChart(printTable, 1, "Projected finances " + START_DATE + "-" + END_DATE);
//            displayChart(affordabilityTable,
//                    1,
//                    "Affordability Index " + START_DATE + "-" + END_DATE);
//        }
//
//        if (PRINT_TABLE)
//        {
//            //            System.out.println(combinedEventsData.toString());
//            //            System.out.println(affordabilityTable.toString());
//        }
    }

    private void importBankAccountData(String filename)
    {

//        Data bankAccountData = ImportDataFromCsv.readCsvByComma(filename);

        Data bankAccountData;

//        bankAccountData.remove(0);

        for (DataRow row : bankAccountData)
        {
            bankAccountList.add(new BankAccount(row, globalStartDate, globalEndDate));
        }
    }

    private void importExpenseEventData(String filename)
    {

//        Data expenseEventData = ImportDataFromCsv.readCsvByComma(filename);

        Data expenseEventData;

//        expenseEventData.remove(0);

        for (DataRow row : expenseEventData)
        {
            expenseEventList.add(new ExpenseEvent(row,
                    globalStartDate,
                    globalEndDate,
                    CREDIT_ALIGNED,
                    bankAccountList));
        }
    }

    private void addDataToTableAndSortByDate(ArrayList<? extends RecurEvent>... listArray)
    {

        for (ArrayList<? extends RecurEvent> list : listArray)
        {

            for (RecurEvent event : list)
            {
                ArrayList<Date> dateList = event.getPaymentDateList();

                for (int index = 0; index < dateList.size(); index++)
                {

                    String dateString = DateTypeUtils.dateToString(dateList.get(index));

                    DataRow row = new DataRow();
                    row.add(dateString);
                    row.add(event.getName());

                    if (event instanceof ExpenseEvent)
                    {
                        row.add(Double.toString(event.getAmount()));

                    }
                    else if (event instanceof BankAccount)
                    {

                        BankAccount bankAccount = (BankAccount) event;

                        if (index == 0)
                        {
                            row.add(bankAccount.getAmountNextStatementString());
                        }
                        else if (index == 1)
                        {
                            row.add(bankAccount.getAmountSecondStatement());
                        }
                        else
                        {
                            row.add("0");
                        }

                    }

                    combinedEventsData.add(row);
                }
            }
        }

        combinedEventsData = RowOps.sortDateStrings(combinedEventsData);
    }

    private void declareClassFields()
    {

        START_DATE = DateOperations.getCurrentDateString();
        numDates = Integer.parseInt(DateOperations.daysBetweenDates(START_DATE, END_DATE));

        globalStartDate = DateTypeUtils.stringToDate(START_DATE);
        globalEndDate = DateTypeUtils.stringToDate(END_DATE);

        printTable = new Data();
        affordabilityTable = new Data();
        combinedEventsData = new Data();

        expenseEventList = new ArrayList<>();
        bankAccountList = new ArrayList<>();
    }
}
