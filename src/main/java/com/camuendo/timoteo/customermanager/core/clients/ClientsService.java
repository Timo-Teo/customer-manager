package com.camuendo.timoteo.customermanager.core.clients;


import com.camuendo.timoteo.customermanager.core.clients.dto.CreateClientDTO;
import com.camuendo.timoteo.customermanager.core.clients.dto.UpdateClientDTO;
import com.camuendo.timoteo.customermanager.core.clients.entities.Client;

import java.util.List;

public interface ClientsService {

    List<Client> findAll();

    Client findById(Long id);

    Client save(CreateClientDTO client);

    Client update(Long id, UpdateClientDTO client);

    void delete(Long id);

}
