package com.mindhub.ecommerce.service;

import com.mindhub.ecommerce.DTOS.ProductDTO;
import com.mindhub.ecommerce.models.Product;

import java.util.List;

public interface ProductService {


    public List<ProductDTO> getProductsDTO();

    public ProductDTO getProductDTO(long id);

    public void saveProduct(Product product);

    public Product findById(long id);

    public void deleteProduct(Product product);



}
