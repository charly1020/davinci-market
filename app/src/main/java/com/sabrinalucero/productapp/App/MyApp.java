package com.sabrinalucero.productapp.App;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Sabrina on 30/06/2017.
 */

public class MyApp extends Application{

  @Override
  public void onCreate() {
    super.onCreate();

    //Permite que el splash dure 3"
    SystemClock.sleep(3000);
  }
}
