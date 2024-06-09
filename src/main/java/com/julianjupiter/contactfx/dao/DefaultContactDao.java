package com.julianjupiter.contactfx.dao;

import com.julianjupiter.contactfx.dao.core.DataSourceFactory;
import com.julianjupiter.contactfx.util.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Julian Jupiter
 */
public class DefaultContactDao implements ContactDao {
    private static final String SQL_SELECT_ALL = """
            SELECT
                id, first_name, last_name, mobile_number, telephone_number, email_address,
                address, barangay, city_or_municipality, province, zip_code,
                created_at, updated_at
            FROM contact
            """;
    private static final String SQL_SELECT_BY_ID = """
            SELECT
                id, first_name, last_name, mobile_number, telephone_number, email_address,
                address, barangay, city_or_municipality, province, zip_code,
                created_at, updated_at
            FROM contact
            WHERE id = ?
            """;
    private static final String SQL_INSERT = """
            INSERT INTO contact (id, first_name, last_name, mobile_number, telephone_number, email_address,
                address, barangay, city_or_municipality, province, zip_code, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String SQL_UPDATE = """
            UPDATE contact SET
                id = ?, first_name = ?, last_name = ?, mobile_number = ?, telephone_number = ?,
                email_address = ?, address = ?, barangay = ?, city_or_municipality = ?, province = ?,
                zip_code = ?, updated_at =?
            WHERE id = ?
            """;
    private static final String SQL_DELETE = "DELETE FROM contact WHERE id = ?";
    private final Connection connection;
    private final ContactMapper contactMapper;

    public DefaultContactDao() {
        this.connection = DataSourceFactory.connection();
        this.contactMapper = new ContactMapper();
    }

    @Override
    public List<Contact> findAll() {
        var contacts = new ArrayList<Contact>();
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var contact = this.contactMapper.map(resultSet);
                contacts.add(contact);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return contacts;
    }

    @Override
    public Optional<Contact> findById(Long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var contact = this.contactMapper.map(resultSet);
                return Optional.of(contact);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Contact save(Contact contact) {
        var contactId = contact.id() == null ? IdGenerator.generate() : contact.id();
        var existingContact = this.findById(contactId);

        if (existingContact.isPresent()) {
            try (var statement = this.connection.prepareStatement(SQL_UPDATE)) {
                var i = 1;
                statement.setString(i++, contact.firstName());
                statement.setString(i++, contact.lastName());
                statement.setString(i++, contact.mobileNumber());
                statement.setString(i++, contact.telephoneNumber());
                statement.setString(i++, contact.emailAddress());
                statement.setString(i++, contact.address());
                statement.setString(i++, contact.cityOrMunicipality());
                statement.setString(i++, contact.province());
                statement.setString(i++, contact.zipCode());
                statement.setTimestamp(i++, Timestamp.from(contact.updatedAt()));
                statement.setLong(i++, contactId);
                int row = statement.executeUpdate();
                if (row == 1) {
                    return contact;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } else {
            try (var statement = this.connection.prepareStatement(SQL_INSERT)) {
                var i = 1;
                statement.setLong(i++, contactId);
                statement.setString(i++, contact.firstName());
                statement.setString(i++, contact.lastName());
                statement.setString(i++, contact.mobileNumber());
                statement.setString(i++, contact.telephoneNumber());
                statement.setString(i++, contact.emailAddress());
                statement.setString(i++, contact.address());
                statement.setString(i++, contact.barangay());
                statement.setString(i++, contact.cityOrMunicipality());
                statement.setString(i++, contact.province());
                statement.setString(i++, contact.zipCode());
                statement.setTimestamp(i++, Timestamp.from(contact.createdAt()));
                int row = statement.executeUpdate();
                if (row == 1) {
                    return contact;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_DELETE)) {
            statement.setLong(1, id);
            int row = statement.executeUpdate();
            return row == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
