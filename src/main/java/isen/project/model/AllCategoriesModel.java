package isen.project.model;

import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import javafx.collections.ObservableList;

/**
 * The type All categories model.
 */
public class AllCategoriesModel {




    private ObservableList<Category> allCategories;

    /**
     * Sets all categories.
     *
     * @param allCategories the all categories
     */
    public void setAllCategories(ObservableList<Category> allCategories) {
        this.allCategories = allCategories;
    }

    /**
     * Gets all categories.
     *
     * @return the all categories
     */
    public ObservableList<Category> getAllCategories() {
        return allCategories;
    }
}
