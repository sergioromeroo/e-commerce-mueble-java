package com.mindhub.ecommerce.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private int quantity = 0;

    private String description;

    private boolean enable = true;


    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    List<TicketProduct> tickets;



    public Product() {}

    public Product(String type, String name, double price, String urlImg, int stock, String materialType,String description) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.urlImg = urlImg;
        this.stock = stock;
        this.materialType = materialType;
        this.description = description;
    }


    public List<TicketProduct> getTickets() {
        return tickets;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
