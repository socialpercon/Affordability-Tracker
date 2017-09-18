package com.mweser.affordabilitytracker.model.java_app;

public class Constants
{
    public static final String CSV_DIRECTORY = "/Users/matthewweser/Google Drive/Projects/Finances/real_inputs/";
    public static final boolean PRINT_TABLE = true;
    public static final boolean DISPLAY_CHART = true;
    public static final boolean BOTH_ENABLED = false;
    public static final boolean HISTORY_ENABLED = false;
    public static final boolean PROJECTION_ENABLED = true;

//    public static void main(String args[])
//    {
//        if (HISTORY_ENABLED || BOTH_ENABLED)
//        {
//            HistoryCurator historyCurator = new HistoryCurator();
//            historyCurator.curateAndPrintHistoryChart();
//        }
//
//        if (!HISTORY_ENABLED || BOTH_ENABLED)
//        {
//            ProjectionCurator projectionCurator = new ProjectionCurator();
//            projectionCurator.curateProjection();
//        }
//    }
}
