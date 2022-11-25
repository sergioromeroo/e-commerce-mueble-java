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

    private int quantity;

    private boolean enable;

    private String description;

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
        this.quantity = product.getQuantity();
        this.enable = product.isEnable();
        this.description = product.getDescription();

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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
