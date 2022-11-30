package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.models.TicketProduct;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketDTO {



    private long id;

    private long client_id;

    private LocalDateTime date;

    private double amount;

    private Set<TicketProductDTO> product;




    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.client_id = ticket.getClient().getId();
        this.date = ticket.getDate();
        this.amount = ticket.getAmount();
        this.product = ticket.getProducts().stream().map(ticketProduct -> new TicketProductDTO(ticketProduct)).collect(Collectors.toSet());
    }





    public long getId() {
        return id;
    }

    public long getClient_id() {
        return client_id;
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


    public Set<TicketProductDTO> getProduct() {
        return product;
    }

    public void setProduct(Set<TicketProductDTO> product) {
        this.product = product;
    }
}
