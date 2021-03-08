package isen.project.view;

import isen.project.App;
import javafx.fxml.FXML;

public class LauncherScreenController {

    /**
     */
    @FXML
    private void handleLaunchButton() {
        App.showView("HomeScreen");
    }

    /**
     * toDo
     */
    @FXML
    private void handleCreditButton(){
        App.showView("CreditView");
    }

}
