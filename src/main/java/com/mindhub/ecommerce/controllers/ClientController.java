package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.DTOS.ClientDTO;
import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/clients")
    public List<ClientDTO> getListOfClientsDTO() {
        return clientService.getClientsDTO();
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientDTO(id);
    }

    @RequestMapping("/clientcurrent")
    public ClientDTO getClientCurrent(Authentication authentication) {
        Client clientCurrent = clientService.getClientByEmail(authentication.getName());
        return new ClientDTO(clientCurrent);
    }
    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam long cellphone,
            @RequestParam String city,
            @RequestParam String addres,
            @RequestParam String state
    ) {



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

        if(clientService.getClientByEmail(email) != null){
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }



        Client newClient=new Client(firstName, lastName, email, passwordEncoder.encode(password) ,cellphone,city,addres,state);
        clientService.saveClient(newClient);



        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @PutMapping("/clients/modify")
    public ResponseEntity<?> putClient(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam long cellPhone,
            @RequestParam String addres,
            @RequestParam String password,
            Authentication authentication
    ){

        Client clientCurrent = clientService.getClientByEmail(authentication.getName());

        if(firstName.isEmpty()){
            return new ResponseEntity<>("The name is empty",HttpStatus.FORBIDDEN);
        }


        if(lastName.isEmpty()){
            return new ResponseEntity<>("The lastname is empty",HttpStatus.FORBIDDEN);
        }

        if(cellPhone == 0){
            return new ResponseEntity<>("The cell phone is 0",HttpStatus.FORBIDDEN);
        }

        if(addres.isEmpty()){
            return new ResponseEntity<>("The addres is empty",HttpStatus.FORBIDDEN);
        }



        if(password.isEmpty()){
            return new ResponseEntity<>("The password is empty",HttpStatus.FORBIDDEN);
        }

        if(clientCurrent == null){
            return new ResponseEntity<>("The client not exist",HttpStatus.FORBIDDEN);
        }


        clientCurrent.setAddres(addres);
        clientCurrent.setFirstname(firstName);
        clientCurrent.setLastname(lastName);
        clientCurrent.setCellPhone(cellPhone);
        clientCurrent.setPassword(password);


        clientService.saveClient(clientCurrent);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PutMapping("/clients/delete")
    public ResponseEntity<?> deleteClient(
            @RequestParam String email
    ){

        Client clientFound = clientService.getClientByEmail(email);

        if(clientFound == null){
            return new ResponseEntity<>("The client not exist", HttpStatus.FORBIDDEN);
        }


        clientService.deleteClient(clientFound);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
