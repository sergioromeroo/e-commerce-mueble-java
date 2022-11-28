package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.ClientProducFav;
import com.mindhub.ecommerce.models.ProductFavorite;

public class ClientProductFavDTO {

    private long id;

    private long client_id;

    private long productFavorite_id;

    private String product_name;

    private String url;

    private double price;





    public ClientProductFavDTO() {
    }


    public ClientProductFavDTO(ClientProducFav clientProducFav) {
        this.id = clientProducFav.getId();
        this.client_id = clientProducFav.getClient().getId();
        this.productFavorite_id = clientProducFav.getProductFavorite().getId();
        this.product_name = clientProducFav.getProductFavorite().getNameProduct();
        this.url = clientProducFav.getProductFavorite().getUrlImgProduct();
        this.price = clientProducFav.getProductFavorite().getPriceProduct();
    }






    public long getId() {
        return id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getProductFavorite_id() {
        return productFavorite_id;
    }

    public void setProductFavorite_id(long productFavorite_id) {
        this.productFavorite_id = productFavorite_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getUrl() {
        return url;
    }

    public double getPrice() {
        return price;
    }
}
