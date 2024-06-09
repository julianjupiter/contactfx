package com.julianjupiter.contactfx.dao;

import com.julianjupiter.contactfx.dao.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Julian Jupiter
 */
public class ContactMapper implements RowMapper<Contact> {
    @Override
    public Contact map(ResultSet resultSet) throws SQLException {
        var createdAt = resultSet.getTimestamp("created_at");
        var updatedAt = resultSet.getTimestamp("updated_at");

        return new Contact(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("mobile_number"),
                resultSet.getString("telephone_number"),
                resultSet.getString("email_address"),
                resultSet.getString("address"),
                resultSet.getString("barangay"),
                resultSet.getString("city_or_municipality"),
                resultSet.getString("province"),
                resultSet.getString("zip_code"),
                createdAt != null ? createdAt.toInstant() : null,
                updatedAt != null ? updatedAt.toInstant() : null
        );
    }
}
