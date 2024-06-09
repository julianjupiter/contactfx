package com.julianjupiter.contactfx.dao.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Julian Jupiter
 */
@FunctionalInterface
public interface RowMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
