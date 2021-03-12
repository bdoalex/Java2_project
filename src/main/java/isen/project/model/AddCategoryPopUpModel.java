package isen.project.model;

import isen.project.App;
import isen.project.model.daos.CategoryDao;
import isen.project.model.entities.Category;
import javafx.collections.ObservableList;

import java.io.IOException;

public class AddCategoryPopUpModel {





    public void handleValidate(String textFieldName,HomeScreenModel parentModel) throws IOException {

        if (Boolean.TRUE.equals(parentModel.getAllCategories().stream().anyMatch(c -> c.getName().equals(textFieldName) ))) {
            App.showFailureSnackBar("category with this name already exist");
        } else {
            CategoryDao dao = new CategoryDao();
            Category newCategory = dao.addCategory(textFieldName);
            parentModel.addOneCateogry(newCategory);
        }
    }


}
