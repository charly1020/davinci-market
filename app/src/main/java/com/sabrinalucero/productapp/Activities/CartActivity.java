package com.sabrinalucero.productapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sabrinalucero.productapp.R;
import com.sabrinalucero.productapp.adapters.CartAdapter;
import com.sabrinalucero.productapp.dbUtils.CartMarketUtils;
import com.sabrinalucero.productapp.model.CartMarket;

import java.util.List;


public class CartActivity extends AppCompatActivity {

    private ListView listView;
    private List<CartMarket> carts;
    private CartMarketUtils cartListUtils = new CartMarketUtils();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list);

        List<CartMarket> carts = cartListUtils.getAll(this);

        //enlazamos con nuestro adaptador personalizado
        listView = (ListView) findViewById(R.id.cartListView);
        CartAdapter myAdapter = new CartAdapter(this, R.layout.cart_item, carts);
        listView.setAdapter(myAdapter);
    }

}
