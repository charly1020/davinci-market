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
import com.sabrinalucero.productapp.adapters.ProductAdapter;
import com.sabrinalucero.productapp.dbUtils.CategoryUtils;
import com.sabrinalucero.productapp.dbUtils.ProductsUtils;
import com.sabrinalucero.productapp.model.Category;
import com.sabrinalucero.productapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

  private List<Product> names;
  private GridView gridView;


  private int counter = 0;

  private ProductAdapter myAdapter;
  private List<Category> categories;
  private List<Product> products;
  private CategoryUtils categoryUtil = new CategoryUtils();
  private ProductsUtils productsUtils = new ProductsUtils();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.products_grid);

    gridView = (GridView) findViewById(R.id.gridView);

    categories = categoryUtil.getAll(this);

    int categoryId = getIntent().getIntExtra("CATEGORY_ID",5);

    products = productsUtils.getProductsById(categoryId, this);
    //products = productsUtils.getAll();

    //creamos los datos de la lista- datos que muestro
    //names = new ArrayList<Product>();

    final Bundle b = getIntent().getExtras();

    //mostrara el contenido segun el evento que presione en cada item del LV
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = b.get("CATEGORY_ID") + "-clicked : " + products.get(position);
        Toast.makeText(ProductsActivity.this, text, Toast.LENGTH_SHORT).show();
      }
    });

    //enlazamos con nuestro adaptador personalizado
    myAdapter = new ProductAdapter (this, R.layout.product_item, products);
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
        //this.names.add("Added  n°"+ (++counter));
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
    String title = this.products.get(info.position).getName();
    menu.setHeaderTitle(title);

    inflater.inflate(R.menu.context_menu, menu);

  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

    switch(item.getItemId()){
      case R.id.delete_item:

        //Se borra nombre-item clickeado, accediendo desde info a la posicion del elemento que fue seleccionado
        this.products.remove(info.position);
        //este metodo hace que se refresque, habiendo sumado el valor anterior, notifique al adapter y se refresque
        this.myAdapter.notifyDataSetChanged();

        return true;
      case R.id.adding_item:
        //this.names.add(new Product(90, ""));
        //este metodo hace que se refresque, habiendo sumado el valor anterior, notifique al adapter y se refresque
        this.myAdapter.notifyDataSetChanged(); //TODO ver si hace falta
      default:
        return super.onContextItemSelected(item);
    }
  }
}

