//package com.mweser.utils.input_output;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class ExportDataToCsv
//{
//
//    public static void writeCSV(String data, String fileName)
//    {
//        try
//        {
//            FileWriter fileWriter = new FileWriter(fileName);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//
//            bufferedWriter.write(data);
//
//            System.out.println("\n>> " + fileName + " created");
//
//            bufferedWriter.close();
//            fileWriter.close();
//        }
//        catch (IOException e)
//        {
//        }
//
//    }
//}
