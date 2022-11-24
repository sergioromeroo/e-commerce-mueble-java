package com.mindhub.ecommerce.DTOS;

public class CreateProductDTO {

    private long id;

    private String type;

    private String name;

    private double price;

    private String urlImg;

    private int stock;

    private String materialType;

    private String description;



    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public int getStock() {
        return stock;
    }

    public String getMaterialType() {
        return materialType;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
