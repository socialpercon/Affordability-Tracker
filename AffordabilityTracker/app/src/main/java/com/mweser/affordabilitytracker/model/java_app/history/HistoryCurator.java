//package com.mweser.history;
//
//import static com.mweser.Constants.DISPLAY_CHART;
//import static com.mweser.Constants.PRINT_TABLE;
//import static com.mweser.utils.data_operations.ColumnOps.MonthCalibrationColumn.ASSETS;
//import static com.mweser.utils.data_operations.ColumnOps.MonthCalibrationColumn.MONTH_YEAR;
//import static com.mweser.utils.data_operations.ColumnOps.TransactionColumn.AMOUNT;
//import static com.mweser.utils.data_operations.ColumnOps.TransactionColumn.DATE;
//import static com.mweser.utils.data_operations.ColumnOps.addRunningTotalColumn;
//import static com.mweser.utils.input_output.ChartGenerator.displayChart;
//
//import java.util.Collections;
//
//import com.mweser.utils.data_operations.ColumnOps;
//import com.mweser.utils.data_operations.RowOps;
//import com.mweser.utils.data_operations.date_operations.DateOperations;
//import com.mweser.utils.input_output.ImportDataFromCsv;
//import com.mweser.utils.structures.Data;
//import com.mweser.utils.structures.DataRow;
//
//public class HistoryCurator
//{
//    private Data transactions;
//    private Data originalTable;
//    private Data calibrationData;
//
//    public HistoryCurator()
//    {
//        transactions = new Data();
//        originalTable = new Data();
//    }
//
//    public void curateAndPrintHistoryChart()
//    {
//
//        importDataFromCsvFiles();
//        initialTableFormatting();
//
//        if (DISPLAY_CHART)
//        {
//            displayChart(transactions, 1, "Net worth history");
//        }
//
//        if (PRINT_TABLE)
//        {
//            System.out.println(transactions.toString());
//        }
//    }
//
//    private void initialTableFormatting()
//    {
//        double startValue = 1650;
//
//        transactions = originalTable;
//        transactions.remove(0);
//        Collections.reverse(transactions);
//
//        transactions = ColumnOps.evaluateIsDebitCol(transactions);
//
//        transactions = transactions.keepColumns(DATE, AMOUNT);
//        calibrationData = calibrationData.keepColumns(MONTH_YEAR, ASSETS);
//
//        calibrationData = DateOperations.convertMonthYearArrayToLongArray(calibrationData,
//                transactions.getValueAt(0, 0),
//                0);
//
//        // TODO: 9/4/17 Consider reordering to allow squash to happen first to improve performance
//        transactions = addRunningTotalColumn(transactions, startValue, 1);
//        transactions = DateOperations.convertDateArrayToLongArray(transactions, 0);
//        transactions = transactions.keepColumns(0, 2);
//        transactions = RowOps.squashDateEntries(transactions);
//        transactions = RowOps.fillMissingDays(transactions, 0, 1);
//
//        transactions = insertMonthlyCalibrations(transactions, calibrationData);
//    }
//
//    private Data insertMonthlyCalibrations(Data data, Data calibrationData)
//    {
//        for (DataRow row : calibrationData)
//        {
//            data.add(row);
//        }
//
//        data = RowOps.sortByFirstIndex(data);
//
//        return data;
//    }
//
//    private void importDataFromCsvFiles()
//    {
//        originalTable = ImportDataFromCsv.readCSV("transactions");
//        calibrationData = ImportDataFromCsv.readCSV("month_history");
//    }
//
//}
