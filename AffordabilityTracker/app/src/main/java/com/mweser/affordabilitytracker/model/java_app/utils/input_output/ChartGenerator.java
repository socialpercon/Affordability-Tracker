//package com.mweser.utils.input_output;
//
//import org.knowm.xchart.QuickChart;
//import org.knowm.xchart.SwingWrapper;
//import org.knowm.xchart.XYChart;
//
//import com.mweser.utils.structures.Data;
//import com.mweser.utils.structures.DataRow;
//
//public class ChartGenerator
//{
//    public static void displayChart(Data data, int valueIndex, String title)
//    {
//        DataRow row;
//
//        double[] dateNum = new double[data.size() - 1];
//        double[] value = new double[data.size() - 1];
//
//        for (int i = 0; i < data.size() - 1; i++)
//        {
//            row = data.get(i);
//
//            dateNum[i] = Double.parseDouble(Integer.toString(i));
//            value[i] = Double.parseDouble(row.get(valueIndex));
//        }
//
//        String headerStr =
//                title;
//
//        XYChart chart = QuickChart.getChart(headerStr,
//                "Date",
//                "Net worth ($)",
//                "Date",
//                dateNum,
//                value);
//
//        new SwingWrapper(chart).displayChart();
//
//    }
//
//
//}
