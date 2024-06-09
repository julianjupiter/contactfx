package com.julianjupiter.contactfx;

import atlantafx.base.theme.PrimerLight;
import com.julianjupiter.contactfx.controller.MainController;
import com.julianjupiter.contactfx.util.FxmlLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ContactFxApplication extends Application {
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        BorderPane location = new FxmlLoader<MainController, BorderPane>(MainController.class)
                .component();
        Scene scene = new Scene(location);
        stage.setTitle("ContactFX");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void start(String[] args) {
        launch(args);
    }
}