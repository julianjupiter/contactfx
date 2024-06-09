package com.julianjupiter.contactfx.service;

import com.julianjupiter.contactfx.dao.Contact;
import com.julianjupiter.contactfx.dao.ContactDao;
import com.julianjupiter.contactfx.dao.DefaultContactDao;
import com.julianjupiter.contactfx.dto.CreateContactDto;
import com.julianjupiter.contactfx.dto.UpdateContactDto;
import com.julianjupiter.contactfx.util.IdGenerator;
import com.julianjupiter.contactfx.dto.ContactDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Julian Jupiter
 */
public class DefaultContactService implements ContactService<ContactDto> {
    private final ContactDao contactDao;

    public DefaultContactService() {
        this.contactDao = new DefaultContactDao();
    }

    @Override
    public List<ContactDto> findAll() {
        return this.contactDao.findAll().stream()
                .map(this::mapToContactProperty)
                .toList();
    }

    @Override
    public Optional<ContactDto> findById(Long id) {
        return this.contactDao.findById(id)
                .map(this::mapToContactProperty);
    }

    @Override
    public ContactDto create(CreateContactDto createContactDto) {
        var newContact = new Contact(
                IdGenerator.generate(),
                createContactDto.firstName(),
                createContactDto.lastName(),
                createContactDto.mobileNumber(),
                createContactDto.telephoneNumber(),
                createContactDto.emailAddress(),
                createContactDto.address(),
                createContactDto.barangay(),
                createContactDto.cityOrMunicipality(),
                createContactDto.province(),
                createContactDto.zipCode(),
                createContactDto.createdAt(),
                null
        );
        Contact savedContact = this.contactDao.save(newContact);

        return this.mapToContactProperty(savedContact);
    }

    @Override
    public ContactDto update(Long id, UpdateContactDto updateContactDto) {
        var updateContact = new Contact(
                id,
                updateContactDto.firstName(),
                updateContactDto.lastName(),
                updateContactDto.mobileNumber(),
                updateContactDto.telephoneNumber(),
                updateContactDto.emailAddress(),
                updateContactDto.address(),
                updateContactDto.barangay(),
                updateContactDto.cityOrMunicipality(),
                updateContactDto.province(),
                updateContactDto.zipCode(),
                updateContactDto.createdAt(),
                updateContactDto.updatedAt()
        );
        Contact updatedContact = this.contactDao.save(updateContact);

        return this.mapToContactProperty(updatedContact);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.contactDao.deleteById(id);
    }

    private ContactDto mapToContactProperty(Contact contact) {
        return new ContactDto(
                contact.id(),
                contact.firstName(),
                contact.lastName(),
                contact.mobileNumber(),
                contact.telephoneNumber(),
                contact.emailAddress(),
                contact.address(),
                contact.barangay(),
                contact.cityOrMunicipality(),
                contact.province(),
                contact.zipCode(),
                contact.createdAt(),
                contact.updatedAt()
        );
    }
}
