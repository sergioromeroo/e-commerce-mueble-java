package com.mindhub.ecommerce.DTOS;

import com.mindhub.ecommerce.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private long cellphone;

    private Set<TicketDTO> tickets = new HashSet<>();

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstname =client.getFirstname() ;
        this.lastname = client.getLastname();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.cellphone = client.getCellPhone();
        this.tickets = client.getTickets().stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toSet());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCellphone() {
        return cellphone;
    }

    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }

    public Set<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}
