package isen.project.view;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import isen.project.model.AllContactModel;
import isen.project.util.Constant;
import isen.project.util.PersonListViewCell;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class AllContactController {



    @FXML
    public JFXTextField textFieldFilter;
    @FXML
    private ListView contactListView;

    AllContactModel allContactModel = new AllContactModel();

    @FXML
    public void initialize(){
        contactListView.setItems(allContactModel.getContactShown());
        contactListView.setCellFactory(contactListView -> new PersonListViewCell(this.contactListView));
        textFieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            allContactModel.Filter(observable, oldValue, newValue);
            contactListView.setItems(allContactModel.getContactShown());
        });
    }
}
