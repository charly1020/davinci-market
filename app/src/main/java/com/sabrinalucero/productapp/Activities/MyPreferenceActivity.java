package com.sabrinalucero.productapp.Activities;

import android.preference.PreferenceActivity;
import com.sabrinalucero.productapp.R;

import java.util.List;

/**
 * Created by sabrina on 6/30/17.
 */


public class MyPreferenceActivity extends PreferenceActivity
{
    @Override
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.header_preferences, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName)
    {
        return MyPreferenceFragment.class.getName().equals(fragmentName);
    }
}