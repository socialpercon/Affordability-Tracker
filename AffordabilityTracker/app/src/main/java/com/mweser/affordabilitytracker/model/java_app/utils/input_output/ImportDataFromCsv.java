//package com.mweser.utils.input_output;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.mweser.Constants;
//import com.mweser.utils.structures.Data;
//import com.mweser.utils.structures.DataRow;
//
//public class ImportDataFromCsv {
//    public static Data readCsvByComma(String fileName) {
//        return readCSV(fileName, ",");
//    }
//
//    public static Data readCSV(String fileName) {
//        return readCSV(fileName, "\",");
//    }
//
//    public static Data readCSV(String fileName, String splitBy) {
//        String line;
//
//        Data data = new Data();
//        DataRow DataRow;
//
//        try {
//            FileReader fileReader = new FileReader(getFilePath(fileName));
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            while ((line = bufferedReader.readLine()) != null) {
//                // use comma as separator
//                String[] lineEntries = line.split(splitBy);
//                DataRow = new DataRow();
//
//                for (String entry : lineEntries) {
//                    entry = removeStartChar(entry);
//                    DataRow.add(entry);
//                }
//
//                data.add(DataRow);
//            }
//
//            bufferedReader.close();
//            fileReader.close();
//        } catch (IOException e) {
//        }
//
//        return data;
//    }
//
//    private static String getFilePath(String filename) {
//        return Constants.CSV_DIRECTORY + filename + ".csv";
//    }
//
//    private static String removeStartChar(String entry) {
//        entry = StringUtils.remove(entry, ",");
//        entry = StringUtils.remove(entry, "\"");
//        entry = StringUtils.stripStart(entry, "\"");
//        entry = StringUtils.stripStart(entry, "$");
//        return entry;
//    }
//
//}
