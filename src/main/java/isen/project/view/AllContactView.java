package isen.project.view;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import isen.project.model.AllContactModel;
import isen.project.util.PersonListViewCell;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AllContactView {



    @FXML
    public JFXTextField textFieldFilter;
    @FXML
    private JFXListView contactListView;

    AllContactModel allContactModel = new AllContactModel();

    @FXML
    public void initialize(){
        contactListView.setItems(allContactModel.getContactShown());
        contactListView.setCellFactory(contactListView -> new PersonListViewCell());
        textFieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            allContactModel.Filter(observable, oldValue, newValue);
            contactListView.setItems(allContactModel.getContactShown());
        });
    }
}
