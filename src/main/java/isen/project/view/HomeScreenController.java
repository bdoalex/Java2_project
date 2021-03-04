package isen.project.view;

import isen.project.App;
import isen.project.ParentController;
import isen.project.model.HomeScreenModel;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/**
 * @author Alexandre BARBOSA DE OLIVEIRA
 * Controller de la vue HomScreen
 */
public class HomeScreenController {

    public AnchorPane drawer;

    @FXML
    private DrawerController drawerController;

    private HomeScreenModel homeScreenModel;

    @FXML
    private AnchorPane containerAnchorPane;


    /**
     * Display the view to add a contact
     *
     * @throws IOException
     */
    @FXML
    public void HandleAddContact() throws IOException {

        changeView("AddContactView");
    }

    /**
     * @param fxml         the name of the file of the view
     * @param actualPerson the person which we want to see the sheet
     * @throws IOException
     */
    public void changeViewToOneContact(String fxml, Person actualPerson) throws IOException {
        containerAnchorPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/" + fxml + ".fxml"));


        Node load = fxmlLoader.load();


        OneContactController controller = fxmlLoader.getController();
        controller.setParentController(this);
        controller.setActualPerson(actualPerson);


        containerAnchorPane.getChildren().add(load);
    }


    public void changeViewToAllContact() throws IOException {
        containerAnchorPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/AllContactView.fxml"));


        Node load = fxmlLoader.load();


        AllContactController controller = fxmlLoader.getController();
        controller.setParentController(this);
        controller.setAllContact(homeScreenModel.getAllContact());

        containerAnchorPane.getChildren().add(load);
    }


    public void changeView(String fxml) throws IOException {
        containerAnchorPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/" + fxml + ".fxml"));


        Node load = fxmlLoader.load();


        containerAnchorPane.getChildren().add(load);
    }


    /**
     * Display the view of Launcher Screen
     *
     * @throws IOException
     */
    @FXML
    public void handleBackButton() throws IOException {
        App.showView("LauncherScreen");
    }

    @FXML
    private void initialize() throws IOException {
        homeScreenModel = new HomeScreenModel();
        drawerController.setParentController(this);
        AnchorPane.setLeftAnchor(containerAnchorPane, (double) Constant.WIDTH_DRAWER_CLOSE);

        changeViewToAllContact();

    }
}
