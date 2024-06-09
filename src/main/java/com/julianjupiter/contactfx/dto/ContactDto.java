package com.julianjupiter.contactfx.dto;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.Instant;

/**
 * @author Julian Jupiter
 */
public class ContactDto {
    private final LongProperty id;
    private final StringProperty name;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty mobileNumber;
    private final StringProperty telephoneNumber;
    private final StringProperty emailAddress;
    private final StringProperty address;
    private final StringProperty barangay;
    private final StringProperty cityOrMunicipality;
    private final StringProperty province;
    private final StringProperty zipCode;
    private final ObjectProperty<Instant> createdAt;
    private final ObjectProperty<Instant> updatedAt;

    public ContactDto() {
        this.id =  new SimpleLongProperty();
        this.name = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.mobileNumber = new SimpleStringProperty();
        this.telephoneNumber = new SimpleStringProperty();
        this.emailAddress = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.barangay = new SimpleStringProperty();
        this.cityOrMunicipality = new SimpleStringProperty();
        this.province = new SimpleStringProperty();
        this.zipCode = new SimpleStringProperty();
        this.createdAt = new SimpleObjectProperty<>();
        this.updatedAt = new SimpleObjectProperty<>();
    }

    public ContactDto(Long id, String firstName, String lastName,
                      String mobileNumber, String telephoneNumber, String emailAddress,
                      String address, String barangay, String cityOrMunicipality, String province, String zipCode,
                      Instant createdAt, Instant updatedAt) {
        this.id =  new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(firstName + " " + lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.mobileNumber = new SimpleStringProperty(mobileNumber);
        this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
        this.emailAddress = new SimpleStringProperty(emailAddress);
        this.address = new SimpleStringProperty(address);
        this.barangay = new SimpleStringProperty(barangay);
        this.cityOrMunicipality = new SimpleStringProperty(cityOrMunicipality);
        this.province = new SimpleStringProperty(province);
        this.zipCode = new SimpleStringProperty(zipCode);
        this.createdAt = new SimpleObjectProperty<>(createdAt);
        this.updatedAt = new SimpleObjectProperty<>(updatedAt);
    }

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public ContactDto setId(Long id) {
        this.id.set(id);
        return this;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public ContactDto setFirstName(String firstName) {
        this.firstName.set(firstName);
        return this;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ContactDto setLastName(String lastName) {
        this.lastName.set(lastName);
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber.get();
    }

    public StringProperty mobileNumberProperty() {
        return mobileNumber;
    }

    public ContactDto setMobileNumber(String mobileNumber) {
        this.mobileNumber.set(mobileNumber);
        return this;
    }

    public String getTelephoneNumber() {
        return telephoneNumber.get();
    }

    public StringProperty telephoneNumberProperty() {
        return telephoneNumber;
    }

    public ContactDto setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber.set(telephoneNumber);
        return this;
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public ContactDto setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
        return this;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public ContactDto setAddress(String address) {
        this.address.set(address);
        return this;
    }

    public String getBarangay() {
        return barangay.get();
    }

    public StringProperty barangayProperty() {
        return barangay;
    }

    public ContactDto setBarangay(String barangay) {
        this.barangay.set(barangay);
        return this;
    }

    public String getCityOrMunicipality() {
        return cityOrMunicipality.get();
    }

    public StringProperty cityOrMunicipalityProperty() {
        return cityOrMunicipality;
    }

    public ContactDto setCityOrMunicipality(String cityOrMunicipality) {
        this.cityOrMunicipality.set(cityOrMunicipality);
        return this;
    }

    public String getProvince() {
        return province.get();
    }

    public StringProperty provinceProperty() {
        return province;
    }

    public ContactDto setProvince(String province) {
        this.province.set(province);
        return this;
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public StringProperty zipCodeProperty() {
        return zipCode;
    }

    public ContactDto setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt.get();
    }

    public ObjectProperty<Instant> createdAtProperty() {
        return createdAt;
    }

    public ContactDto setCreatedAt(Instant createdAt) {
        this.createdAt.set(createdAt);
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt.get();
    }

    public ObjectProperty<Instant> updatedAtProperty() {
        return updatedAt;
    }

    public ContactDto setUpdatedAt(Instant updatedAt) {
        this.updatedAt.set(updatedAt);
        return this;
    }
}
