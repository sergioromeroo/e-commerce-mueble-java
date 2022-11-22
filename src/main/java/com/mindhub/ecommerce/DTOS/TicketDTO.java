package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Ticket;

import java.time.LocalDateTime;

public class TicketDTO {



    private long id;

    private LocalDateTime date;

    private double amount;

    private String product;

    private Client client;






    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.date = ticket.getDate();
        this.amount = ticket.getAmount();
        this.product = ticket.getProduct();
        this.client = ticket.getClient();
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
