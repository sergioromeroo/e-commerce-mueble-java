package com.mindhub.ecommerce.DTOS;


import com.mindhub.ecommerce.models.Product;

public class ProductDTO {


    private long id;

    private String type;

    private String name;

    private double price;

    private String urlImg;

    private int stock;

    private String materialType;




    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.type = product.getType();
        this.name = product.getName();
        this.price = product.getPrice();
        this.urlImg = product.getUrlImg();
        this.stock = product.getStock();
        this.materialType = product.getMaterialType();
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
}
