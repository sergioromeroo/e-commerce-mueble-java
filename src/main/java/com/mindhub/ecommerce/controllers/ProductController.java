package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.ProductDTO;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {


    @Autowired
    ProductService productService;


    @RequestMapping("/products")
    public List<ProductDTO> getProducts(){
        return productService.getProductsDTO();
    }

    @RequestMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable long id){
        return productService.getProductDTO(id);
    }



    @PatchMapping("/products/update")
    public ResponseEntity<?> updateProduct(
            @RequestParam int stock,
            @RequestParam long id
    ){

        Product productFound =  productService.findById(id);

        productFound.setStock(stock);

        productService.saveProduct(productFound);


        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping("/products/delete")
    public ResponseEntity<?> deleteProduct(
            @RequestParam long id
    ){

        Product productFound = productService.findById(id);

        productService.deleteProduct(productFound);

        return new ResponseEntity<>(HttpStatus.OK);
    }




}
