package com.sabrinalucero.productapp.model;

/**
 * Created by charly on 6/30/17.
 */

public class Product {

    private int id;
    private String name;
    private String description;
    private int catId;

    public Product(){}

    public Product(int id, String name, String description, int catId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.catId = catId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}