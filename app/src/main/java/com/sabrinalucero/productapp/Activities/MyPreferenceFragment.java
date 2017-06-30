package com.sabrinalucero.productapp.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.CheckBox;

import com.sabrinalucero.productapp.R;

import java.util.Map;

/**
 * Created by sabrina on 6/30/17.
 */

public class MyPreferenceFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference);

        final SharedPreferences pref = getActivity().getPreferences(0);

        final CheckBoxPreference checkboxPref = (CheckBoxPreference)getPreferenceManager().findPreference("checkBoxPref");

        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference,
                                              Object newValue) {
                Context context = getActivity();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("checkBoxPref",newValue.toString());
                //editor.apply();
                return true;
            }

        });

    }


}
