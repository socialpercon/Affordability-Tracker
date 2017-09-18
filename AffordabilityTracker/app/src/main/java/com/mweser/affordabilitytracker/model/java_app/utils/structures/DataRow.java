package com.mweser.affordabilitytracker.model.java_app.utils.structures;

import java.util.ArrayList;

public class DataRow extends ArrayList<String>
{
    public <E extends Enum<E>> String get(Enum<E> column)
    {
        return this.get(column.ordinal());
    }

    public DataRow keepColumns(int... colIndicesToKeep)
    {
        DataRow row = new DataRow();

        for (int col : colIndicesToKeep)
        {
            row.add(this.get(col));
        }

        return row;
    }

    public String toString(int... colIndicesToPrint)
    {
        String outString = "";

        for (int i = 0; i < this.size(); i++)
        {
            if (contains(colIndicesToPrint, i))
            {
                outString += this.get(i) + "\t\t";

            }
        }

        return outString;
    }

    private boolean contains(int[] list, int i)
    {
        for (int value : list)
        {
            if (i == value)
            {
                return true;
            }
        }

        return false;
    }
}
