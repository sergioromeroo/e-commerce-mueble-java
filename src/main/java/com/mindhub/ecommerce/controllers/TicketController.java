package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.ClientDTO;
import com.mindhub.ecommerce.DTOS.TicketDTO;
import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.models.TicketProduct;
import com.mindhub.ecommerce.repositories.TicketProductRepository;
import com.mindhub.ecommerce.service.ClientService;
import com.mindhub.ecommerce.service.ProductService;
import com.mindhub.ecommerce.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    ClientService clientService;

    @Autowired
    ProductService productService;

    @Autowired
    TicketProductRepository ticketProductRepository;

    @RequestMapping("/tickets")
    public List<TicketDTO> getTicketsDTO() {
        return ticketService.getTicketsDTO();
    }



    @PostMapping("/tickets")
    public ResponseEntity<?> newOrder(
            @RequestParam double amount,
            @RequestParam String paymentMethod,
            @RequestParam List<Integer> idProduct,
            @RequestParam List<Integer> quantity,
            Authentication authentication

    ){
        Client clientCurrent = clientService.getClientByEmail(authentication.getName());


        if(amount == 0){
            return new ResponseEntity<>("The amount is 0", HttpStatus.FORBIDDEN);
        }

        if(paymentMethod.isEmpty()){
            return new ResponseEntity<>("The payment method is empty", HttpStatus.FORBIDDEN);
        }

        if(clientCurrent == null){
            return new ResponseEntity<>("The client no exist", HttpStatus.FORBIDDEN);
        }


        Ticket ticketCurrent = new Ticket(LocalDateTime.now(),amount,clientCurrent,paymentMethod);
        ticketService.saveTicket(ticketCurrent);
//        int i = idProduct.get(0);

        for(int i = 0; i < idProduct.size(); i++){

            Product productFound =  productService.findById(idProduct.get(i));
            int subtraction = quantity.get(i);

            productFound.setStock(productFound.getStock() - subtraction);
            productService.saveProduct(productFound);

            if(subtraction != 0) {
                ticketProductRepository.save(new TicketProduct(ticketCurrent, productFound, subtraction));
            }// aca guardamos el ticket product
        }


        return new ResponseEntity<>(ticketCurrent.getId(),HttpStatus.CREATED);
    }



}
