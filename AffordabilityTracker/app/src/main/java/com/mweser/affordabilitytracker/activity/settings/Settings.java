package com.mweser.affordabilitytracker.activity.settings;

import java.util.List;

import com.mweser.affordabilitytracker.activity.utils.ActivityUtils;
import com.mweser.affordabilitytracker.activity.utils.DataEntryActivity;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ToggleButton;

public class Settings extends DataEntryActivity
{
    List<ToggleButton> toggleButtons;

    enum ToggleButtonTypes
    {
        CREDIT_ALIGNED, DEBIT_ALIGNED
    }

    public Settings(Activity activity, Context appContext, Context baseContext)
    {
        super(activity, appContext, baseContext);

        // TODO: 9/28/17 Change to UPDATE instead of INSERT
    }

    @Override
    protected void populateAdditionalUiElements()
    {
        String typeValue = "FALSE";

        if (toggleButtons.get(Schema.settings.CREDIT_ALIGNED.ordinal())
                .isChecked())
        {
            typeValue = "TRUE";
        }

        textFieldsToSave.add(typeValue);
        schemaElementsAddedOrder.add(Schema.settings.CREDIT_ALIGNED);
    }

    public void initToggleButtons(int... toggleBtns)
    {
        toggleButtons = ActivityUtils.generateListOfUiElements(activity, toggleBtns);
        setToggleBtnProperties();
    }

    List<ToggleButton> setToggleBtnProperties()
    {
        setCreditAlignedToggleChecked(true);

        toggleButtons.get(ToggleButtonTypes.CREDIT_ALIGNED.ordinal())
                .setOnClickListener(generateCreditAlignedListener());

        toggleButtons.get(ToggleButtonTypes.DEBIT_ALIGNED.ordinal())
                .setOnClickListener(generateDebitAlignedListener());

        return toggleButtons;
    }

    View.OnClickListener generateCreditAlignedListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setCreditAlignedToggleChecked(true);
            }
        };
    }

    View.OnClickListener generateDebitAlignedListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setCreditAlignedToggleChecked(false);
            }
        };
    }

    void setCreditAlignedToggleChecked(boolean isChecked)
    {
        toggleButtons.get(ToggleButtonTypes.CREDIT_ALIGNED.ordinal())
                .setChecked(isChecked);
        toggleButtons.get(ToggleButtonTypes.DEBIT_ALIGNED.ordinal())
                .setChecked(!isChecked);
    }
}
