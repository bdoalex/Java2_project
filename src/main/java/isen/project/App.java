package isen.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static BorderPane mainLayout;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Projet Java 2");

        mainLayout = loadFXML("MainLayout");
        scene = new Scene(mainLayout, 850, 500);

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        App.showView("LauncherScreen");

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static <T> T loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void showView(String rootElement) {
        try {
            // We can only set the center of a borderPane, not a Parent, so we rely on
            // either an explicit cast or our better generics implementation to convert our
            // scene and modify it.
            mainLayout.setCenter(loadFXML(rootElement));
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

    public static void main(String[] args) {
        launch();
    }

}