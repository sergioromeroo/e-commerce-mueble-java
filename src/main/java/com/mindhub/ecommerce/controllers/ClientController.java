package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.DTOS.ClientDTO;
import com.mindhub.ecommerce.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping("/clients")
    public List<ClientDTO> getListOfClientsDTO() {
        return clientService.getClientsDTO();
    }
    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientDTO(id);
    }
}
