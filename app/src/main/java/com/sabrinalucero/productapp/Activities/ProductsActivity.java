package com.sabrinalucero.productapp.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sabrinalucero.productapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

  private List<String> names;
  private GridView gridView;


  private int counter = 0;

  private MyAdapter myAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.products_grid);

    gridView = (GridView) findViewById(R.id.gridView);

    //creamos los datos de la lista- datos que muestro
    names = new ArrayList<String>();
    names.add("LÁCTEOS");
    names.add("FRUTAS");
    names.add("VERDURAS");
    names.add("CARNICERIA");
    names.add("LIMPIEZA");
    names.add("PANADERIA");
    names.add("BEBIDAS");
    names.add("OTROS");

    final Bundle b = getIntent().getExtras();

    //mostrara el contenido segun el evento que presione en cada item del LV
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = b.get("CATEGORY_ID") + "-clicked : " + names.get(position);
        Toast.makeText(ProductsActivity.this, text, Toast.LENGTH_SHORT).show();
      }
    });

    //enlazamos con nuestro adaptador personalizado
   myAdapter = new MyAdapter (this, R.layout.product_item, names);
    gridView.setAdapter(myAdapter);

    //registro de un context menu
    registerForContextMenu(gridView);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()){
      case R.id.add_item:
        //Se agrega un nuevo nombre
        this.names.add("Added  n°"+ (++counter));
        //este metodo hace que se refresque, habiendo sumado el valor anterior, notifique al adapter y se refresque
        this.myAdapter.notifyDataSetChanged();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }

  //infla el layout context menu
  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    MenuInflater inflater = getMenuInflater();

    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
    //Accedemos al elemento seleccionado para saber cual estamos por borrar
    menu.setHeaderTitle(this.names.get(info.position));

    inflater.inflate(R.menu.context_menu, menu);

  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

    switch(item.getItemId()){
      case R.id.delete_item:

        //Se borra nombre-item clickeado, accediendo desde info a la posicion del elemento que fue seleccionado
        this.names.remove(info.position);
        //este metodo hace que se refresque, habiendo sumado el valor anterior, notifique al adapter y se refresque
        this.myAdapter.notifyDataSetChanged();

        return true;
      case R.id.adding_item:
        this.names.add("Added  n°"+ (++counter));
        //este metodo hace que se refresque, habiendo sumado el valor anterior, notifique al adapter y se refresque
        this.myAdapter.notifyDataSetChanged(); //TODO ver si hace falta
      default:
        return super.onContextItemSelected(item);
    }
  }
}

