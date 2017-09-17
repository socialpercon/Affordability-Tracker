package com.mweser.affordabilitytracker.controller;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.view.BankAccountActivity;
import com.mweser.affordabilitytracker.view.CreditPointsActivity;
import com.mweser.affordabilitytracker.view.ExpensesActivity;
import com.mweser.affordabilitytracker.view.ProjectionActivity;
import com.mweser.affordabilitytracker.view.ProjectionListActivity;
import com.mweser.affordabilitytracker.view.SettingsActivity;
import com.mweser.affordabilitytracker.view.ThresholdsActivity;
import com.mweser.affordabilitytracker.view.WishlistActivity;
import com.mweser.affordabilitytracker.view.WishlistScheduleActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

public class Utils
{
    public static void navBarSwitch(Context appContext, Context baseContext, Activity activity, MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_accounts) {

            newToast(appContext, "This is the accounts page");
            Utils.startActivity(baseContext, activity, BankAccountActivity.class);

        } else if (id == R.id.nav_expenses) {
            newToast(appContext, "This is the expenses page");
            Utils.startActivity(baseContext, activity, ExpensesActivity.class);

        } else if (id == R.id.nav_thresholds) {
            newToast(appContext, "This is the thresholds page");
            Utils.startActivity(baseContext, activity, ThresholdsActivity.class);

        } else if (id == R.id.nav_wishlist) {
            newToast(appContext, "This is the wishlist page");
            Utils.startActivity(baseContext, activity, WishlistActivity.class);

        } else if (id == R.id.nav_projection) {
            newToast(appContext, "This is the projection page");
            Utils.startActivity(baseContext, activity, ProjectionActivity.class);

        } else if (id == R.id.nav_future_events_list) {
            newToast(appContext, "This is the future events page");
            Utils.startActivity(baseContext, activity, ProjectionListActivity.class);

        } else if (id == R.id.nav_wishlist_schedule) {
            newToast(appContext, "This is the wishlist schedule page");
            Utils.startActivity(baseContext, activity, WishlistScheduleActivity.class);

        } else if (id == R.id.nav_credit_points) {
            newToast(appContext, "This is the credit card points page");
            Utils.startActivity(baseContext, activity, CreditPointsActivity.class);

        } else if (id == R.id.nav_prefs) {
            newToast(appContext, "This is the preferences page");
            Utils.startActivity(baseContext, activity, SettingsActivity.class);

        }

    }
    
    public static void newToast(Context appContext, String message)
    {
        Toast.makeText(appContext, message, Toast.LENGTH_LONG)
                .show();
    }

    public static void startActivity(Context baseContext, Activity currentActivity, Class<?> nextActivityClass)
    {
        currentActivity.startActivity(new Intent(baseContext, nextActivityClass));
    }

}
