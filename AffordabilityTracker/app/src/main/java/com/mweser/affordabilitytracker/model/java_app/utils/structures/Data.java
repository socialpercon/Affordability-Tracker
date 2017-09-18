package com.mweser.affordabilitytracker.model.java_app.utils.structures;

import java.util.ArrayList;

public class Data extends ArrayList<DataRow> {

    public <E extends Enum<E>> Data keepColumns(Enum<E>... colIndicesToKeep) {
        int[] ordinalList = new int[colIndicesToKeep.length];

        for(int i = 0; i < colIndicesToKeep.length; i++)
        {
            ordinalList[i] = colIndicesToKeep[i].ordinal();
        }

        return keepColumns(ordinalList);
    }

    public Data keepColumns(int... colIndicesToKeep) {
        Data table = new Data();

        for (DataRow row : this) {
            table.add(row.keepColumns(colIndicesToKeep));
        }

        return table;
    }

    public String getValueAt(int rowIndex, int colIndex) {
        if (rowIndex < this.size()) {
            if (colIndex < this.get(rowIndex)
                    .size()) {
                return this.get(rowIndex)
                        .get(colIndex);
            }
        }

        return null;
    }

    public void setValueAt(int rowIndex, int colIndex, String value) {
        if (rowIndex < this.size()) {
            if (colIndex < this.get(rowIndex)
                    .size()) {
                this.get(rowIndex)
                        .set(colIndex, value);
            }
        }
    }

    @Override
    public String toString() {
        String outString = "";

        for (DataRow row : this) {
            outString += row.toString() + "\n";
        }

        return outString;
    }


    public String toString(int... colIndicesToPrint) {
        String outString = "";

        for (DataRow row : this) {

            outString += row.toString(colIndicesToPrint) + "\n";
        }

        return outString;
    }

    public void print()
    {
        System.out.println(toString());
    }
}
