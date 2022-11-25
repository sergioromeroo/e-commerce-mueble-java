package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.TicketProduct;

public class TicketProductDTO {



    private long idProduct;




    public TicketProductDTO() {
    }

    public TicketProductDTO(TicketProduct ticketProduct) {
        this.idProduct = ticketProduct.getProduct().getId();
    }





    public long getIdProduct() {
        return idProduct;
    }
}
