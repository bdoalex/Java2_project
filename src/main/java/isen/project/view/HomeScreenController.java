package isen.project.view;

import com.jfoenix.controls.JFXDrawer;
import isen.project.App;
import isen.project.model.AllContactModel;
import isen.project.util.Constant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


/**
 * @author Alexandre BARBOSA DE OLIVEIRA
 * Controller de la vue HomScreen
 */
public class HomeScreenController {

    public AnchorPane drawer;

    @FXML
    private DrawerController drawerController;



    @FXML
    private AnchorPane containerAnchorPane;


    /**
     * Display the view to add a contact
     *
     * @throws IOException
     */
    @FXML
    public void HandleAddContact() throws IOException {

        ChangeView("AddContactView");
    }


    public void ChangeView(String fxml) throws IOException {
        containerAnchorPane.getChildren().clear();
        containerAnchorPane.getChildren().add(App.loadFXML(fxml));
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
        drawerController.SetParentController(this);
        AnchorPane.setLeftAnchor(containerAnchorPane, (double) Constant.WIDTH_DRAWER_CLOSE);

        ChangeView("AllContactView");

    }
}
