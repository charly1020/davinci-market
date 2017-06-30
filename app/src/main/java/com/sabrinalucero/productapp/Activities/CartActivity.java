package com.sabrinalucero.productapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.sabrinalucero.productapp.R;
import com.sabrinalucero.productapp.adapters.CartAdapter;
import com.sabrinalucero.productapp.dbUtils.CartMarketUtils;
import com.sabrinalucero.productapp.model.CartMarket;
import com.sabrinalucero.productapp.model.Product;

import java.util.Date;
import java.util.List;


public class CartActivity extends AppCompatActivity {

    private ListView listView;
    private List<CartMarket> carts;
    private CartMarketUtils cartListUtils = new CartMarketUtils();
    private CartAdapter myAdapter;
    private CartMarket currentContextualProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list);

        carts = cartListUtils.getAll(this);

        //enlazamos con nuestro adaptador personalizado
        listView = (ListView) findViewById(R.id.cartListView);
        myAdapter = new CartAdapter(this, R.layout.cart_item, carts);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = carts.get(position).getName().toUpperCase();
            }
        });

        registerForContextMenu(listView);

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

        currentContextualProduct = this.carts.get(info.position);

        //Accedemos al elemento seleccionado para saber cual estamos por borrar
        String title = this.carts.get(info.position).getName();
        menu.setHeaderTitle(title);

        inflater.inflate(R.menu.context_cart, menu);

    }


    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        switch(menuItem.getItemId()){
            case R.id.delete_item:
                //Se borra nombre-item clickeado, accediendo desde info a la posicion del elemento que fue seleccionado
                this.carts.remove(info.position);
                //este metodo hace que se refresque, habiendo sumado el valor anterior, notifique al adapter y se refresque
                deleteItemCart(currentContextualProduct);
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    private void deleteItemCart(CartMarket product) {
        cartListUtils.deleteItem(product.getId());
    }

}
