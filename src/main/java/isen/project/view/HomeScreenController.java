package isen.project.view;

import isen.project.App;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.PersonListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


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




    public void populateList() {
        if (personDao.getPersons() != null) {
            contactObservableList = personDao.getPersons();
        }
    }

    @FXML
    public void handleAddButton() throws IOException {
        showViewSplitPane(1, "AddContactView");
    }


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
