package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.ProductFavoriteDTO;
import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.ProductFavorite;
import com.mindhub.ecommerce.service.ClientService;
import com.mindhub.ecommerce.service.ProductFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductFavoriteController {

    @Autowired
    ProductFavoriteService productFavoriteService;

    @Autowired
    ClientService clientService;


    @RequestMapping("/productfavorite")
    public List<ProductFavoriteDTO> getProductFavDTO(){
        return productFavoriteService.getListProductFavDTO();
    }


    @PostMapping("/add/productFavorite")
    public ResponseEntity<?> addFavorite(
            @RequestParam String nameProduct,
            @RequestParam String urlProduct,
            @RequestParam double price,
            @RequestParam long id,
            Authentication authentication
    ){

        Client clientCurrent = clientService.getClientByEmail(authentication.getName());


        if(nameProduct.isEmpty()){
            return new ResponseEntity<>("The product name is empty",HttpStatus.FORBIDDEN);
        }

        if(urlProduct.isEmpty()){
            return new ResponseEntity<>("The product url is empty",HttpStatus.FORBIDDEN);
        }

        if(price == 0){
            return new ResponseEntity<>("The price is 0",HttpStatus.FORBIDDEN);
        }


        if(clientCurrent == null){
            return new ResponseEntity<>("the client not exits",HttpStatus.FORBIDDEN);
        }


        ProductFavorite productFavorite = new ProductFavorite(id,nameProduct,urlProduct,price);





        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
