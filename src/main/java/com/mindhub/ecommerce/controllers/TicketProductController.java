package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.TicketProductDTO;
import com.mindhub.ecommerce.repositories.TicketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketProductController {

    @Autowired
    TicketProductRepository ticketProductRepository;

    @GetMapping("/ticketproduct")
    public List<TicketProductDTO> getTicketProductList(){
        return  ticketProductRepository.findAll().stream().map(ticketProduct -> new TicketProductDTO(ticketProduct)).collect(Collectors.toList());
    }


}
