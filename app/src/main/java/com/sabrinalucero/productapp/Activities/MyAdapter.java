package com.sabrinalucero.productapp.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static com.sabrinalucero.productapp.R.id.textView;

/**
 * Created by Sabrina on 30/06/2017.
 */

public class MyAdapter extends BaseAdapter {


  private Context context;
  private int layout;
  private List<String> names;

  public MyAdapter(Context context, int layout, List<String> names) {
    this.context = context;
    this.layout = layout;
    this.names = names;
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
    ViewHolder holder;

    if (convertView == null) {
      //la inflamos, vista con el layout
      LayoutInflater layoutInflater = LayoutInflater.from(this.context);
      //recuperamos la vista y la guardamos en el convert view
      convertView = layoutInflater.inflate(this.layout, null);

      //creamos una nueva instancia del holder
      holder = new ViewHolder();

      //con esto añadimos esta referencia en una nueva instancia en el holder
      //en el objeto holder guardamos a la referencia del text view
      //referenciamos el elemento a modificar y lo rellenamos

      holder.nameTextView = (TextView) convertView.findViewById(textView);

      //seteamos un tag con una instancia del objeto - sino podria ser clave valor si quiero pasar mas referencias.
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    //devuelve el valor actual segun la posicion
    String currentName = names.get(position);
    //currentName = (String) getItem(position);

    //referenciamos el elemento a modificar y lo rellenamos
    holder.nameTextView.setText(currentName);


    //devolvemos vista inflada y modificada
    return convertView;

  }

  static class ViewHolder {

    private TextView nameTextView;


  }
}
