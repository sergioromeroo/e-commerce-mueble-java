package com.mindhub.ecommerce.service.implement;


import com.mindhub.ecommerce.DTOS.ClientProductFavDTO;
import com.mindhub.ecommerce.models.ClientProducFav;
import com.mindhub.ecommerce.repositories.ClientProductFavRepository;
import com.mindhub.ecommerce.service.ClientProductFavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientProductFavImplement implements ClientProductFavService {

    @Autowired
    ClientProductFavRepository clientProductFavRepository;

    @Override
    public List<ClientProductFavDTO> getClientProductFav() {
        return clientProductFavRepository.findAll().stream().map(clientProducFav -> new ClientProductFavDTO(clientProducFav)).collect(Collectors.toList());
    }

    @Override
    public void saveClientProductFav(ClientProducFav clientProducFav) {
        clientProductFavRepository.save(clientProducFav);
    }
}
