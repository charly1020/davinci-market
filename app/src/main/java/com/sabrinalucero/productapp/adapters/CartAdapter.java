package com.sabrinalucero.productapp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sabrinalucero.productapp.R;
import com.sabrinalucero.productapp.model.CartMarket;
import com.sabrinalucero.productapp.model.Category;

import java.util.List;

/**
 * Created by sabrina on 6/30/17.
 */

public class CartAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private List<CartMarket> names;

    public CartAdapter(Context context, int layout, List<CartMarket> names) {
        this.context = context;
        this.layout = layout;
        this.names= names;
    }

    //cuantas veces va a iterar sobre la coleccion, devuelve el numero de items que va a dibujar en el LV
    @Override
    public int getCount() {
        return this.names.size();
    }

    //obtiene un item de la coleccion
    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }


    @Override
    public long getItemId(int id) {
        return id;
    }

    //obtiene cada item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View Holder Pattern
        CartAdapter.ViewHolder holder;

        if (convertView == null) {
            //la inflamos, vista con el layout
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            //recuperamos la vista y la guardamos en el convert view
            convertView = layoutInflater.inflate(this.layout, null);

            //creamos una nueva instancia del holder
            holder = new CartAdapter.ViewHolder();

            //con esto añadimos esta referencia en una nueva instancia en el holder
            //en el objeto holder guardamos a la referencia del text view
            //referenciamos el elemento a modificar y lo rellenamos

            holder.cartTextView = (TextView) convertView.findViewById(R.id.cateoryTextView);
            holder.cartIconView = (ImageView) convertView.findViewById(R.id.categoryImageView);

            //seteamos un tag con una instancia del objeto - sino podria ser clave valor si quiero pasar mas referencias.
            convertView.setTag(holder);
        } else {
            holder = (CartAdapter.ViewHolder) convertView.getTag();
        }

        //devuelve el valor actual segun la posicion
        String currentName = names.get(position).getName();
        int resourceId = context.getResources().getIdentifier(currentName, "drawable", context.getPackageName());

        if(resourceId > 0) {
            Drawable myDrawable = context.getResources().getDrawable(resourceId);
            holder.cartIconView.setImageDrawable(myDrawable);
        }

        //referenciamos el elemento a modificar y lo rellenamos
        holder.cartTextView.setText(currentName);


        //devolvemos vista inflada y modificada
        return convertView;

    }

    static class ViewHolder {
        private TextView cartTextView;
        private ImageView cartIconView;
    }
}