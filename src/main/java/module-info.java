module com.julianjupiter.contactfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires atlantafx.base;

    opens com.julianjupiter.contactfx to javafx.fxml;
    exports com.julianjupiter.contactfx;
    opens com.julianjupiter.contactfx.controller to javafx.fxml;
    exports com.julianjupiter.contactfx.controller;
    exports com.julianjupiter.contactfx.dto;
    opens com.julianjupiter.contactfx.dto to javafx.fxml;
}