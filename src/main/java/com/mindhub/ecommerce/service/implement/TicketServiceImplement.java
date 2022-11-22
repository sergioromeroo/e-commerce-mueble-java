package com.mindhub.ecommerce.service.implement;

import com.mindhub.ecommerce.DTOS.TicketDTO;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.repositories.TicketRepository;
import com.mindhub.ecommerce.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TicketServiceImplement implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<TicketDTO> getTicketsDTO() {
        return ticketRepository.findAll().stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toList());
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
