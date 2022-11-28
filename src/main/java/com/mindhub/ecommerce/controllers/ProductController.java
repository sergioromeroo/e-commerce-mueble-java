package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.DTOS.CreateProductDTO;
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

    @PostMapping("/post/product")
    public ResponseEntity<?> createproduct(@RequestBody CreateProductDTO createProductDTO){


        if (createProductDTO.getName().isEmpty() || createProductDTO.getPrice() == 0 || createProductDTO.getType().isEmpty() || createProductDTO.getMaterialType().isEmpty() || createProductDTO.getStock() == 0){
            return new ResponseEntity<>("Admin data is missing please check again", HttpStatus.FORBIDDEN);
        }


        productService.saveProduct(new Product(createProductDTO.getType(),createProductDTO.getName(),createProductDTO.getPrice(),createProductDTO.getUrlImg(),createProductDTO.getStock(),createProductDTO.getMaterialType(),createProductDTO.getDescription()));

        return new ResponseEntity<>("The new product has been created succsesfuly", HttpStatus.CREATED);
    }


    @PatchMapping("/products/update")
    public ResponseEntity<?> updateProduct(
            @RequestParam Integer stock,
            @RequestParam long id
    ){




        if(stock < 0){
            return new ResponseEntity<>("the stock cannot be less than 0", HttpStatus.FORBIDDEN);
        }


        Product productFound =  productService.findById(id);

        if(productFound == null){
            return new ResponseEntity<>("The id not exist", HttpStatus.FORBIDDEN);
        }

        productFound.setStock(stock);

        productService.saveProduct(productFound);


        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PatchMapping("/products/delete")
    public ResponseEntity<?> deleteProduct(
            @RequestParam long id
    ){

        Product productFound = productService.findById(id);

        productFound.setEnable(false);

        productService.saveProduct(productFound);

        return new ResponseEntity<>(HttpStatus.OK);
    }




}
