package isen.project.view;

import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.PersonListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
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
        showViewSplitPane(1, "AddContactView");
    }


    /**
     * @param index element index in the splitpane
     * @param fxml name of the view to display
     * @throws IOException
     */
    public void showViewSplitPane(int index, String fxml) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/isen/project/view/" + fxml + ".fxml"));
        rightAnchorPane = loader.load();
        homeScreenPane.getItems().set(index, rightAnchorPane);
    }


    @FXML
    private void initialize() {
        populateList();
        contactListView.setItems(contactObservableList);
        contactListView.setCellFactory(contactListView -> new PersonListViewCell());

    }
}
