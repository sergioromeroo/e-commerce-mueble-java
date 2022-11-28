package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class ProductFavorite {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private long id;


    private long product_id;
    private String nameProduct;

    private String urlImgProduct;

    private double priceProduct;



    @OneToMany(mappedBy = "productFavorite",fetch = FetchType.EAGER)
    Set<ClientProducFav> client = new HashSet<>();




    public ProductFavorite() {
    }



    public ProductFavorite(long product_id,String nameProduct, String urlImgProduct, double priceProduct) {
        this.product_id = product_id;
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


    public Set<ClientProducFav> getClient() {
        return client;
    }


    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}


