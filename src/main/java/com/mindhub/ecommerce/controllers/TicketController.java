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
            @RequestParam long idProduct, // aca seria una lista de productos
            Authentication authentication

    ){

        Client clientCurrent = clientService.getClientByEmail(authentication.getName());  //aca seria authetication
        Ticket ticketCurrent = new Ticket(LocalDateTime.now(),amount,clientCurrent,paymentMethod);
        Product productsCurrent = productService.findById(idProduct);



        if(amount == 0){
            return new ResponseEntity<>("The amount is 0", HttpStatus.FORBIDDEN);
        }

        if(paymentMethod.isEmpty()){
            return new ResponseEntity<>("The payment method is empty", HttpStatus.FORBIDDEN);
        }

        if(clientCurrent == null){
            return new ResponseEntity<>("The client no exist", HttpStatus.FORBIDDEN);
        }



        ticketService.saveTicket(ticketCurrent);

        TicketProduct ticketProduct = new TicketProduct(ticketCurrent,productsCurrent);

        ticketProductRepository.save(ticketProduct);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /*

        --- ALGORITMO PARA MODIFICAR EL STOCK Y PONER LOS PRODUCTOS EN EL TICKET ---

        for(i = 0; i < product.quize(); i++){

            TicketProduct = new TicketProduct(ticketCurrent,product[i]);

            int stockActuality = product[i].getStock() - 1;

            product[i].setStock(stockActulity);

            ProductService.save(product[i]);

            TicketProndtService.save(prueba);

        }


     */


    /*
        public  Responsentity<> nuevoPedido(
            @RequestParam monto,
            conquepago,
            @RequestParam list<Product> product
            authorization
        )
        client current
        Ticket ticketCurrent = new Ticket(monto,conquepago,new Date, clientcurrent)
    *
        ticketService.save(ticketCurrent);

        for(i = 0; i < product.size(); i++{

        TicketProndt prueba = new TicketProndt(ticketCurrent, i)
        TicketProndtService.save(prueba);

        }



    *   */

}
