package isen.project.view;

import isen.project.util.Constant;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;

public class DrawerController {

    @FXML
    private HomeScreenController parentController;

    @FXML
    VBox container;

    private Rectangle clip;
    private Boolean isOpen = false;


    public void AnimationChangeWidthContainer(int newSize) {


        Duration cycleDuration = Duration.millis(500);
        Timeline timeline = new Timeline(
                new KeyFrame(cycleDuration, new KeyValue(container.prefWidthProperty(), newSize, Interpolator.EASE_BOTH)),
                new KeyFrame(cycleDuration, new KeyValue(clip.widthProperty(), newSize, Interpolator.EASE_BOTH))
        );

        timeline.play();
    }


    public void ClickOnHamburger() {


        isOpen = !isOpen;
        if (isOpen) {
            AnimationChangeWidthContainer(Constant.WIDTH_DRAWER_OPEN);
        } else {
            AnimationChangeWidthContainer(Constant.WIDTH_DRAWER_CLOSE);

        }
    }

    @FXML
    public void initialize() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        clip = new Rectangle(Constant.WIDTH_DRAWER_CLOSE, screenBounds.getHeight());
        container.setPrefWidth(Constant.WIDTH_DRAWER_CLOSE);
        container.setClip(clip);

    }

    public void HandleAddContact(MouseEvent mouseEvent) throws IOException {
        parentController.HandleAddContact();
    }

    public void SetParentController(HomeScreenController parentController) {
        this.parentController = parentController;
    }
}
