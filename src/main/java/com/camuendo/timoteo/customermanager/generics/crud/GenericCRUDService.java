package com.camuendo.timoteo.customermanager.generics.crud;

import java.util.List;

public interface GenericCRUDService<T, ID, C, U> {
    T save(C entity);

    T update(ID id, U entity);

    void delete(ID id);

    T findById(ID id);

    List<T> findAll();
}
