package com.sabrinalucero.productapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.sabrinalucero.productapp.R;
import com.sabrinalucero.productapp.Utils.Util;
import com.sabrinalucero.productapp.adapters.CategoryAdapter;
import com.sabrinalucero.productapp.dbUtils.CategoryUtils;
import com.sabrinalucero.productapp.dbUtils.ProductsUtils;
import com.sabrinalucero.productapp.model.Category;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {


  private SharedPreferences prefs;

  private ListView listView;
  private List<Category> categories;
  private CategoryUtils categoryUtil = new CategoryUtils();
  private ProductsUtils productsUtils = new ProductsUtils();



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.categories_list);

    //accedemos al mismo archivo llamandolo del mismo modo
    prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    listView = (ListView) findViewById(R.id.listView);

    categoryUtil.initDb(this);
    categories = categoryUtil.getAll(this);
    productsUtils.initDb(this, categories);

    categories = categoryUtil.getAll(this);


    //mostrara el contenido segun el evento que presione en cada item del LV
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(CategoryActivity.this, categories.get(position).getName().toUpperCase(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
        intent.putExtra("CATEGORY_ID", categories.get(position).getId());
        startActivity(intent);

      }
    });

    //enlazamos con nuestro adaptador personalizado
    CategoryAdapter myAdapter = new CategoryAdapter(this, R.layout.category_item, categories);
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
    Intent intent;
    int id = item.getItemId();

    switch (id) {
      case R.id.menu_logout:
        logOut();
        return true;
      case R.id.menu_forget_logout:
        Util.removeSharedPreferences(prefs);
        logOut();
        return true;
      case R.id.menu_aboutMe:
        intent = new Intent(CategoryActivity.this, AboutMeActivity.class);
        startActivity(intent);
        return true;
      case R.id.preferences:
      {
        Intent intent1 = new Intent();
        intent1.setClassName(this, "com.sabrinalucero.productapp.Activities.MyPreferenceActivity");
        startActivity(intent1);
        return true;
      }
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

