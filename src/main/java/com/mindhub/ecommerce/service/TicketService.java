package com.mindhub.ecommerce.service;


import com.mindhub.ecommerce.DTOS.TicketDTO;
import com.mindhub.ecommerce.models.Ticket;

import java.util.List;

public interface TicketService {


    public List<TicketDTO> getTicketsDTO();


    public void saveTicket(Ticket ticket);

}
