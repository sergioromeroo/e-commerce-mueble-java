package com.mindhub.ecommerce.service;

import com.mindhub.ecommerce.DTOS.ClientProductFavDTO;
import com.mindhub.ecommerce.models.ClientProducFav;

import java.util.List;

public interface ClientProductFavService {

    List<ClientProductFavDTO> getClientProductFav();

    void saveClientProductFav(ClientProducFav clientProducFav);

}
