package isen.project.view;

import isen.project.App;
import isen.project.ParentController;
import isen.project.model.AllCategoriesModel;
import isen.project.model.entities.Category;
import isen.project.util.CategoriesListViewCell;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class AllCategoriesController extends ParentController {

    @FXML
    public ListView categoriesListView;

    private AllCategoriesModel model = new AllCategoriesModel();

    public void setAllCategories(ObservableList<Category> allCategories) {

        //Todo is possible that all listener stack , we have to clear properly previous listener
        allCategories.addListener((ListChangeListener<Category>) change -> this.model.setAllCategories((ObservableList<Category>) change.getList()));

        model.setAllCategories(allCategories);
        categoriesListView.setItems(model.getAllCategories());
        categoriesListView.setCellFactory(temp -> new CategoriesListViewCell(this.categoriesListView,homeScreenParentController.getHomeScreenModel()));

    }

    @FXML
    public void handleClickOnAddCategory() throws IOException {
        FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/isen/project/view/AddCategoryPopUp.fxml"));
        VBox layout = mLLoader.load();

        AddCategoryPopUpController controller = mLLoader.getController();
        controller.setParentController(homeScreenParentController);
        App.showDialog(layout);
    }
}
