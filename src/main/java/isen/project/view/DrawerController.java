package isen.project.view;

import isen.project.App;
import isen.project.model.IOContacts;
import isen.project.util.Constant;
import isen.project.util.ParentController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;

/**
 * The type Drawer controller.
 */
public class DrawerController extends ParentController {

    /**
     * The Io contacts.
     */
    IOContacts ioContacts = new IOContacts();

    /**
     * The Container.
     */
    @FXML
    VBox container;

    //the clip is for the overflow
    private Rectangle clip;
    private Boolean isOpen = false;

    /**
     * Animation for the drawer
     * we change the width and the clip ( which correspond to the overflow)
     *
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

        if (Boolean.TRUE.equals(isOpen)) {
            animationChangeWidthContainer(Constant.WIDTH_DRAWER_OPEN);
        } else {
            animationChangeWidthContainer(Constant.WIDTH_DRAWER_CLOSE);

        }
    }



    /**
     * Home mouse clicked.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void homeMouseClicked() throws IOException {
        homeScreenParentController.changeViewToAllContact();
    }

    /**
     * Add contact mouse clicked.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void addContactMouseClicked() throws IOException {
        homeScreenParentController.changeViewToAddContact();

    }

    /**
     * Display the view of Launcher Screen
     */
    @FXML
    public void backButtonMouseClicked() {
        App.showView("LauncherScreen");
    }

    /**
     * Import data when button is clicked.
     */
    @FXML

    public void importClicked() {
        ioContacts.importData(homeScreenParentController);
    }

    /**
     * Export clicked.
     */
    @FXML
    public void exportClicked() throws IOException {
        FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/isen/project/view/ExportView.fxml"));
        VBox layout = mLLoader.load();

        ExportViewController controller = mLLoader.getController();
        controller.setParentController(homeScreenParentController);
        controller.fillComboBoxCategory();

        App.showDialog(layout);

    }

    /**
     * Handle go to all categories.
     *
     * @throws IOException the io exception
     */
    public void handleGoToAllCategories() throws IOException {
        homeScreenParentController.changeViewToAllCategories();
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        clip = new Rectangle(Constant.WIDTH_DRAWER_CLOSE, screenBounds.getHeight());
        container.setPrefWidth(Constant.WIDTH_DRAWER_CLOSE);

        container.setClip(clip);

    }


}
