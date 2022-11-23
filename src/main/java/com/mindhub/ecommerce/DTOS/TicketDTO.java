package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDTO {



    private long id;

    private LocalDateTime date;

    private double amount;

    private String product;








    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.date = ticket.getDate();
        this.amount = ticket.getAmount();

    }





    public long getId() {
        return id;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
