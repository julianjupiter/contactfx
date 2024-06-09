package com.julianjupiter.contactfx.dto;

import java.time.Instant;

/**
 * @author Julian Jupiter
 */
public record CreateContactDto(
        String firstName,
        String lastName,
        String mobileNumber,
        String telephoneNumber,
        String emailAddress,
        String address,
        String barangay,
        String cityOrMunicipality,
        String province,
        String zipCode,
        Instant createdAt) {
}
