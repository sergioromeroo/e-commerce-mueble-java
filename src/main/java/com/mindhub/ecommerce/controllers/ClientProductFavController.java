package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.ClientProductFavDTO;
import com.mindhub.ecommerce.repositories.ClientProductFavRepository;
import com.mindhub.ecommerce.service.ClientProductFavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientProductFavController {

//    @Autowired
//    ClientProductFavService clientProductFavService;


    @Autowired
    ClientProductFavRepository clientProductFavRepository;


    @GetMapping("/clientproductfav")
    public List<ClientProductFavDTO> getClientProductFav(){
        return clientProductFavRepository.findAll().stream().map(clientProducFav -> new ClientProductFavDTO(clientProducFav)).collect(Collectors.toList());
    }


}
