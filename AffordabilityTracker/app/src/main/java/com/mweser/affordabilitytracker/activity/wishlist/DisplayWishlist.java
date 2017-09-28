package com.mweser.affordabilitytracker.activity.wishlist;

import static com.mweser.affordabilitytracker.database.schema.Schema.Tables.wishlist;

import com.mweser.affordabilitytracker.activity.utils.StringListActivity;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;

public class DisplayWishlist extends StringListActivity
{
    public DisplayWishlist(Activity activity, Context appContext, Context baseContext)
    {
        super(activity, appContext, baseContext);
    }

    @Override
    protected String queryAndDisplayList()
    {
        return queryTable(wishlist,
                Schema.wishlist.NAME,
                Schema.wishlist.AMOUNT,
                Schema.wishlist.CALCULATED_DATE);
    }
}
