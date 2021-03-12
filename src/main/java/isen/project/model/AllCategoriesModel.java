package isen.project.model;

import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import javafx.collections.ObservableList;

public class AllCategoriesModel {




    private ObservableList<Category> allCategories;

    public void setAllCategories(ObservableList<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public ObservableList<Category> getAllCategories() {
        return allCategories;
    }
}
