package isen.project;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXSnackbar;
import isen.project.model.daos.DataSourceFactory;
import isen.project.util.Constant;
import isen.project.view.ToastSuccessController;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static JFXDialog dialog;


    private static AnchorPane mainLayout;

    private static AnchorPane containerContent;

    private static StackPane containerDialog;


    @Override
    public void init() throws Exception {
        super.init();
        Connection connection = DataSourceFactory.getConnection();
        Statement stmt = connection.createStatement();



        File f = new File(getClass().getResource("/isen/project/sql/database-creation.sql").getFile()) ;

        System.out.println(f);

        Scanner myReader = new Scanner(f);
        StringBuilder str = new StringBuilder();

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            str.append(data);

            if(data.contains(";")){
                stmt.executeUpdate(String.valueOf(str));
                str = new StringBuilder();
            }

        }


    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Connection connection = DataSourceFactory.getConnection();
        Statement stmt = connection.createStatement();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();


        stage.setTitle("Projet Java 2");

        mainLayout = loadFXML("MainLayout");

        containerContent = (AnchorPane) mainLayout.getChildren().get(0);

        containerDialog = (StackPane) mainLayout.getChildren().get(1);
        containerDialog.setVisible(false);

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


        containerContent.getChildren().add(loadFXML("LauncherScreen"));


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
            containerContent.getChildren().clear();
            containerContent.getChildren().add(loadFXML(rootElement));

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


    public static void showDialog(Region layout) {
        containerDialog.setVisible(true);

        dialog = new JFXDialog(containerDialog, layout, JFXDialog.DialogTransition.TOP);
        dialog.setOnDialogClosed(closeEvent -> {
            containerDialog.setVisible(false);
        });

        dialog.show();
    }

    public static void closeDialog() {
        dialog.close();
    }


    public static void showSuccessSnackBar(String success) throws IOException {
        JFXSnackbar bar = new JFXSnackbar(mainLayout);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/ToastSuccess.fxml"));
        Node load =  fxmlLoader.load();
        ToastSuccessController controller = fxmlLoader.getController();
        controller.setText(success);

        JFXSnackbar.SnackbarEvent event = new JFXSnackbar.SnackbarEvent(load);

        bar.enqueue(event);
    }

    public static void showFailureSnackBar(String success) throws IOException {
        JFXSnackbar bar = new JFXSnackbar(mainLayout);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/ToastFailure.fxml"));
        Node load =  fxmlLoader.load();
        ToastSuccessController controller = fxmlLoader.getController();
        controller.setText(success);

        JFXSnackbar.SnackbarEvent event = new JFXSnackbar.SnackbarEvent(load);

        bar.enqueue(event);
    }

    public static AnchorPane getMainLayout() {
        return mainLayout;
    }


    public static void main(String[] args) {
        launch();
    }

    public static String saveProfilIcon(File fileProfilIcon) throws IOException {
        //We load our file into Image
        Image imageProfilIcon = new Image(fileProfilIcon.toURI().toString());
        String fileName = fileProfilIcon.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileProfilIcon.getName().length());

        //We transform our image to saveable file
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageProfilIcon, null);
        String nameOfSaveFile = java.util.UUID.randomUUID().toString() + "." + fileExtension;
        //We generate random id
        File fileToSave = new File(Constant.URL_TO_IMAGE + nameOfSaveFile);
        //We save our file into fileIcon
        ImageIO.write(bufferedImage, "png", fileToSave);

        return nameOfSaveFile;

    }

    public static Boolean deleteProfilIcon(String nameFile) throws IOException {
        //We load our file into Image
        File file = new File(Constant.URL_TO_IMAGE + nameFile);

        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }

}