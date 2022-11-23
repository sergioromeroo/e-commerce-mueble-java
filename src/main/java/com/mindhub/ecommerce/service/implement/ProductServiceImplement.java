package com.mindhub.ecommerce.service.implement;

import com.mindhub.ecommerce.DTOS.ProductDTO;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<ProductDTO> getProductsDTO() {
        return productRepository.findAll().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductDTO(long id) {
        return productRepository.findById(id).map(product -> new ProductDTO(product)).orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(long id) {
        return productRepository.findAllByid(id);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }


}
