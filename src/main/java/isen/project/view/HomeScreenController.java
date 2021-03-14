package isen.project.view;

import isen.project.App;
import isen.project.model.HomeScreenModel;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;


/**
 * The type Home screen controller.
 *
 */
public class HomeScreenController {


    @FXML
    private final DrawerController drawerController = new DrawerController();

    private Node lastNode;

    private HomeScreenModel homeScreenModel;

    @FXML
    private AnchorPane containerAnchorPane;


    /**
     * Change view to one contact.
     *
     * @param actualPerson the person which we want to see the sheet
     * @throws IOException the io exception
     */
    public void changeViewToOneContact(Person actualPerson) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/OneContactView.fxml"));


        Node load = fxmlLoader.load();


        OneContactController controller = fxmlLoader.getController();
        controller.setParentController(this);
        controller.setActualPerson(actualPerson);


        transition(load);
    }

    /**
     * Transition right to left when new part have to be show
     *
     * @param load => the new node ( can be list of all contact or one contact)
     */
    public void transition(Node load) {
        //We set the new node to the right out of the screen
        load.translateXProperty().set(containerAnchorPane.getWidth());

        //We add it to the main container
        containerAnchorPane.getChildren().add(load);

        Timeline timeline = new Timeline();
        //Translate the new node from right to left
        KeyValue kv1 = new KeyValue(load.translateXProperty(), 0, Interpolator.EASE_BOTH);



        if (lastNode != null) {
            //translete the current position to the left
            //it make the lastNode out of the screen
            KeyValue kv2 = new KeyValue(lastNode.translateXProperty(), -containerAnchorPane.getWidth() , Interpolator.EASE_BOTH);

            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.40),  kv2));

        }
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.40),  kv1));

        timeline.setOnFinished(t -> {
            if (lastNode != null) {
              containerAnchorPane.getChildren().remove(lastNode);
            }

            lastNode = load;
        });
        timeline.play();



    }


    /**
     * Change view to all contact.
     *
     * @throws IOException the io exception
     */
    public void changeViewToAllContact() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/AllContactView.fxml"));


        Node load = fxmlLoader.load();


        AllContactController controller = fxmlLoader.getController();
        controller.setParentController(this);
        controller.setAllContact(homeScreenModel.getAllContact());
        transition(load);
    }

    /**
     * Change view to all categories.
     *
     * @throws IOException the io exception
     */
    public void changeViewToAllCategories() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/AllCategoriesView.fxml"));


        Node load = fxmlLoader.load();


        AllCategoriesController controller = fxmlLoader.getController();
        controller.setParentController(this);
        controller.setAllCategories(homeScreenModel.getAllCategories());
        transition(load);
    }

    /**
     * Change view to add contact.
     *
     * @throws IOException the io exception
     */
    public void changeViewToAddContact() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/AddContactView.fxml"));


        Node load = fxmlLoader.load();


        AddContactViewController controller = fxmlLoader.getController();
        controller.setParentController(this);
        controller.fillComboBoxCategory();
        transition(load);

    }




    @FXML
    private void initialize() throws IOException {
        homeScreenModel = new HomeScreenModel();
        drawerController.setParentController(this);
        AnchorPane.setLeftAnchor(containerAnchorPane, (double) Constant.WIDTH_DRAWER_CLOSE);

        changeViewToAllContact();

    }


    /**
     * Gets home screen model.
     *
     * @return the home screen model
     */
    public HomeScreenModel getHomeScreenModel() {
        return homeScreenModel;
    }


}
