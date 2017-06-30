package com.sabrinalucero.productapp.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.sabrinalucero.productapp.Activities.LoginActivity;
import com.sabrinalucero.productapp.Activities.ListActivity;
import com.sabrinalucero.productapp.Utils.Util;

public class SplashActivity extends AppCompatActivity {

  private SharedPreferences prefs;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //obtenemos la instancia
    prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);


    Intent intentLogin = new Intent(this, LoginActivity.class);
    Intent intentMain = new Intent(this, ListActivity.class);

    //si no está vacio el email y no esta vacia la passw
    if(!TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) &&
            !TextUtils.isEmpty(Util.getUserPasswordPrefs(prefs))){
      startActivity(intentMain);
    }else{
      startActivity(intentLogin);
    }

    //fin de la instancia para evitar que se pueda volver -
    // esto tb se puede hacer con flags
    finish();

  }



}

