package com.julianjupiter.contactfx.service;

import com.julianjupiter.contactfx.dto.CreateContactDto;
import com.julianjupiter.contactfx.dto.UpdateContactDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Julian Jupiter
 */
public interface ContactService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T create(CreateContactDto createContactDto);

    T update(Long id, UpdateContactDto updateContactDto);

    boolean deleteById(Long id);
}
