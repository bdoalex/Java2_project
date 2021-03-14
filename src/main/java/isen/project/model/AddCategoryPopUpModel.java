package isen.project.model;

import isen.project.App;

import java.io.IOException;

/**
 * The type Add category pop up model.
 */
public class AddCategoryPopUpModel {


    /**
     * Handle validate.
     *
     * @param textFieldName the text field name
     * @param parentModel   the parent model
     * @throws IOException the io exception
     */
    public void handleValidate(String textFieldName,HomeScreenModel parentModel) throws IOException {
        if(textFieldName.isEmpty() || textFieldName.isBlank()){
            App.showFailureSnackBar("please precise a valid name ");
        }
        else if (Boolean.TRUE.equals(parentModel.getAllCategories().stream().anyMatch(c -> c.getName().equals(textFieldName) ))) {
            App.showFailureSnackBar("category with this name already exist");
        } else {
            parentModel.addOneCategory(textFieldName);
            App.showSuccessSnackBar("success");
            App.closeDialog();
        }
    }


}
