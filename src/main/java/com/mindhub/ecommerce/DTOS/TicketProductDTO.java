package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.TicketProduct;

public class TicketProductDTO {

    private long id;

    private long idTicket;

    private long idProduct;

    public TicketProductDTO() {
    }

    public TicketProductDTO(TicketProduct ticketProduct) {
        this.idTicket = ticketProduct.getTicket().getId();
        this.idProduct = ticketProduct.getProduct().getId();
    }

    public long getId() {
        return id;
    }

    public long getIdTicket() {
        return idTicket;
    }

    public long getIdProduct() {
        return idProduct;
    }
}
