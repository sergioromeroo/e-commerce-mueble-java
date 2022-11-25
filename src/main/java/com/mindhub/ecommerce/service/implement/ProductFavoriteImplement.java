package com.mindhub.ecommerce.service.implement;

import com.mindhub.ecommerce.DTOS.ProductFavoriteDTO;
import com.mindhub.ecommerce.models.ProductFavorite;
import com.mindhub.ecommerce.repositories.ProductFavoriteRepository;
import com.mindhub.ecommerce.service.ProductFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductFavoriteImplement implements ProductFavoriteService {

    @Autowired
    ProductFavoriteRepository productFavoriteRepository;

    @Override
    public List<ProductFavoriteDTO> getListProductFavDTO() {
        return productFavoriteRepository.findAll().stream().map(productFavorite -> new ProductFavoriteDTO(productFavorite)).collect(Collectors.toList());
    }

    @Override
    public void saveProductFavorite(ProductFavorite productFavorite) {
        productFavoriteRepository.save(productFavorite);
    }
}
