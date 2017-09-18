package com.mweser.affordabilitytracker.model.java_app.utils.data_operations;

import static com.mweser.affordabilitytracker.model.java_app.utils.data_operations.ColumnOps.TransactionColumn.AMOUNT;
import static com.mweser.affordabilitytracker.model.java_app.utils.data_operations.ColumnOps.TransactionColumn.IS_DEBIT;

import java.text.DecimalFormat;

import com.mweser.affordabilitytracker.model.java_app.utils.structures.Data;
import com.mweser.affordabilitytracker.model.java_app.utils.structures.DataRow;

/**
 * Utils for checking/manipulating various columns of data structures.
 */
public class ColumnOps
{
    public enum TransactionColumn
    {
        DATE, ITEM, DESCRIPTION, AMOUNT, IS_DEBIT, TYPE, ACCOUNT
    }

    public enum MonthCalibrationColumn
    {
        MONTH_YEAR, ASSETS, DEBTS, NET
    }

    // TODO: 8/28/17 Generericize method for greater universal utility
    public static Data evaluateIsDebitCol(Data transactionTable)
    {
        for (int rowIndex = 0; rowIndex < transactionTable.size(); rowIndex++)
        {
            DataRow row = transactionTable.get(rowIndex);

            if (row.get(IS_DEBIT.ordinal())
                    .contains("debit"))
            {
                row.set(AMOUNT.ordinal(), "-" + row.get(AMOUNT.ordinal()));
                transactionTable.set(rowIndex, row);
            }
        }

        return transactionTable;
    }

    public static Data addRunningTotalColumn(Data table, double startValue, int valueColumn)
    {
        DataRow row = table.get(0);
        DataRow prevRow;

        row.add(Double.toString(startValue));

        for (int rowIndex = 0; rowIndex < table.size() - 1; rowIndex++)
        {
            prevRow = table.get(rowIndex);
            row = table.get(rowIndex + 1);

            row.add(sumTwoRows(row, prevRow, valueColumn));

        }

        return table;
    }

    private static String sumTwoRows(DataRow row, DataRow prevRow, int valueColumn)
    {
        DecimalFormat df = new DecimalFormat("0.##");

        double prevRowValue = Double.parseDouble(prevRow.get(prevRow.size() - 1));
        double rowValue = Double.parseDouble(row.get(valueColumn));

        double sum = prevRowValue + rowValue;


        return df.format(sum);

    }
}
