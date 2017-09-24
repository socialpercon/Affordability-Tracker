package com.mweser.affordabilitytracker.activity.create_account;

import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TextFields.AMOUNT_NEXT_STATEMENT;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TextFields.PAYMENT_DATE;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TextFields.POINTS;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TextFields.STATEMENT_DATE;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TypeToggles.CREDIT;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TypeToggles.DEBIT;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.activity;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.baseContext;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.saveFieldsToDatabase;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.textInputs;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.toggleButtons;

import java.util.List;

import com.mweser.affordabilitytracker.activity.ActivityUtils;

import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateAccountUi
{
    static View.OnClickListener generateCreditListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setCreditToggleValue(true);
                setCreditFieldVisibilty(View.VISIBLE);
            }
        };
    }

    static List<ToggleButton> setToggleBtnProperties()
    {
        setCreditToggleValue(true);

        toggleButtons.get(CREDIT.ordinal())
                .setOnClickListener(generateCreditListener());

        toggleButtons.get(DEBIT.ordinal())
                .setOnClickListener(generateDebitListener());

        return toggleButtons;
    }

    static View.OnClickListener generateDebitListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setCreditToggleValue(false);
                setCreditFieldVisibilty(View.INVISIBLE);
            }
        };
    }

    static View.OnClickListener generateFabListener(final Class<?> nextActivityClass)
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

    static void setCreditToggleValue(boolean isChecked)
    {
        toggleButtons.get(CREDIT.ordinal())
                .setChecked(isChecked);
        toggleButtons.get(DEBIT.ordinal())
                .setChecked(!isChecked);
    }

    static void setCreditFieldVisibilty(int visibility)
    {
        setFieldVisibility(textInputs,
                visibility,
                STATEMENT_DATE,
                PAYMENT_DATE,
                AMOUNT_NEXT_STATEMENT,
                POINTS);
    }

    static void setFieldVisibility(List<EditText> list, int visibility, Enum<?>... fieldEnums)
    {
        for (Enum<?> field : fieldEnums)
        {
            list.get(field.ordinal())
                    .setVisibility(visibility);
        }
    }
}
