package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.TicketProduct;

import java.util.List;

public class TicketProductDTO {



    private long idProduct;

    private String name;

    private String urlImg;

    private String type;

    private double price;

    private int quantity;

    public TicketProductDTO() {
    }

    public TicketProductDTO(TicketProduct ticketProduct) {

        this.idProduct = ticketProduct.getProduct().getId();
        this.name = ticketProduct.getProduct().getName();
        this.urlImg = ticketProduct.getProduct().getUrlImg();
        this.type = ticketProduct.getProduct().getType();
        this.price = ticketProduct.getProduct().getPrice();
        this.quantity = ticketProduct.getQuantity();

    }


    public long getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
