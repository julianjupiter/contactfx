package com.julianjupiter.contactfx.dao.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Julian Jupiter
 */
public final class DataSourceFactory {
    private static final String PROPERTIES_FILE = "application.properties";
    private static Connection connection = null;

    private DataSourceFactory() {
    }

    public static Connection connection() {
        if (connection == null) {
            try {
                var properties = properties();
                var driverClassName = properties.getProperty("app.datasource.driver-class-name");
                var url = properties.getProperty("app.datasource.url");
                var user = properties.getProperty("app.datasource.user");
                var password = properties.getProperty("app.datasource.password");
                Class.forName(driverClassName);
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to the database", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Error on JDBC driver", e);
            }
        }

        return connection;
    }

    private static Properties properties() {
        var classLoader = Thread.currentThread().getContextClassLoader();
        var properties = new Properties();
        try (var inputStream = classLoader.getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new RuntimeException("Error reading application.properties", exception);
        }

        return properties;
    }
}
