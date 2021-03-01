package isen.project.view;

import isen.project.App;
import isen.project.model.HomeScreenModel;
import isen.project.model.entities.Person;
import isen.project.util.PersonListViewCell;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/**
 * @author Alexandre BARBOSA DE OLIVEIRA
 * Controller de la vue HomScreen
 */
public class HomeScreenController {


    @FXML
    public TextField textFieldFilter;

    @FXML
    public SplitPane homeScreenPane;

    @FXML
    private ListView contactListView;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private AnchorPane rightAnchorPane;

    @FXML
    private AnchorPane formPane;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nickNameTextField;

    @FXML
    private DatePicker birthDateTextField;

    @FXML
    private TextField PhoneTextField;

    @FXML
    private Button validateButton;

    @FXML
    private Button backButton;

    @FXML
    private DatePicker birthDatePicker;


    HomeScreenModel homeScreenModel = new HomeScreenModel();


    @FXML
    public void handleFilterButton() throws IOException {

    }

    /**
     * Complete the list
     */


    /**
     * Display the view to add a contact
     *
     * @throws IOException
     */
    @FXML
    public void handleAddButton() throws IOException {
        App.showView("AddContactView");
    }

    /**
     * Display the view of Launcher Screen
     *
     * @throws IOException
     */
    @FXML
    public void handleBackButton() throws IOException {
        App.showView("LauncherScreen");
    }

    @FXML
    private void initialize() {
        contactListView.setItems(homeScreenModel.getContactShown());
        contactListView.setCellFactory(contactListView -> new PersonListViewCell());
        textFieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            homeScreenModel.Filter(observable, oldValue, newValue);
            contactListView.setItems(homeScreenModel.getContactShown());
        });

    }
}
