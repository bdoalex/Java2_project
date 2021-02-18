package isen.project.view;

import isen.project.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LauncherScreenController {

    @FXML
    private Button launchButton;


    /**
     * @throws IOException
     * Show the homeScreenVien when you click the launch button
     */
    @FXML
    private void handleLaunchButton() throws IOException{
        App.showView("HomeScreen");
    }

    /**
     * toDo
     */
    @FXML
    private void handleCreditButton(){

    }

}
