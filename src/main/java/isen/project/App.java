package isen.project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import com.jfoenix.controls.JFXSnackbar;
import isen.project.model.daos.DataSourceFactory;
import isen.project.util.Constant;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static JFXDialog dialog;


    private static AnchorPane mainLayout;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Connection connection = DataSourceFactory.getConnection();
        Statement stmt = connection.createStatement();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();


        stage.setTitle("Projet Java 2");

        mainLayout = loadFXML("MainLayout");





        scene = new Scene(mainLayout, 850, 500);

        stage.setScene(scene);
        stage.show();
        stage.setMaxWidth(screenBounds.getMaxX());
        stage.setMaxHeight(screenBounds.getMaxY());
        stage.setMinHeight(Constant.MIN_HEIGHT);
        stage.setMinWidth(Constant.MIN_WIDTH);

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            mainLayout.prefWidth((Double) newVal);
        });


        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            // Do whatever you want
        });


        mainLayout.getChildren().add(loadFXML("LauncherScreen"));


    }


    public static <T> T loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void showView(String rootElement) {
        try {
            // We can only set the center of a borderPane, not a Parent, so we rely on
            // either an explicit cast or our better generics implementation to convert our
            // scene and modify it.
            mainLayout.getChildren().clear();
            mainLayout.getChildren().add(loadFXML(rootElement));
        } catch (IOException e) {
            // Chances are that the file is not found. Nothing we can do, really, but as
            // IOException is checked, it would require us to add nasty support all over our
            // code
            // Instead, a better practice is to convert this checked exception into an
            // unchecked exception, and let it bubble up to the main thread, killing the
            // JVM. We could also close the app, but it would be dangerous, as some
            // resources could be opened somewhere and not correctly closed...
            // BEWARE ! you should ALWAYS keep the stacktrace complete. using the original
            // exception as an argument allows that !
            throw new IllegalArgumentException(e);
        }
    }


    public static void showDialog(JFXDialogLayout content,List<Button> allActions){
        StackPane stackPane = new StackPane();
        AnchorPane.setLeftAnchor(stackPane,0.0);
        AnchorPane.setRightAnchor(stackPane,0.0);
        AnchorPane.setTopAnchor(stackPane,0.0);
        AnchorPane.setBottomAnchor(stackPane,0.0);

        App.getMainLayout().getChildren().add(stackPane);
        dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        content.setActions(allActions);

        dialog.show();
    }

    public static void closeDialog(){
        dialog.close();
        mainLayout.getChildren().remove(dialog.getDialogContainer());
    }

    public static void showSuccessSnackBar(String success) throws IOException {
        JFXSnackbar bar = new JFXSnackbar(mainLayout);
        String css = App.class.getResource("/isen/project/css/SnackBarSuccess.css").toExternalForm();
    //    bar.getStylesheets().add(css);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setBody(new Text("Are you sure you want to delete  from your contacts ? "));



       JFXSnackbar.SnackbarEvent event = new JFXSnackbar.SnackbarEvent("lol");

        bar.enqueue(event);
    }

    public static AnchorPane getMainLayout() {
        return mainLayout;
    }

    public static void main(String[] args) {
        launch();
    }

}