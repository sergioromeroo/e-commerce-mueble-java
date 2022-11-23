package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.ClientDTO;
import com.mindhub.ecommerce.DTOS.TicketDTO;
import com.mindhub.ecommerce.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @RequestMapping("/tickets")
    public List<TicketDTO> getTicketsDTO() {
        return ticketService.getTicketsDTO();
    }


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
