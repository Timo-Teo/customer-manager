package com.camuendo.timoteo.customermanager.generics.crud;

import com.camuendo.timoteo.customermanager.generics.dto.GenericMapperDTO;
import com.camuendo.timoteo.customermanager.generics.repositories.GenericRepository;
import jakarta.persistence.EntityNotFoundException;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class GenericCRUDServiceImpl<T, ID, C, U> implements GenericCRUDService<T, ID, C, U> {

    private final GenericRepository<T, ID> repository;
    private final GenericMapperDTO<T, C, U> converter;

    public GenericCRUDServiceImpl(GenericRepository<T, ID> repository, GenericMapperDTO<T, C, U> converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public T save(C createDto) {
        T entity = converter.dtoToEntity(createDto);
        return repository.save(entity);
    }

    @Override
    public T update(ID id, U updateDto) {
        T entityToUpdate = findById(id);
        converter.updateEntityWithDto(entityToUpdate, updateDto);
        return repository.save(entityToUpdate);
    }

    @Override
    public void delete(ID id) {
        T entity = findById(id);
        repository.delete(entity);

    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(ID id) {
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        String entityName = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
        throw new EntityNotFoundException(entityName + " with ID: " + id + " not found");
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
