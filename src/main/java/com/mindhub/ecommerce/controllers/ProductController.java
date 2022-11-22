package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.ProductDTO;
import com.mindhub.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
