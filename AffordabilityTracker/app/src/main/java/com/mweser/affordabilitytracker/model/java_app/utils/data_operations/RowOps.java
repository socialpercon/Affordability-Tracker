package com.mweser.affordabilitytracker.model.java_app.utils.data_operations;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.mweser.affordabilitytracker.model.java_app.utils.data_operations.date_operations.DateTypeUtils;
import com.mweser.affordabilitytracker.model.java_app.utils.structures.Data;
import com.mweser.affordabilitytracker.model.java_app.utils.structures.DataRow;

public class RowOps
{
    public static Data trimExtraRows(Data data, int maxSize)
    {
        while (data.size() > maxSize)
        {
            data.remove(data.size() - 1);
        }

        return data;
    }

    /**
     * Create "fullTable" with each value represented
     * Copy existing entries with same key to fullTable,
     * leaving the empty entries with the same value as the previous.
     *
     * @param data
     * @return
     */
    public static Data fillMissingDays(Data data, int dateColumn, int valueColumn)
    {
        // TODO: 9/4/17 Make this more performant

        Data fullTable = createFullSizedTable(data, dateColumn, valueColumn);
        DataRow row;

        fullTable.set(0, data.get(dateColumn));

        for (int rowNum = 1; rowNum < fullTable.size(); rowNum++)
        {
            row = getRowWithValue(data,
                    Integer.toString(rowNum),
                    fullTable.getValueAt(rowNum - 1, valueColumn));
            fullTable.set(rowNum, row);
        }

        return fullTable;
    }

    private static Data createFullSizedTable(Data data, int dateColumn, int valueColumn)
    {
        Data fullTable = new Data();

        int numRows = data.size();
        int fullTableSize = Integer.parseInt(data.getValueAt(numRows - 1, 0));

        // create new indexed table with zeroes populated to data values
        for (int i = 0; i < fullTableSize; i++)
        {
            DataRow row = new DataRow();
            row.add(Integer.toString(i));
            row.add("0");
            fullTable.add(row);
        }

        return fullTable;
    }

    /**
     * Method returns row populated with row value if not found in list,
     * else returns existing row from data.
     *
     * @param data
     * @param value
     * @return
     */
    private static DataRow getRowWithValue(Data data, String value, String prevValue)
    {
        DataRow defaultRow = new DataRow();
        defaultRow.add(value);
        defaultRow.add(prevValue);

        for (DataRow row : data)
        {
            if (row.get(0)
                    .equals(value))
            {
                return row;
            }
        }

        return defaultRow;
    }

    /**
     * Use if already running total run, where later indices have overall value for day
     *
     * @param data
     * @return
     */
    public static Data squashDateEntries(Data data)
    {
        Data newTable = new Data();
        DataRow row;
        DataRow nextRow;

        int numRows = data.size();

        for (int rowIndex = 0; rowIndex < numRows - 1; rowIndex++)
        {
            row = data.get(rowIndex);
            nextRow = data.get(rowIndex + 1);

            if (!row.get(0)
                    .equals(nextRow.get(0)))
            {
                newTable.add(row);
            }
        }
        newTable.add(data.get(data.size() - 1));
        return newTable;
    }

    // TODO: 9/4/17 Needs to be able to work with rows that contain 2 elements (use last value)
    public static Data squashDateEntries(Data data, int dateColumn, int valueColumn)
    {
        Data newTable = new Data();
        int newTableEntries = 0;

        DataRow row;
        DataRow prevRow;

        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++)
        {
            row = data.get(rowIndex);
            DataRow newRow = copyRowData(row);

            // if same date, sum running values together and delete previous event
            if (newTable.size() > 0)
            {
                prevRow = newTable.get(newTable.size() - 1);

                if (datesMatch(row, prevRow, dateColumn))
                {
                    newRow = setValueColumnToSumOfTwoRows(newRow, row, prevRow, valueColumn);
                    newTable.remove(--newTableEntries);
                }
            }
            newTable.add(newRow);
            newTableEntries++;
        }

        return newTable;
    }

    private static DataRow setValueColumnToSumOfTwoRows(DataRow newRow, DataRow row,
            DataRow prevRow, int valueColumn)
    {
        newRow.set(valueColumn, Double.toString(sumValuesOfRows(row, prevRow, valueColumn)));
        return newRow;
    }

    private static double sumValuesOfRows(DataRow row, DataRow prevRow, int valueColumn)
    {
        double value = Double.parseDouble(row.get(valueColumn));
        double prevValue = Double.parseDouble(prevRow.get(valueColumn));
        double sum = value + prevValue;

        return sum;
    }

    private static boolean datesMatch(DataRow row, DataRow prevRow, int dateColumn)
    {
        String date1 = prevRow.get(dateColumn);
        String date2 = row.get(dateColumn);

        return date1.equals(date2);
    }

    private static DataRow copyRowData(DataRow row)
    {
        DataRow newRow = new DataRow();

        for (String rowElement : row)
        {
            newRow.add(rowElement);
        }

        return newRow;
    }

    public static Data sortDateStrings(Data data)
    {
        Collections.sort(data, new Comparator<DataRow>()
        {
            @Override
            public int compare(DataRow row1, DataRow row2)
            {
                Date date1 = DateTypeUtils.stringToDate(row1.get(0));
                Date date2 = DateTypeUtils.stringToDate(row2.get(0));

                return DateUtils.truncatedCompareTo(date1, date2, Calendar.HOUR_OF_DAY);
            }
        });

        return data;
    }

    public static Data sortByFirstIndex(Data data)
    {
        Collections.sort(data, new Comparator<DataRow>()
        {
            @Override
            public int compare(DataRow row1, DataRow row2)
            {
                int value1 = Integer.parseInt(row1.get(0));
                int value2 = Integer.parseInt(row2.get(0));

                return value1 - value2;
            }
        });

        return data;
    }

    public static Data sortBySecondIndex(Data data)
    {
        Collections.sort(data, new Comparator<DataRow>()
        {
            @Override
            public int compare(DataRow row1, DataRow row2)
            {
                double value1 = Double.parseDouble(row1.get(1));
                double value2 = Double.parseDouble(row2.get(1));

                return ((int) value1 * 100 - (int) value2 * 100);
            }
        });

        return data;
    }
}
