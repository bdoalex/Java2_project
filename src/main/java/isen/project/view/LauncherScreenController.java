package isen.project.view;

import isen.project.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LauncherScreenController {

    @FXML
    private Button launchButton;

    @FXML
    private void handleLaunchButton() throws IOException{
        App.showView("HomeScreen");
    }

}
