package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.ClientProducFav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientProductFavRepository extends JpaRepository<ClientProducFav,Long> {

    ClientProducFav findAllByid(long id);

}
