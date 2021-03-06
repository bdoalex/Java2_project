package isen.project.view;

import com.jfoenix.controls.JFXTextField;
import isen.project.ParentController;
import isen.project.model.AllContactModel;
import isen.project.model.entities.Person;
import isen.project.util.PersonListViewCell;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AllContactController extends ParentController {


    @FXML
    public JFXTextField textFieldFilter;
    @FXML
    private ListView contactListView;

    AllContactModel allContactModel;


    public void setAllContact(ObservableList<Person> allContact) {
        allContactModel = new AllContactModel(allContact);
        contactListView.setItems(allContactModel.getContactShown());
        contactListView.setCellFactory(contactListView -> new PersonListViewCell(this.contactListView));

    }

    public void modifyOneContact(int index, Person newPerson) {
        allContactModel.modifyOneContact(index, newPerson);
        homeScreenParentController.getHomeScreenModel().modifyOneContact(index, newPerson);
    }

    @FXML
    public void initialize() {
        textFieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            allContactModel.Filter(observable, oldValue, newValue);
            contactListView.setItems(allContactModel.getContactShown());
        });
        contactListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (contactListView.getSelectionModel().getSelectedItem() != null) {
                    Person person = (Person) contactListView.getSelectionModel().getSelectedItem();
                    try {
                        homeScreenParentController.changeViewToOneContact(person);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
