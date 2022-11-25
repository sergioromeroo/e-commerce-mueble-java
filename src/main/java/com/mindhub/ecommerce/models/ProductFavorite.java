package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ProductFavorite {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private long id;

    private String nameProduct;

    private String urlImgProduct;

    private double priceProduct;






    public ProductFavorite() {
    }



    public ProductFavorite(String nameProduct, String urlImgProduct, double priceProduct) {

        this.nameProduct = nameProduct;
        this.urlImgProduct = urlImgProduct;
        this.priceProduct = priceProduct;
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
