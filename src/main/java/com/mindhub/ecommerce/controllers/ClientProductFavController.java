package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.ClientProductFavDTO;
import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.ClientProducFav;
import com.mindhub.ecommerce.models.ProductFavorite;
import com.mindhub.ecommerce.repositories.ClientProductFavRepository;
import com.mindhub.ecommerce.service.ClientProductFavService;
import com.mindhub.ecommerce.service.ClientService;
import com.mindhub.ecommerce.service.ProductFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientProductFavController {

//    @Autowired
//    ClientProductFavService clientProductFavService;


    @Autowired
    ClientProductFavRepository clientProductFavRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    ProductFavoriteService productFavoriteService;


    @GetMapping("/clientproductfav")
    public List<ClientProductFavDTO> getClientProductFav(){
        return clientProductFavRepository.findAll().stream().map(clientProducFav -> new ClientProductFavDTO(clientProducFav)).collect(Collectors.toList());
    }


    @PostMapping("/clientproductfav")
    public ResponseEntity<?> addProductFavorite(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam String url,
            @RequestParam double price,
            Authentication authentication
    ){

        Client clienCurrent = clientService.getClientByEmail(authentication.getName());

        if(name.isEmpty()){
            return new ResponseEntity<>("The name is empty", HttpStatus.FORBIDDEN);
        }

        if(url.isEmpty()){
            return new ResponseEntity<>("The url is empty", HttpStatus.FORBIDDEN);
        }

        if(price == 0){
            return new ResponseEntity<>("The price is 0", HttpStatus.FORBIDDEN);
        }

        if(clienCurrent == null){
            return new ResponseEntity<>("The client not exist", HttpStatus.FORBIDDEN);
        }

        if(id == 0){
            return new ResponseEntity<>("The id is 0", HttpStatus.FORBIDDEN);
        }

        ProductFavorite productFavorite = new ProductFavorite(id,name,url,price);

        productFavoriteService.saveProductFavorite(productFavorite);

        List<ClientProducFav> favoriteForId = clienCurrent.getProductsFavorites().stream().filter(favorite -> favorite.getProductFavorite().getProduct_id()==id).collect(Collectors.toList());

        if(favoriteForId.size() > 0) {
            return new ResponseEntity<>("Product already in favorites", HttpStatus.FORBIDDEN);
        }

        ClientProducFav clientProducFav = new ClientProducFav(clienCurrent,productFavorite);

        clientProducFav.setEnable(true);

        clientProductFavRepository.save(clientProducFav);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }





    @PatchMapping ("/clientproductfav")
    public ResponseEntity<?> deleteProductFavorite(
            @RequestParam long id
    ){
        ClientProducFav clientProducFavFound = clientProductFavRepository.findAllByid(id);

        if(clientProducFavFound == null){
            return new ResponseEntity<>("The product not exist in favorite", HttpStatus.FORBIDDEN);
        }

        clientProducFavFound.setEnable(false);

        clientProductFavRepository.delete(clientProducFavFound);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
