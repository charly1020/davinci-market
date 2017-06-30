package com.sabrinalucero.productapp.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sabrinalucero.productapp.dbHelpers.ProductSQLLiteHelper;
import com.sabrinalucero.productapp.model.Category;
import com.sabrinalucero.productapp.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabrina on 6/30/17.
 */

public class ProductsUtils {

    private ProductSQLLiteHelper productHelper;
    private SQLiteDatabase db;
    private CategoryUtils catUtil;


    public void initDb(Context context, List<Category> categories) {

        productHelper = new ProductSQLLiteHelper(context, "dbDavinci1", null, 1);

        db = productHelper.getWritableDatabase();

        productHelper.clearTable(db);

        createProducts(categories);

    }

    public void createProducts(List<Category> categories) {
        Product p;
        for (Category c : categories) {
            switch (c.getName()) {
                case "verduleria":
                    fillGreenGrossery(c);
                    break;
                case "carniceria":
                    fillButchery(c);
                    break;
                case "panaderia":
                    fillBackery(c);
                    break;
                case "limpieza":
                    fillCleaning(c);
                    break;
                case "bebidas":
                    fillDrinks(c);
                    break;
                case "lacteos":
                    fillDairyProducts(c);
                    break;
            }
        }
    }

    private void fillGreenGrossery(Category c) {

        Product prod1 = new Product();
        prod1.setId(1);
        prod1.setName("tomate");
        prod1.setDescription("verdura");
        prod1.setCatId(c.getId());
        createProduct(prod1);

        Product prod2 = new Product();
        prod2.setId(2);
        prod2.setName("limon");
        prod2.setDescription("fruta");
        prod2.setCatId(c.getId());
        createProduct(prod2);

        Product prod3 = new Product();
        prod3.setId(3);
        prod3.setName("rucula");
        prod3.setDescription("verdura");
        prod3.setCatId(c.getId());
        createProduct(prod3);

        Product prod4 = new Product();
        prod4.setId(4);
        prod4.setName("manzana");
        prod4.setDescription("fruta");
        prod4.setCatId(c.getId());
        createProduct(prod4);

        Product prod5 = new Product();
        prod5.setId(5);
        prod5.setName("cebolla");
        prod5.setDescription("verdura");
        prod5.setCatId(c.getId());
        createProduct(prod5);

        Product prod6 = new Product();
        prod6.setId(6);
        prod6.setName("banana");
        prod6.setDescription("fruta");
        prod6.setCatId(c.getId());
        createProduct(prod6);

    }

    private void fillBackery(Category c) {

        Product prod7 = new Product();
        prod7.setId(7);
        prod7.setName("panlactal");
        prod7.setDescription("panaderia");
        prod7.setCatId(c.getId());
        createProduct(prod7);

        Product prod8 = new Product();
        prod8.setId(8);
        prod8.setName("galletitas");
        prod8.setDescription("panaderia");
        prod8.setCatId(c.getId());
        createProduct(prod8);

        Product prod9 = new Product();
        prod9.setId(9);
        prod9.setName("hamburguesas");
        prod9.setDescription("panaderia");
        prod9.setCatId(c.getId());
        createProduct(prod9);

        Product prod10 = new Product();
        prod10.setId(10);
        prod10.setName("panchos");
        prod10.setDescription("panaderia");
        prod10.setCatId(c.getId());
        createProduct(prod10);

        Product prod11 = new Product();
        prod11.setId(11);
        prod11.setName("talitas");
        prod11.setDescription("panaderia");
        prod11.setCatId(c.getId());
        createProduct(prod11);

        Product prod12 = new Product();
        prod12.setId(12);
        prod12.setName("facturas");
        prod12.setDescription("panaderia");
        prod12.setCatId(c.getId());
        createProduct(prod12);

    }

    private void fillButchery(Category c) {

        Product prod13 = new Product();
        prod13.setId(13);
        prod13.setName("peceto");
        prod13.setDescription("carniceria");
        prod13.setCatId(c.getId());
        createProduct(prod13);

        Product prod14 = new Product();
        prod14.setId(14);
        prod14.setName("asado");
        prod14.setDescription("carniceria");
        prod14.setCatId(c.getId());
        createProduct(prod14);

        Product prod15 = new Product();
        prod15.setId(15);
        prod15.setName("carne picada");
        prod15.setDescription("carniceria");
        prod15.setCatId(c.getId());
        createProduct(prod15);

        Product prod16 = new Product();
        prod16.setId(16);
        prod16.setName("bondiola");
        prod16.setDescription("carniceria");
        prod16.setCatId(c.getId());
        createProduct(prod16);

        Product prod17 = new Product();
        prod17.setId(17);
        prod17.setName("pollo");
        prod17.setDescription("carniceria");
        prod17.setCatId(c.getId());
        createProduct(prod17);

        Product prod18 = new Product();
        prod18.setId(18);
        prod18.setName("pescado");
        prod18.setDescription("carniceria");
        prod18.setCatId(c.getId());
        createProduct(prod18);

    }

    private void fillDrinks(Category c) {

        Product prod19 = new Product();
        prod19.setId(19);
        prod19.setName("vino");
        prod19.setDescription("bebidas");
        prod19.setCatId(c.getId());
        createProduct(prod19);
    }

    private void fillCleaning(Category c) {

        Product prod20 = new Product();
        prod20.setId(20);
        prod20.setName("jabon");
        prod20.setDescription("limpieza");
        prod20.setCatId(c.getId());
        createProduct(prod20);

    }

    private void fillDairyProducts(Category c) {

        Product prod21 = new Product();
        prod21.setId(21);
        prod21.setName("queso");
        prod21.setDescription("lacteos");
        prod21.setCatId(c.getId());
        createProduct(prod21);
    }


    private void createProduct(Product p) {
        if (db != null) {
            ContentValues newRegistry = new ContentValues();
            newRegistry.put("id", p.getId());
            newRegistry.put("name", p.getName());
            newRegistry.put("description", p.getDescription());
            newRegistry.put("product_cat", p.getCatId());

            db.insert("Product", null, newRegistry);
        }
    }

    public List<Product> getAll() {
        Cursor cursor = db.rawQuery("SELECT * FROM Product", null);
        List<Product> products = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {

                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                Integer catId = cursor.getInt(cursor.getColumnIndex("product_cat"));

                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setDescription(description);
                product.setCatId(catId);

                products.add(product);
                cursor.moveToNext();
            }
        }
        return products;
    }

    public List<Product> getProductsById(int categoryId, Context context) {
        if (db == null) {
            productHelper = new ProductSQLLiteHelper(context, "dbDavinci1", null, 1);
            db = productHelper.getWritableDatabase();
        }

        Cursor cursor = db.rawQuery("SELECT * FROM Product where product_cat = " + categoryId, null);
        List<Product> products = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {

                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                Integer catId = cursor.getInt(cursor.getColumnIndex("product_cat"));

                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setDescription(description);
                product.setCatId(catId);

                products.add(product);
                cursor.moveToNext();
            }
        }
        return products;
    }

}