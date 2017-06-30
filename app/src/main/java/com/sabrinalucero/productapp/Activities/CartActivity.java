package com.sabrinalucero.productapp.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.sabrinalucero.productapp.R;
import com.sabrinalucero.productapp.adapters.CartAdapter;
import com.sabrinalucero.productapp.adapters.CategoryAdapter;
import com.sabrinalucero.productapp.dbUtils.CartMarketUtils;
import com.sabrinalucero.productapp.model.CartMarket;
import com.sabrinalucero.productapp.model.Category;

import java.util.List;


public class CartActivity extends AppCompatActivity {

    private ListView listView;
    private List<CartMarket> carts;
    private CartMarketUtils cartListUtils = new CartMarketUtils();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        List<CartMarket> carts = cartListUtils.getAll(this);

        //enlazamos con nuestro adaptador personalizado
        listView = (ListView) findViewById(R.id.cartListView);
        CartAdapter myAdapter = new CartAdapter(this, R.layout.cart_item, carts);
        //listView.setAdapter(myAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //invocar al menu sino no va a aparecer
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
