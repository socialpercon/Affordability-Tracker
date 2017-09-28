package com.mweser.affordabilitytracker.activity.settings;

import static com.mweser.affordabilitytracker.database.schema.Schema.settings.ACCENT_HUE;
import static com.mweser.affordabilitytracker.database.schema.Schema.settings.DATE_RANGE;
import static com.mweser.affordabilitytracker.database.schema.Schema.settings.DEFAULT_FREQUENCY;
import static com.mweser.affordabilitytracker.database.schema.Schema.settings.DEFAULT_FREQUENCY_TYPE;
import static com.mweser.affordabilitytracker.database.schema.Schema.settings.END_DATE;
import static com.mweser.affordabilitytracker.database.schema.Schema.settings.RANGE_UNIT;
import static com.mweser.affordabilitytracker.database.schema.Schema.settings.SECURITY_PIN;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.activity.MainActivity;
import com.mweser.affordabilitytracker.activity.utils.ActivityUtils;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onCreateSetup();
        setUpUiElements();
    }

    // TODO: 9/24/17 In next version, change to sliders and other useful UI elements
    private void setUpUiElements()
    {
        Settings settings = new Settings(this, getApplicationContext(), getBaseContext());

        settings.initFab(R.id.fab, MainActivity.class);

        // ACCENT_HUE, CREDIT_ALIGNED, DEFAULT_FREQUENCY, DEFAULT_FREQUENCY_TYPE, SECURITY_PIN, START_DATE, DATE_RANGE, END_DATE
        settings.initTextFields(R.id.txtSettingsEndDate,
                R.id.txtSettingsSecurityPin,
                R.id.txtSettingsDefaultFrequency,
                R.id.txtSettingsDefaultFrequencyUnit,
                R.id.txtSettingsDateRange,
                R.id.txtSettingsDateRangeUnit,
                R.id.txtSettingsAccentHue);

        settings.schemaItemOrder(Schema.Tables.settings,
                END_DATE,
                SECURITY_PIN,
                DEFAULT_FREQUENCY,
                DEFAULT_FREQUENCY_TYPE,
                DATE_RANGE,
                RANGE_UNIT,
                ACCENT_HUE);

        settings.initToggleButtons(R.id.toggleSettingCreditAligned, R.id.toggleSettingDebitAligned);
    }

    private void onCreateSetup()
    {
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed()
    {
        ActivityUtils.toggleDrawer(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        ActivityUtils.navBarSwitch(getApplicationContext(), getBaseContext(), this, item);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
