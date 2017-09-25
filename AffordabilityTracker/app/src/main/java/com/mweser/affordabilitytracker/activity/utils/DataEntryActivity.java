package com.mweser.affordabilitytracker.activity.utils;

import static com.mweser.affordabilitytracker.activity.utils.ActivityUtils.generateEnumArrayList;
import static com.mweser.affordabilitytracker.activity.utils.ActivityUtils.generateListOfUiElements;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

public abstract class DataEntryActivity
{
    protected Activity activity;
    protected Context appContext;
    protected Context baseContext;
    protected Schema.Tables schemaTable;
    protected List<Enum<?>> schemaElementsAddedOrder;
    protected List<String> textFieldsToSave;
    protected List<EditText> textInputs;

    public DataEntryActivity(Activity activity, Context appContext, Context baseContext)
    {
        this.activity = activity;
        this.appContext = appContext;
        this.baseContext = baseContext;
    }

    protected List<String> populateFieldsFromUiElements()
    {
        textFieldsToSave = new ArrayList<>();

        for (EditText text : textInputs)
        {
            textFieldsToSave.add(text.getText()
                    .toString());
        }

        populateAdditionalUiElements();
        return textFieldsToSave;
    }

    protected abstract void populateAdditionalUiElements();

    protected void saveFieldsToDatabase()
    {
        ActivityUtils.insertUiFieldsToDatabase(appContext,
                schemaTable,
                populateFieldsFromUiElements(),
                schemaElementsAddedOrder);
    }

    public void schemaItemOrder(Schema.Tables schemaTable, Enum<?>... schemaAddedOrder)
    {
        this.schemaTable = schemaTable;
        schemaElementsAddedOrder = generateEnumArrayList(schemaAddedOrder);
    }

    public void initTextFields(int... textFields)
    {
        textInputs = generateListOfUiElements(activity, textFields);
    }

    public void initFab(int id, Class<?> nextClass)
    {
        activity.findViewById(id)
                .setOnClickListener(generateFabListener(nextClass));
    }

    protected View.OnClickListener generateFabListener(final Class<?> nextActivityClass)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveFieldsToDatabase();
                activity.finish();
                ActivityUtils.startActivity(baseContext, activity, nextActivityClass);
            }
        };
    }

    protected void setContexts(Activity currentActivity, Context applicationContext,
            Context baseContxt)
    {
        activity = currentActivity;
        appContext = applicationContext;
        baseContext = baseContxt;
    }
}
