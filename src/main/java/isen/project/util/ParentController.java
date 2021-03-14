package isen.project.util;

import isen.project.view.HomeScreenController;
import javafx.fxml.FXML;

/**
 * The type Parent controller.
 */
public class ParentController {


    /**
     * Gets home screen parent controller.
     *
     * @return the home screen parent controller
     */
    public HomeScreenController getHomeScreenParentController() {
        return homeScreenParentController;
    }

    /**
     * Sets parent controller.
     *
     * @param parentController the parent controller
     */
    public void setParentController(HomeScreenController parentController) {
        this.homeScreenParentController = parentController;
    }

    /**
     * The Home screen parent controller.
     */
    @FXML
    protected HomeScreenController homeScreenParentController;

}
