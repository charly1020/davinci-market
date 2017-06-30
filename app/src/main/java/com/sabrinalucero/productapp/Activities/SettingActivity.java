package com.sabrinalucero.productapp.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.sabrinalucero.productapp.R;

/**
 * Created by Sabrina on 30/06/2017.
 */

public class SettingActivity extends PreferenceActivity {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {


    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.settings);
  }
}







