package com.julianjupiter.contactfx.controller;

import com.julianjupiter.contactfx.dto.ContactDto;
import com.julianjupiter.contactfx.service.ContactService;
import com.julianjupiter.contactfx.service.DefaultContactService;
import com.julianjupiter.contactfx.util.FxmlLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Julian Jupiter
 */
public class MainController implements Initializable {
    @FXML
    private BorderPane contactBorderPane;
    @FXML
    private GridPane contactGridPane;
    @FXML
    private Button editContactButton;
    @FXML
    private Button saveContactButton;
    @FXML
    private TableView<ContactDto> contactTableView;
    @FXML
    private TableColumn<ContactDto, String> firstNameTableColumn;
    @FXML
    private TableColumn<ContactDto, String> lastNameTableColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField mobileNumberTextField;
    @FXML
    private TextField telephoneNumberTextField;
    @FXML
    private TextField emailAddressTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField barangayTextField;
    @FXML
    private TextField cityOrMunicipalityTextField;
    @FXML
    private TextField provinceTextField;
    @FXML
    private TextField zipCodeTextField;
    private final ContactService<ContactDto> contactService;
    private final ObjectProperty<ObservableList<ContactDto>> allContactsProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    private final ObjectProperty<ContactDto> selectedContactProperty = new SimpleObjectProperty<>();
    private final ReadOnlyBooleanWrapper editContactButtonClicked = new ReadOnlyBooleanWrapper();
    private final ReadOnlyBooleanWrapper newContactButtonClicked = new ReadOnlyBooleanWrapper();
    private boolean contactFormLoaded = false;

    public MainController() {
        this.contactService = new DefaultContactService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome();
        contactTableView();
        contactTextFields();
        buttons();
        // load all contacts
        allContactsProperty.get().setAll(contactService.findAll());
    }

    @FXML
    public void newContact(ActionEvent event) {
        if (!contactFormLoaded) {
            contactFormLoaded = true;
            contactBorderPane.setCenter(contactGridPane);
        }
        contactTableView.getSelectionModel().clearSelection();
        selectedContactProperty.set(null);
        newContactButtonClicked.set(true);
    }

    @FXML
    public void saveContact(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Save Contact");
        alert.setContentText("Contact has been saved.");
        alert.showAndWait();

        newContactButtonClicked.set(false);
        editContactButtonClicked.set(false);
    }

    @FXML
    public void editContact(ActionEvent event) {
        editContactButtonClicked.set(true);
    }

    private void welcome() {
        StackPane location = new FxmlLoader<WelcomeController, StackPane>(WelcomeController.class)
                .component();
        contactBorderPane.setCenter(location);
    }

    private void contactTableView() {
        contactTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        contactTableView.itemsProperty().bind(allContactsProperty);
        contactTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, oldContactDto, newContactDto) -> {
                    if (!contactFormLoaded) {
                        contactFormLoaded = true;
                        contactBorderPane.setCenter(contactGridPane);
                    }
                    selectedContactProperty.set(newContactDto);
                    editContactButtonClicked.set(false);
                });
        firstNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    private void contactTextFields() {
        BooleanBinding disableTextFields = Bindings.createBooleanBinding(() ->
                        !newContactButtonClicked.get() && !editContactButtonClicked.get(),
                newContactButtonClicked, editContactButtonClicked
        );
        firstNameTextField.disableProperty().bind(disableTextFields);
        lastNameTextField.disableProperty().bind(disableTextFields);
        mobileNumberTextField.disableProperty().bind(disableTextFields);
        telephoneNumberTextField.disableProperty().bind(disableTextFields);
        emailAddressTextField.disableProperty().bind(disableTextFields);
        addressTextField.disableProperty().bind(disableTextFields);
        barangayTextField.disableProperty().bind(disableTextFields);
        cityOrMunicipalityTextField.disableProperty().bind(disableTextFields);
        provinceTextField.disableProperty().bind(disableTextFields);
        zipCodeTextField.disableProperty().bind(disableTextFields);

        selectedContactProperty.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                nameLabel.textProperty().unbindBidirectional(oldValue.nameProperty());
                firstNameTextField.textProperty().unbindBidirectional(oldValue.firstNameProperty());
                lastNameTextField.textProperty().unbindBidirectional(oldValue.lastNameProperty());
                mobileNumberTextField.textProperty().unbindBidirectional(oldValue.mobileNumberProperty());
                telephoneNumberTextField.textProperty().unbindBidirectional(oldValue.telephoneNumberProperty());
                emailAddressTextField.textProperty().unbindBidirectional(oldValue.emailAddressProperty());
                addressTextField.textProperty().unbindBidirectional(oldValue.addressProperty());
                barangayTextField.textProperty().unbindBidirectional(oldValue.barangayProperty());
                cityOrMunicipalityTextField.textProperty().unbindBidirectional(oldValue.cityOrMunicipalityProperty());
                provinceTextField.textProperty().unbindBidirectional(oldValue.provinceProperty());
                zipCodeTextField.textProperty().unbindBidirectional(oldValue.zipCodeProperty());
            }

            if (newValue != null) {
                nameLabel.textProperty().bindBidirectional(newValue.nameProperty());
                firstNameTextField.textProperty().bindBidirectional(newValue.firstNameProperty());
                lastNameTextField.textProperty().bindBidirectional(newValue.lastNameProperty());
                mobileNumberTextField.textProperty().bindBidirectional(newValue.mobileNumberProperty());
                telephoneNumberTextField.textProperty().bindBidirectional(newValue.telephoneNumberProperty());
                emailAddressTextField.textProperty().bindBidirectional(newValue.emailAddressProperty());
                addressTextField.textProperty().bindBidirectional(newValue.addressProperty());
                barangayTextField.textProperty().bindBidirectional(newValue.barangayProperty());
                cityOrMunicipalityTextField.textProperty().bindBidirectional(newValue.cityOrMunicipalityProperty());
                provinceTextField.textProperty().bindBidirectional(newValue.provinceProperty());
                zipCodeTextField.textProperty().bindBidirectional(newValue.zipCodeProperty());
            } else {
                nameLabel.setText("");
                firstNameTextField.clear();
                lastNameTextField.clear();
                mobileNumberTextField.clear();
                telephoneNumberTextField.clear();
                emailAddressTextField.clear();
                addressTextField.clear();
                barangayTextField.clear();
                cityOrMunicipalityTextField.clear();
                provinceTextField.clear();
                zipCodeTextField.clear();
            }
        });
    }

    private void buttons() {
        editContactButton.disableProperty().bind(selectedContactProperty.isNull());

        BooleanBinding disableSaveContactButton = Bindings.createBooleanBinding(() ->
                        !newContactButtonClicked.get() && !editContactButtonClicked.get(),
                newContactButtonClicked, editContactButtonClicked
        );
        saveContactButton.disableProperty().bind(disableSaveContactButton);
    }
}
