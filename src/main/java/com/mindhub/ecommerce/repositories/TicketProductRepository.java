package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.TicketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface TicketProductRepository extends JpaRepository<TicketProduct,Long> {
}
