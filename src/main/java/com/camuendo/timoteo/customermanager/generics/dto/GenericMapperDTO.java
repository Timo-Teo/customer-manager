package com.camuendo.timoteo.customermanager.generics.dto;

public interface GenericMapperDTO<E, C, U> {
    E dtoToEntity(C createDto);

    void updateEntityWithDto(E entity, U updateDto);
}
