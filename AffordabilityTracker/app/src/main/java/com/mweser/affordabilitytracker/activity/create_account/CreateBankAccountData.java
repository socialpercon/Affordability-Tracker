package com.mweser.affordabilitytracker.activity.create_account;

import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TextFields.AMOUNT_NEXT_STATEMENT;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TextFields.PAYMENT_DATE;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TextFields.POINTS;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TextFields.STATEMENT_DATE;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TypeToggles.CREDIT;
import static com.mweser.affordabilitytracker.activity.create_account.CreateBankAccountData.TypeToggles.DEBIT;
import static com.mweser.affordabilitytracker.activity.utils.ActivityUtils.generateListOfUiElements;

import java.util.ArrayList;
import java.util.List;

import com.mweser.affordabilitytracker.activity.utils.DataEntryActivity;
import com.mweser.affordabilitytracker.database.schema.Schema;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateBankAccountData extends DataEntryActivity
{
    private List<ToggleButton> toggleButtons;

    enum TypeToggles
    {
        CREDIT, DEBIT
    }

    enum TextFields
    {
        NAME, PAYMENT_DATE, STATEMENT_DATE, AMOUNT_NEXT_STATEMENT, TOTAL_BALANCE, POINTS
    }

    public CreateBankAccountData(Activity activity, Context appContext, Context baseContext)
    {
        super(activity, appContext, baseContext);
    }

    public void initToggleButtons(int... toggleBtns)
    {
        toggleButtons = generateListOfUiElements(activity, toggleBtns);
        toggleButtons = setToggleBtnProperties();
    }

    public List<String> populateFieldsFromUiElements()
    {
        textFieldsToSave = new ArrayList<>();

        for (EditText text : textInputs)
        {
            textFieldsToSave.add(text.getText()
                    .toString());
        }

        addAccountType();

        return textFieldsToSave;
    }

    private void addAccountType()
    {
        if (toggleButtons.get(CREDIT.ordinal())
                .isChecked())
        {
            textFieldsToSave.add("CREDIT");
            schemaElementsAddedOrder.add(Schema.accounts.TYPE);
        }
        else
        {
            textFieldsToSave.add("DEBIT");
            schemaElementsAddedOrder.add(Schema.accounts.TYPE);
        }
    }

    private View.OnClickListener generateCreditListener()
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

    private List<ToggleButton> setToggleBtnProperties()
    {
        setCreditToggleValue(true);

        toggleButtons.get(CREDIT.ordinal())
                .setOnClickListener(generateCreditListener());

        toggleButtons.get(DEBIT.ordinal())
                .setOnClickListener(generateDebitListener());

        return toggleButtons;
    }

    private View.OnClickListener generateDebitListener()
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

    private void setCreditToggleValue(boolean isChecked)
    {
        toggleButtons.get(CREDIT.ordinal())
                .setChecked(isChecked);
        toggleButtons.get(DEBIT.ordinal())
                .setChecked(!isChecked);
    }

    private void setCreditFieldVisibilty(int visibility)
    {
        setFieldVisibility(textInputs,
                visibility,
                STATEMENT_DATE,
                PAYMENT_DATE,
                AMOUNT_NEXT_STATEMENT,
                POINTS);
    }

    private void setFieldVisibility(List<EditText> list, int visibility, Enum<?>... fieldEnums)
    {
        for (Enum<?> field : fieldEnums)
        {
            list.get(field.ordinal())
                    .setVisibility(visibility);
        }
    }
}
