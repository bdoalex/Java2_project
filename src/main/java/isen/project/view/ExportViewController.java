package isen.project.view;

import com.jfoenix.controls.JFXComboBox;
import isen.project.App;
import isen.project.model.IOContacts;
import isen.project.model.entities.Category;
import isen.project.util.ParentController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.util.StringConverter;

import java.io.IOException;

public class ExportViewController extends ParentController {

    IOContacts ioContacts = new IOContacts();

    @FXML
    private JFXComboBox<Category> comboBoxCategory;

    @FXML
    void exportAllContacts() throws IOException {

        ioContacts.exportData();
        App.showSuccessSnackBar("success");
        App.closeDialog();
    }

    @FXML
    void exportCategory() throws IOException {
        if (comboBoxCategory.getValue()==null){
            App.showFailureSnackBar("Error, choose a category");

        }
        else {
            ioContacts.exportData(comboBoxCategory.getValue());
            App.showSuccessSnackBar("success");
            App.closeDialog();
        }


    }


    /**
     * Fill combo box category.
     */
    public void fillComboBoxCategory() {


        comboBoxCategory.getItems().addAll(homeScreenParentController.getHomeScreenModel().getAllCategories());


        comboBoxCategory.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category category) {

                if (category != null) {
                    return category.getName();
                }
                return "";

            }

            @Override
            public Category fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    void initialize() {
    }
}
