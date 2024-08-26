package com.camuendo.timoteo.customermanager.core.clients;

import com.camuendo.timoteo.customermanager.core.clients.dto.CreateClientDTO;
import com.camuendo.timoteo.customermanager.core.clients.map.MapperClient;
import com.camuendo.timoteo.customermanager.core.clients.dto.UpdateClientDTO;
import com.camuendo.timoteo.customermanager.core.clients.entities.Client;
import com.camuendo.timoteo.customermanager.core.clients.repository.ClientsRepository;
import com.camuendo.timoteo.customermanager.generics.crud.GenericCRUDServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ClientsServiceImpl extends GenericCRUDServiceImpl<Client, Long, CreateClientDTO, UpdateClientDTO> implements ClientsService {

    public ClientsServiceImpl(ClientsRepository clientsRepository, MapperClient mapperClient) {
        super(clientsRepository, mapperClient);
    }




}
