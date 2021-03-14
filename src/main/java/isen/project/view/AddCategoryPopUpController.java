package isen.project.view;

import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.util.ParentController;
import isen.project.model.AddCategoryPopUpModel;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The type Add category pop up controller.
 */
public class AddCategoryPopUpController extends ParentController {

    /**
     * The Text field category name.
     */
    @FXML
    public JFXTextField textFieldCategoryName;
    /**
     * The Model.
     */
    AddCategoryPopUpModel model = new AddCategoryPopUpModel();


    /**
     * Handle button accept.
     *
     * @throws IOException the io exception
     */
    public void handleButtonAccept() throws IOException {

        model.handleValidate(textFieldCategoryName.getText(),homeScreenParentController.getHomeScreenModel());

    }

    /**
     * Handle button cancel.
     */
    public void handleButtonCancel() {

        App.closeDialog();
    }
}
