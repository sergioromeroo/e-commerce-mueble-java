package com.mindhub.ecommerce.service;

import com.mindhub.ecommerce.DTOS.ClientDTO;
import com.mindhub.ecommerce.models.Client;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClientsDTO();

    ClientDTO getClientDTO(Long id);


    Client getClientByEmail(String email);

    void saveClient(Client client);
}
