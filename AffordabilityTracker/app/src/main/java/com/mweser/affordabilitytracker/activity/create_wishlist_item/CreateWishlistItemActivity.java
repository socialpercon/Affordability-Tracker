package com.mweser.affordabilitytracker.activity.create_wishlist_item;

import com.mweser.affordabilitytracker.R;
import com.mweser.affordabilitytracker.activity.wishlist.WishlistActivity;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CreateWishlistItemActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onCreateSetup();
        setUpUiElements();
    }

    private void setUpUiElements()
    {
        CreateWishlistItem createWishlistItem = new CreateWishlistItem(this, getApplicationContext(), getBaseContext());

        createWishlistItem.initFab(R.id.fab, WishlistActivity.class);

        // NAME, AMOUNT, PRIORITY_RANKING, DESIRED_DATE, CALCULATED_DATE, IRRELEVANCY_DATE, FREQUENCY, FREQUENCY_TYPE, RECUR_TYPE, AMOUNT_TYPE
        createWishlistItem.initTextFields(
                R.id.txtWishlistItemName,
                R.id.txtWishlistAmount,
                R.id.txtWishlistDesiredDate,
                R.id.txtWishlistIrrelevancyDate,
                R.id.txtWishlistPriority,
                R.id.txtWishlistAccountName);

        createWishlistItem.schemaItemOrder(Schema.Tables.wishlist,
                Schema.wishlist.NAME,
                Schema.wishlist.AMOUNT,
                Schema.wishlist.DESIRED_DATE,
                Schema.wishlist.IRRELEVANCY_DATE,
                Schema.wishlist.PRIORITY_RANKING,
                Schema.wishlist.ACCOUNT);

    }


    private void onCreateSetup()
    {
        setContentView(R.layout.activity_create_wishlist_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
