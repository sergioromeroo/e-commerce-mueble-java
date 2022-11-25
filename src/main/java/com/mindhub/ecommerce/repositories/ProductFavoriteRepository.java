package com.mindhub.ecommerce.repositories;


import com.mindhub.ecommerce.models.ProductFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductFavoriteRepository extends JpaRepository<ProductFavorite,Long> {

}
