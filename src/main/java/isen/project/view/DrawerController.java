package isen.project.view;

import isen.project.App;
import isen.project.ParentController;
import isen.project.model.IOContacts;
import isen.project.util.Constant;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;

public class DrawerController  extends ParentController {

    IOContacts ioContacts = new IOContacts();

    @FXML
    VBox container;

    //the clip is for the overflow
    private Rectangle clip;
    private Boolean isOpen = false;

    /**
     * Animation for the drawer
     * we change the width and the clip ( which correspond to the overflow)
     * @param newSize of the container
     */
    public void animationChangeWidthContainer(int newSize) {


        Duration cycleDuration = Duration.millis(500);
        Timeline timeline = new Timeline(
                new KeyFrame(cycleDuration, new KeyValue(container.prefWidthProperty(), newSize, Interpolator.EASE_BOTH)),
                new KeyFrame(cycleDuration, new KeyValue(clip.widthProperty(), newSize, Interpolator.EASE_BOTH))
        );

        timeline.play();
    }

    /**
     * Click on the menu
     */
    public void clickOnHamburger() {


        isOpen = !isOpen;
        if (isOpen) {
            animationChangeWidthContainer(Constant.WIDTH_DRAWER_OPEN);
        } else {
            animationChangeWidthContainer(Constant.WIDTH_DRAWER_CLOSE);

        }
    }

    @FXML
    public void initialize() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        clip = new Rectangle(Constant.WIDTH_DRAWER_CLOSE, screenBounds.getHeight());
        container.setPrefWidth(Constant.WIDTH_DRAWER_CLOSE);

        container.setClip(clip);

    }

    @FXML
    public void homeMouseClicked() throws IOException {
        homeScreenParentController.changeViewToAllContact();
    }

    @FXML
    public void addContactMouseClicked() throws IOException {
        homeScreenParentController.changeViewToAddContact();

    }

    /**
     * Display the view of Launcher Screen
     *
     */
    @FXML
    public void backButtonMouseClicked() {
        App.showView("LauncherScreen");
    }

    @FXML

    public void importClicked() throws IOException {
        IOContacts ioContacts = new IOContacts();

        ioContacts.importData(homeScreenParentController);
}
    @FXML
    public void exportClicked(){
        ioContacts.exportData();
    }

    //Allow the drawer to change the content of the main view
    //That's why we need to get the parent

}
