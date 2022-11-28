package com.mindhub.ecommerce.service;

import com.mindhub.ecommerce.DTOS.ClientDTO;
import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Product;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClientsDTO();

    ClientDTO getClientDTO(Long id);


    Client getClientByEmail(String email);

    void saveClient(Client client);

    void deleteClient(Client client);

    Client findAllByid(Long id);
}
