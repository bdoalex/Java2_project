package isen.project.view;

import isen.project.App;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.PersonListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/**
 * @author Alexandre BARBOSA DE OLIVEIRA
 * Controller de la vue HomScreen
 */
public class HomeScreenController {

    PersonDao personDao = new PersonDao();

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
    private DatePicker birthDatePicker;

    private Person currentPerson;


    private ObservableList<Person> contactObservableList = FXCollections.observableArrayList();


    /**
     * Complete the list
     */
    public void populateList() {
        if (personDao.getPersons() != null) {
            contactObservableList = personDao.getPersons();
        }
    }

    /**
     * Display the view to add a contact
     * @throws IOException
     */
    @FXML
    public void handleAddButton() throws IOException {
        App.showView("AddContactView");
    }



    public void refreshList(){
        populateList();
        contactListView.setItems(contactObservableList);
    }






    @FXML
    private void initialize() {
        refreshList();
        contactListView.setCellFactory(contactListView -> new PersonListViewCell());


    }
}
