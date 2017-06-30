package com.sabrinalucero.productapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sabrinalucero.productapp.R;
import com.sabrinalucero.productapp.Utils.Util;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {


  private SharedPreferences prefs;

  private ListView listView;
  private List<String> names;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //accedemos al mismo archivo llamandolo del mismo modo
    prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

    listView = (ListView) findViewById(R.id.listView);

    //creamos los datos de la lista datos que muestro
    names = new ArrayList<>();

    names.add("LÁCTEOS");
    names.add("FRUTAS");
    names.add("VERDURAS");
    names.add("CARNICERIA");
    names.add("LIMPIEZA");
    names.add("PANADERIA");
    names.add("BEBIDAS");
    names.add("OTROS");

    //Adaptador, esto es una forma visual de mostrar los datos
   // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);

    //enlazamos el adaptador con nuestro listView
  //   listView.setAdapter(adapter);


    //mostrara el contenido segun el evento que presione en cada item del LV
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListActivity.this, "clicked : " + names.get(position), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ListActivity.this, GridActivity.class);
        startActivity(intent);

      }
    });

    //enlazamos con nuestro adaptador personalizado
    MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);
    listView.setAdapter(myAdapter);

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    //invocar al menu sino no va a aparecer
    getMenuInflater().inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

int id = item.getItemId();
    switch (item.getItemId()) {
      case R.id.menu_logout:
        logOut();
        return true;
      case R.id.menu_forget_logout:
        Util.removeSharedPreferences(prefs);
        logOut();
        return true;
      default:
        return super.onOptionsItemSelected(item);
  }
}

  private void logOut() {
    Intent intent = new Intent(this, LoginActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
  }


}

