package com.mindhub.ecommerce.service;

import com.mindhub.ecommerce.DTOS.ProductFavoriteDTO;
import com.mindhub.ecommerce.models.ProductFavorite;

import java.util.List;

public interface ProductFavoriteService {


    List<ProductFavoriteDTO> getListProductFavDTO();

    void saveProductFavorite(ProductFavorite productFavorite);


}
