package com.sabrinalucero.productapp.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sabrinalucero.productapp.dbHelpers.CategorySQLiteHelper;
import com.sabrinalucero.productapp.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charly on 6/30/17.
 */

public class CategoryUtils {

    private CategorySQLiteHelper categoryHelper;
    private SQLiteDatabase db;

    public void initDb(Context context) {
        categoryHelper = new CategorySQLiteHelper(context, "dbDavinci1", null, 1);
        db = categoryHelper.getWritableDatabase();

        categoryHelper.clearTable(db);

        Category greenGrossery = new Category();
        greenGrossery.setId(5);
        greenGrossery.setName("verduleria");
        greenGrossery.setDescription("todo verde");

        Category butchery = new Category();
        butchery.setId(6);
        butchery.setName("carniceria");
        butchery.setDescription("calne");

        Category backery = new Category();
        backery.setId(7);
        backery.setName("panaderia");
        backery.setDescription("pancitos");

        Category cleaning = new Category();
        cleaning.setId(8);
        cleaning.setName("limpieza");
        cleaning.setDescription("articulos");

        Category dairyProducts = new Category();
        dairyProducts.setId(9);
        dairyProducts.setName("lacteos");
        dairyProducts.setDescription("muuu");

        Category drinks = new Category();
        drinks.setId(10);
        drinks.setName("bebidas");
        drinks.setDescription("todo coca");


        create(greenGrossery);
        create(butchery);
        create(backery);
        create(cleaning);
        create(dairyProducts);
        create(drinks);
    }

    public void create(Category category){
        if(db != null){
            ContentValues newRegistry = new ContentValues();
            newRegistry.put("id", category.getId());
            newRegistry.put("name", category.getName());
            newRegistry.put("description", category.getDescription());

            db.insert("Category", null, newRegistry);
        }
    }

    public List<Category> getAll(){
        Cursor cursor = db.rawQuery("SELECT * FROM Category", null);
        List<Category> categories = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while(cursor.isAfterLast()== false){

                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                Category category = new Category();
                category.setId(id);
                category.setName(name);
                category.setDescription(description);

                categories.add(category);
                cursor.moveToNext();
            }
        }
        return categories;
    }

    public void destroyDb(){
        db.close();
    }
}
