package isen.project.view;

import isen.project.App;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;

/**
 * The type Credit view controller.
 */
public class CreditViewController {
    @FXML
    private Circle memberIcon1;

    @FXML
    private Circle memberIcon2;

    @FXML
    private Circle memberIcon3;

    @FXML
    private Circle memberIcon4;

    /**
     * Handle back to menu.
     */
    @FXML
    void handleBackToMenu() {
        App.showView("LauncherScreen");

    }

    @FXML
    private void initialize() {
        URL path = getClass().getResource("/isen/project/image/members");
        Image alexImage = new Image(path + "/alexandre.jpg",false);
        Image flavienImage = new Image(path + "/flavien.jpeg",false);
        Image quentinImage = new Image(path + "/quentin.jpeg",false);
        Image louisImage = new Image(path + "/louis.jpg",false);

        memberIcon1.setFill(new ImagePattern(alexImage));
        memberIcon2.setFill(new ImagePattern(flavienImage));
        memberIcon3.setFill(new ImagePattern(quentinImage));
        memberIcon4.setFill(new ImagePattern(louisImage));



    }

}
