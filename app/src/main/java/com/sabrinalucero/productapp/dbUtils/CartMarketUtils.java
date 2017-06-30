package com.sabrinalucero.productapp.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sabrinalucero.productapp.dbHelpers.CartMarketSQLiteHelper;
import com.sabrinalucero.productapp.model.CartMarket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by charly on 6/30/17.
 */

public class CartMarketUtils {

    private CartMarketSQLiteHelper cartHelper;
    private SQLiteDatabase db;

    public void initDb(Context context) {
        cartHelper = new CartMarketSQLiteHelper(context, "dbDavinci1", null, 1);
        db = cartHelper.getWritableDatabase();

        int cant = getAll(context).size();
        if(cant == 0)
         cartHelper.clearTable(db);

    }

    public void clearDb(){
        cartHelper.clearTable(db);
    }

    //"CREATE TABLE CartList(id INTEGER PRIMARY KEY NOT NULL, name TEXT, description TEXT, fecha DATE, status BOOLEAN, )";
    public void createItem(CartMarket cartItem){

        if(db != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", cartItem.getId());
            contentValues.put("name", cartItem.getName());
            contentValues.put("description", cartItem.getDescription());
            contentValues.put("cart_date", cartItem.getCartDate().toString());
            contentValues.put("status", cartItem.getStatus());

            db.insert("CartList", null, contentValues);
        }

    }

    public List<CartMarket> getAll(Context context){

        if(db == null) {
            cartHelper = new CartMarketSQLiteHelper(context, "dbDavinci1", null, 1);
            db = cartHelper.getWritableDatabase();
        }
        Cursor cursor = null;
        try {

         cursor = db.rawQuery("SELECT * FROM CartList", null);
        } catch (Exception e){
            System.out.print(e.getMessage());
        }

        List<CartMarket> cartItems = new ArrayList<>();
        SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        if (cursor.moveToFirst()) {
            while(cursor.isAfterLast()== false){

                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                Date cartDate = null;
                try {
                    cartDate = parser.parse(cursor.getString(cursor.getColumnIndex("cart_date")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Boolean status = new Boolean( cursor.getString(cursor.getColumnIndex("status")));


                CartMarket cartItem = new CartMarket();
                cartItem.setId(id);
                cartItem.setName(name);
                cartItem.setDescription(description);
                cartItem.setCartDate(cartDate);
                cartItem.setStatus(status);


                cartItems.add(cartItem);
                cursor.moveToNext();
            }
        }
        return cartItems;

    }

    public void deleteItem(int listItemId){
        ContentValues contentValues = new ContentValues();
        String idToDelete = "id = " + listItemId;
        db.delete("CartList", idToDelete , null );
    }

}