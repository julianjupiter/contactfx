package com.julianjupiter.contactfx.dao.core;

import java.util.List;
import java.util.Optional;

/**
 * @author Julian Jupiter
 */
public interface Dao<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    Boolean deleteById(Long id);
}
