package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository  extends JpaRepository<Client,Long> {
    Client findByEmail(String email);

    Client findAllByid(Long id);
}
