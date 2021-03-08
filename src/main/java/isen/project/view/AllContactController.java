package isen.project.view;

import com.jfoenix.controls.JFXTextField;
import isen.project.ParentController;
import isen.project.model.AllContactModel;
import isen.project.model.entities.Person;
import isen.project.util.PersonListViewCell;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;

public class AllContactController extends ParentController {


    @FXML
    public JFXTextField textFieldFilter;
    @FXML
    private ListView<Person> contactListView;

    AllContactModel allContactModel;



    public void setAllContact(ObservableList<Person> allContact) {
        allContactModel = new AllContactModel(allContact);
        contactListView.setItems(allContactModel.getContactShown());
        contactListView.setCellFactory(contactListView -> new PersonListViewCell(this.contactListView));

    }



    @FXML
    public void initialize() {
        textFieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            allContactModel.filter(newValue);
            contactListView.setItems(allContactModel.getContactShown());
        });
        contactListView.setOnMouseClicked(event -> {
            if (contactListView.getSelectionModel().getSelectedItem() != null) {
                Person person = contactListView.getSelectionModel().getSelectedItem();
                try {
                    homeScreenParentController.changeViewToOneContact(person);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
