//package com.mweser.affordabilitytracker.activity.utils;
//
//import static com.mweser.affordabilitytracker.activity.utils.DataEntryActivity.TextFields.POINTS;
//import static com.mweser.affordabilitytracker.activity.utils.DataEntryActivity.activity;
//import static com.mweser.affordabilitytracker.activity.utils.DataEntryActivity.baseContext;
//import static com.mweser.affordabilitytracker.activity.utils.DataEntryActivity.saveFieldsToDatabase;
//import static com.mweser.affordabilitytracker.activity.utils.DataEntryActivity.textInputs;
//import static com.mweser.affordabilitytracker.activity.utils.DataEntryActivity.toggleButtons;
//import static com.mweser.affordabilitytracker.database.schema.Schema.accounts.AMOUNT_NEXT_STATEMENT;
//import static com.mweser.affordabilitytracker.database.schema.Schema.accounts.PAYMENT_DATE;
//import static com.mweser.affordabilitytracker.database.schema.Schema.accounts.STATEMENT_DATE;
//import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountType.CREDIT;
//import static com.mweser.affordabilitytracker.projection_app.event_structures.RecurEventValueTypes.AccountType.DEBIT;
//
//import java.util.List;
//
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ToggleButton;
//
//public class ActivityUi
//{
//    static View.OnClickListener generateCreditListener()
//    {
//        return new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                setCreditToggleValue(true);
//                setCreditFieldVisibilty(View.VISIBLE);
//            }
//        };
//    }
//
//    static List<ToggleButton> setToggleBtnProperties()
//    {
//        setCreditToggleValue(true);
//
//        toggleButtons.get(CREDIT.ordinal())
//                .setOnClickListener(generateCreditListener());
//
//        toggleButtons.get(DEBIT.ordinal())
//                .setOnClickListener(generateDebitListener());
//
//        return toggleButtons;
//    }
//
//    static View.OnClickListener generateDebitListener()
//    {
//        return new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                setCreditToggleValue(false);
//                setCreditFieldVisibilty(View.INVISIBLE);
//            }
//        };
//    }
//
//    static View.OnClickListener generateFabListener(final Class<?> nextActivityClass)
//    {
//        return new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                saveFieldsToDatabase();
//                activity.finish();
//                ActivityUtils.startActivity(baseContext, activity, nextActivityClass);
//            }
//        };
//    }
//
//    static void setCreditToggleValue(boolean isChecked)
//    {
//        toggleButtons.get(CREDIT.ordinal())
//                .setChecked(isChecked);
//        toggleButtons.get(DEBIT.ordinal())
//                .setChecked(!isChecked);
//    }
//
//    static void setCreditFieldVisibilty(int visibility)
//    {
//        setFieldVisibility(textInputs,
//                visibility,
//                STATEMENT_DATE,
//                PAYMENT_DATE,
//                AMOUNT_NEXT_STATEMENT,
//                POINTS);
//    }
//
//    static void setFieldVisibility(List<EditText> list, int visibility, Enum<?>... fieldEnums)
//    {
//        for (Enum<?> field : fieldEnums)
//        {
//            list.get(field.ordinal())
//                    .setVisibility(visibility);
//        }
//    }
//}
