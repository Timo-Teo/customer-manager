package com.camuendo.timoteo.customermanager.core.clients.map;

import com.camuendo.timoteo.customermanager.common.util.NullFieldChecker;
import com.camuendo.timoteo.customermanager.core.clients.dto.CreateClientDTO;
import com.camuendo.timoteo.customermanager.core.clients.dto.UpdateClientDTO;
import com.camuendo.timoteo.customermanager.core.clients.entities.Client;
import com.camuendo.timoteo.customermanager.generics.dto.GenericMapperDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperClient implements GenericMapperDTO<Client, CreateClientDTO, UpdateClientDTO> {

    @Override
    public Client dtoToEntity(CreateClientDTO createDto) {
        Client client = new Client();
        client.setName(createDto.getName());
        client.setLastname(createDto.getLastname());

        return client;
    }

    @Override
    public void updateEntityWithDto(Client entity, UpdateClientDTO updateDto) {
        if (NullFieldChecker.areAllFieldsNull(updateDto)) {
            throw new IllegalArgumentException("All fields are null. No update will be performed.");
        }

        Optional.ofNullable(updateDto.getName()).ifPresent(entity::setName);
        Optional.ofNullable(updateDto.getLastname()).ifPresent(entity::setLastname);
    }


}