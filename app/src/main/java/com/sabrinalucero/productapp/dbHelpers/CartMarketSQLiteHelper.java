package com.sabrinalucero.productapp.dbHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sabrina on 6/30/17.
 */

public class CartMarketSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE CartList(id INTEGER PRIMARY KEY NOT NULL, name TEXT, description TEXT, cart_date DATE, status BOOLEAN)";

    public CartMarketSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void clearTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS CartList");
        db.execSQL(sqlCreate);
    }

}