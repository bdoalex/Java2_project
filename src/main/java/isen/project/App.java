package isen.project;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXSnackbar;
import isen.project.model.daos.DataSourceFactory;
import isen.project.util.Constant;
import isen.project.view.ToastFailureController;
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
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * JavaFX App
 */
public class App extends Application {


    private static final Deque<JFXDialog> allDialog = new LinkedList<>();


    private static AnchorPane mainLayout;

    private static AnchorPane containerContent;

    private static StackPane containerDialog;


    @Override
    public void init() throws Exception {
        super.init();
        Connection connection = DataSourceFactory.getConnection();
        File f = new File(getClass().getResource("/isen/project/sql/database-creation.sql").getFile());

        try (Statement stmt = connection.createStatement(); Scanner myReader = new Scanner(f)) {

            StringBuilder str = new StringBuilder();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                str.append(data);

                if (data.contains(";")) {
                    stmt.executeUpdate(String.valueOf(str));
                    str = new StringBuilder();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void start(Stage stage) throws IOException {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();


        stage.setTitle("Projet Java 2");

        mainLayout = loadFXML("MainLayout");

        containerContent = (AnchorPane) mainLayout.getChildren().get(0);

        containerDialog = (StackPane) mainLayout.getChildren().get(1);
        containerDialog.setVisible(false);

        Scene scene = new Scene(mainLayout, 850, 500);

        stage.setScene(scene);
        stage.show();
        stage.setMaxWidth(screenBounds.getMaxX());
        stage.setMaxHeight(screenBounds.getMaxY());
        stage.setMinHeight(Constant.MIN_HEIGHT);
        stage.setMinWidth(Constant.MIN_WIDTH);

        stage.widthProperty().addListener((obs, oldVal, newVal) -> mainLayout.prefWidth((Double) newVal)

        );


        containerContent.getChildren().add(loadFXML("LauncherScreen"));


    }


    /**
     * Load fxml t.
     *
     * @param <T>  the type parameter
     * @param fxml the fxml
     * @return the t
     * @throws IOException the io exception
     */
    public static <T> T loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    /**
     * Show view.
     *
     * @param rootElement the root element
     */
    public static void showView(String rootElement) {
        try {

            containerContent.getChildren().clear();
            containerContent.getChildren().add(loadFXML(rootElement));

        } catch (IOException e) {

            throw new IllegalArgumentException(e);
        }
    }


    /**
     * Show dialog.
     *
     * @param layout the layout
     */
    public static void showDialog(Region layout) {
        containerDialog.setVisible(true);

        JFXDialog temp = new JFXDialog(containerDialog, layout, JFXDialog.DialogTransition.TOP);

        allDialog.add(temp);

        temp.setOnDialogClosed(closeEvent -> {

                    allDialog.removeLast();
                    if (allDialog.isEmpty()) {
                        containerDialog.setVisible(false);
                    }

                }

        );

        temp.show();
    }

    /**
     * Close dialog.
     */
    public static void closeDialog() {
        JFXDialog temp = allDialog.getLast();

        temp.close();
    }


    /**
     * Show success snack bar.
     *
     * @param success the success
     * @throws IOException the io exception
     */
    public static void showSuccessSnackBar(String success) throws IOException {
        JFXSnackbar bar = new JFXSnackbar(mainLayout);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/ToastSuccess.fxml"));
        Node load = fxmlLoader.load();
        ToastSuccessController controller = fxmlLoader.getController();
        controller.setText(success);

        JFXSnackbar.SnackbarEvent event = new JFXSnackbar.SnackbarEvent(load);

        bar.enqueue(event);
    }

    /**
     * Show failure snack bar.
     *
     * @param success the success
     * @throws IOException the io exception
     */
    public static void showFailureSnackBar(String success) throws IOException {
        JFXSnackbar bar = new JFXSnackbar(mainLayout);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/project/view/ToastFailure.fxml"));
        Node load = fxmlLoader.load();
        ToastFailureController controller = fxmlLoader.getController();
        controller.setText(success);

        JFXSnackbar.SnackbarEvent event = new JFXSnackbar.SnackbarEvent(load);

        bar.enqueue(event);
    }



    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Save profil icon string.
     *
     * @param fileProfilIcon the file profil icon
     * @return the string
     * @throws IOException the io exception
     */
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

    /**
     * Delete profil icon boolean.
     *
     * @param nameFile the name file
     * @return the boolean
     */
    public static Boolean deleteProfilIcon(String nameFile) {
        //We load our file into Image
        File file = new File(Constant.URL_TO_IMAGE + nameFile);

        return file.delete();
    }

}