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

public class ActivityUtils
{
    public static void navBarSwitch(Context appContext, Context baseContext, Activity activity, MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_accounts) {
            ActivityUtils.startActivity(baseContext, activity, BankAccountActivity.class);

        } else if (id == R.id.nav_expenses) {
            ActivityUtils.startActivity(baseContext, activity, ExpensesActivity.class);

        } else if (id == R.id.nav_thresholds) {
            ActivityUtils.startActivity(baseContext, activity, ThresholdsActivity.class);

        } else if (id == R.id.nav_wishlist) {
            ActivityUtils.startActivity(baseContext, activity, WishlistActivity.class);

        } else if (id == R.id.nav_projection) {
            ActivityUtils.startActivity(baseContext, activity, ProjectionActivity.class);

        } else if (id == R.id.nav_future_events_list) {
            ActivityUtils.startActivity(baseContext, activity, ProjectionListActivity.class);

        } else if (id == R.id.nav_wishlist_schedule) {
            ActivityUtils.startActivity(baseContext, activity, WishlistScheduleActivity.class);

        } else if (id == R.id.nav_credit_points) {
            ActivityUtils.startActivity(baseContext, activity, CreditPointsActivity.class);

        } else if (id == R.id.nav_prefs) {
            ActivityUtils.startActivity(baseContext, activity, SettingsActivity.class);

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
