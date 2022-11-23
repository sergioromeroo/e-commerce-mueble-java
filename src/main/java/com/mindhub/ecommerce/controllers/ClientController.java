package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.DTOS.ClientDTO;
import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping( "/clients")
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email , @RequestParam int age, @RequestParam String password, @RequestParam long cellphone) {



        if (firstName.isEmpty()) {

            return new ResponseEntity<>("Missing firstname", HttpStatus.FORBIDDEN);
        }
        if (lastName.isEmpty()) {

            return new ResponseEntity<>("Missing Last Name", HttpStatus.FORBIDDEN);

        }
        if (email.isEmpty()) {

            return new ResponseEntity<>("Missing email", HttpStatus.FORBIDDEN);

        }
        if (password.isEmpty()) {

            return new ResponseEntity<>("Missing password", HttpStatus.FORBIDDEN);

        }
        if (cellphone == 0) {

            return new ResponseEntity<>("Missing cellphone", HttpStatus.FORBIDDEN);

        }





        Client newClient=new Client(firstName, lastName,age, email, password,cellphone);
        clientService.saveClient(newClient);



        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
