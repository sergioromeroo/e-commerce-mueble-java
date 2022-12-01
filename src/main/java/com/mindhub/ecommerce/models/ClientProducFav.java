package com.mindhub.ecommerce.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientProducFav {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client clients;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productFavorite_id")
    private ProductFavorite productFavorite;


    private boolean enable = false;

    public ClientProducFav() {
    }


    public ClientProducFav(Client client, ProductFavorite productFavorite) {
        this.clients = client;
        this.productFavorite = productFavorite;
    }


    public long getId() {
        return id;
    }

    public Client getClient() {
        return clients;
    }

    public void setClient(Client client) {
        this.clients = client;
    }

    public ProductFavorite getProductFavorite() {
        return productFavorite;
    }

    public void setProductFavorite(ProductFavorite productFavorite) {
        this.productFavorite = productFavorite;
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


}
