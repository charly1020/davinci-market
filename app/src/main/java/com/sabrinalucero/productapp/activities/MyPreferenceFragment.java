package com.sabrinalucero.productapp.activities;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.sabrinalucero.productapp.R;

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
    }
}
