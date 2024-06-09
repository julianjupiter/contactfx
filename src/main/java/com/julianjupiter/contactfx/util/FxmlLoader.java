package com.julianjupiter.contactfx.util;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Julian Jupiter
 */
public final class FxmlLoader<T extends Initializable, U extends Node> {
    private static final String FXML_EXTENSION = ".fxml";
    private final Class<T> controllerClass;
    private Map<Class<T>, Callable<T>> controllerFactory;

    public FxmlLoader(Class<T> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public void controllerFactory(Map<Class<T>, Callable<T>> controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    public U component() {
        var url = this.controllerClass.getResource(this.controllerClass.getSimpleName() + FXML_EXTENSION);
        try {
            var loader = new FXMLLoader(url);
            if (this.controllerFactory != null) {
                this.controllerFactory(loader);
            }
            return loader.load();
        } catch (IOException e) {
            throw new ContactException(e);
        }
    }

    private void controllerFactory(FXMLLoader loader) {
        loader.setControllerFactory(param -> {
            Callable<? extends Initializable> callable = controllerFactory.get(param);
            if (callable == null) {
                try {
                    return param.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                         InvocationTargetException exception) {
                    throw new IllegalStateException(exception);
                }
            } else {
                try {
                    return callable.call();
                } catch (Exception exception) {
                    throw new IllegalStateException(exception);
                }
            }
        });
    }
}
