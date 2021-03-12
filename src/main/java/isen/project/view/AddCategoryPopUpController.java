package isen.project.view;

import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.ParentController;
import isen.project.model.AddCategoryPopUpModel;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddCategoryPopUpController extends ParentController {

    @FXML
    public JFXTextField textFieldCategoryName;
    AddCategoryPopUpModel model = new AddCategoryPopUpModel();


    public void handleButtonAccept() throws IOException {

        model.handleValidate(textFieldCategoryName.getText(),homeScreenParentController.getHomeScreenModel());

    }

    public void handleButtonCancel() {

        App.closeDialog();
    }
}
