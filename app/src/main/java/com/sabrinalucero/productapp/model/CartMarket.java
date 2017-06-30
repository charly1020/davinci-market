package com.sabrinalucero.productapp.model;

import java.util.Date;

/**
 * Created by charly on 6/30/17.
 */

public class CartMarket {

    private int id;
    private String name;
    private String description;
    private Date cartDate;
    private Boolean status;

    public CartMarket() {
    }

    public CartMarket(int id, String name, String description, Date cartDate, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cartDate = cartDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCartDate() {
        return cartDate;
    }

    public void setCartDate(Date cartDate) {
        this.cartDate = cartDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
