package com.mindhub.ecommerce.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private long id;

    private String type;

    private String name;

    private double price;

    private String urlImg;

    private int stock;

    private String materialType;

    private int quantity = 1;





    public Product() {}

    public Product(String type, String name, double price, String urlImg, int stock, String materialType) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.urlImg = urlImg;
        this.stock = stock;
        this.materialType = materialType;
    }







    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
