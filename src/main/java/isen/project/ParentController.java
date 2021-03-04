package isen.project;

import isen.project.view.HomeScreenController;
import javafx.fxml.FXML;

public class ParentController {


    public HomeScreenController getHomeScreenParentController() {
        return homeScreenParentController;
    }

    public void setParentController(HomeScreenController parentController) {
        this.homeScreenParentController = parentController;
    }

    @FXML
    protected HomeScreenController homeScreenParentController;

}
