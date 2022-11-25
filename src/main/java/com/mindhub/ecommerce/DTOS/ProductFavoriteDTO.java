package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.ProductFavorite;

public class ProductFavoriteDTO {



    private long id;

    private String nameProduct;

    private String urlImgProduct;

    private double priceProduct;






    public ProductFavoriteDTO() {
    }


    public ProductFavoriteDTO(ProductFavorite productFavorite) {
        this.id = productFavorite.getId();
        this.nameProduct = productFavorite.getNameProduct();
        this.urlImgProduct = productFavorite.getUrlImgProduct();
        this.priceProduct = productFavorite.getPriceProduct();
    }






    public long getId() {
        return id;
    }


    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getUrlImgProduct() {
        return urlImgProduct;
    }

    public void setUrlImgProduct(String urlImgProduct) {
        this.urlImgProduct = urlImgProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }
}
