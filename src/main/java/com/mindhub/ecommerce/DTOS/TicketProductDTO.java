package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.TicketProduct;

import java.util.List;

public class TicketProductDTO {



    private long idProduct;

    private String name;

    private String type;

    private double price;


    public TicketProductDTO() {
    }

    public TicketProductDTO(TicketProduct ticketProduct) {

        this.idProduct = ticketProduct.getProduct().getId();
        this.name = ticketProduct.getProduct().getName();
        this.type = ticketProduct.getProduct().getType();
        this.price = ticketProduct.getProduct().getPrice();

    }


    public long getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

}
