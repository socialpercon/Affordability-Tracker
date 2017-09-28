package com.mweser.affordabilitytracker.activity.utils;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.activity.account.AccountActivity;
import com.mweser.affordabilitytracker.activity.expense.ExpensesActivity;
import com.mweser.affordabilitytracker.activity.points.CreditPointsActivity;
import com.mweser.affordabilitytracker.activity.projection.ProjectionActivity;
import com.mweser.affordabilitytracker.activity.projection.ProjectionListActivity;
import com.mweser.affordabilitytracker.activity.settings.SettingsActivity;
import com.mweser.affordabilitytracker.activity.thresholds.ThresholdsActivity;
import com.mweser.affordabilitytracker.activity.wishlist.WishlistActivity;
import com.mweser.affordabilitytracker.activity.wishlist_schedule.WishlistScheduleActivity;
import com.mweser.affordabilitytracker.database.Database;
import com.mweser.affordabilitytracker.database.database_operations.InsertOperations;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ActivityUtils
{
    public static void toggleDrawer(Activity activity)
    {
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    public static void clearDataTableOption(Schema.Tables table)
    {


    }

    public static <T extends View> List<T> generateListOfUiElements(Activity activity, int... ids)
    {
        List<T> list = new ArrayList<>();

        for (int index = 0; index < ids.length; index++)
        {
            list.add((T) activity.findViewById(ids[index]));
        }

        return list;
    }

    public static void setUpActivityTransitionFab(int id, Context baseContext, Activity activity,
            Class<?> nextActivityClass)
    {
        activity.findViewById(id)
                .setOnClickListener(generateActivitySwitchFabListener(baseContext,
                        activity,
                        nextActivityClass));
    }

    public static void insertUiFieldsToDatabase(Context appContext, Schema.Tables table,
            List<String> dataEntries, List<Enum<?>> schemaOrder)
    {
        Database.executeSQL(appContext, InsertOperations.newInsertCommand(table.toString(),
                dataEntries,
                schemaOrder));
    }

    public static List<Enum<?>> generateEnumArrayList(Enum<?>... enumList)
    {
        List<Enum<?>> list = new ArrayList<>();

        for (Enum<?> index : enumList)
        {
            list.add(index);
        }

        return list;
    }

    private static View.OnClickListener generateActivitySwitchFabListener(final Context baseContext,
            final Activity activity, final Class<?> nextActivityClass)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                activity.finish();
                ActivityUtils.startActivity(baseContext, activity, nextActivityClass);
            }
        };
    }

    public static void navBarSwitch(Context appContext, Context baseContext, Activity activity,
            MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_accounts)
        {
            ActivityUtils.startActivity(baseContext, activity, AccountActivity.class);
        }
        else if (id == R.id.nav_expenses)
        {
            ActivityUtils.startActivity(baseContext, activity, ExpensesActivity.class);
        }
        else if (id == R.id.nav_thresholds)
        {
            ActivityUtils.startActivity(baseContext, activity, ThresholdsActivity.class);
        }
        else if (id == R.id.nav_wishlist)
        {
            ActivityUtils.startActivity(baseContext, activity, WishlistActivity.class);
        }
        else if (id == R.id.nav_projection)
        {
            ActivityUtils.startActivity(baseContext, activity, ProjectionActivity.class);
        }
        else if (id == R.id.nav_future_events_list)
        {
            ActivityUtils.startActivity(baseContext, activity, ProjectionListActivity.class);
        }
        else if (id == R.id.nav_wishlist_schedule)
        {
            ActivityUtils.startActivity(baseContext, activity, WishlistScheduleActivity.class);
        }
        else if (id == R.id.nav_credit_points)
        {
            ActivityUtils.startActivity(baseContext, activity, CreditPointsActivity.class);
        }
        else if (id == R.id.nav_prefs)
        {
            ActivityUtils.startActivity(baseContext, activity, SettingsActivity.class);
        }
    }

    public static void newToast(Context appContext, String message)
    {
        Toast.makeText(appContext, message, Toast.LENGTH_LONG)
                .show();
    }

    public static void startActivity(Context baseContext, Activity currentActivity,
            Class<?> nextActivityClass)
    {
        currentActivity.startActivity(new Intent(baseContext, nextActivityClass));
    }
}
