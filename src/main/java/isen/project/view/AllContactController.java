package isen.project.view;

import com.jfoenix.controls.JFXTextField;
import isen.project.util.ParentController;
import isen.project.model.AllContactModel;
import isen.project.model.entities.Person;
import isen.project.util.PersonListViewCell;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;

/**
 * The type All contact controller.
 */
public class AllContactController extends ParentController {


    /**
     * The Text field filter.
     */
    @FXML
    public JFXTextField textFieldFilter;
    @FXML
    private ListView<Person> contactListView;

    /**
     * The All contact model.
     */
    AllContactModel allContactModel;


    /**
     * Sets all contact.
     *
     * @param allContact the all contact
     */
    public void setAllContact(ObservableList<Person> allContact) {

        //Todo is possible that all listener stack , we have to clear properly previous listener
        allContact.addListener((ListChangeListener<Person>) change -> allContactModel.setAllContact((ObservableList<Person>) change.getList()));

        allContactModel = new AllContactModel(allContact);
        contactListView.setItems(allContactModel.getContactShown());
        contactListView.setCellFactory(temp -> new PersonListViewCell(this.contactListView));

    }


    /**
     * Initialize.
     */
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
