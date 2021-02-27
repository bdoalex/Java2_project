package isen.project.view;

import isen.project.App;
import isen.project.model.AddContactModel;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class AddContactViewController {

    PersonDao personDao = new PersonDao();

    AddContactModel model = new AddContactModel();

    File fileProfilIcon;

    @FXML
    private ImageView iconProfilImageView;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField nickNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button validateButton;

    /**
     * Save the person in the DB when you click on the add button
     * delete the form to add a person
     */
    @FXML
    public void handleValidateButton() {
        if (lastNameTextField.getText().isEmpty() || firstNameTextField.getText().isEmpty() || nickNameTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Champs manquant");//toDo : gérer en fonction de l'erreur renvoyé
            alert.showAndWait();

        } else {
            try {
                //We check if we can create the photo and if we can add it to the databse
                //if not we throw an exception

                String nameOfSaveFile = Constant.DEFAULT_IMAGE;
                if(fileProfilIcon == null){
                    nameOfSaveFile = model.SaveFile(fileProfilIcon);
                }


                Person newPerson = new Person(lastNameTextField.getText(), firstNameTextField.getText(), nickNameTextField.getText(), phoneTextField.getText(), addressTextField.getText(), emailTextField.getText(), birthDatePicker.getValue(), nameOfSaveFile);
                personDao.addPerson(newPerson);
                App.showView("HomeScreen");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Erreur");//toDo : gérer en fonction de l'erreur renvoyé
                //todo we have to delete the image if we can't add into the database
                alert.showAndWait();
            }


        }
    }

    /**
     * Display the view to add a contact
     *
     * @throws IOException
     */
    @FXML
    public void handleBackButton() throws IOException {
        App.showView("HomeScreen");
    }

    /**
     * OnClick on button change icon
     */
    @FXML
    public void handleChangeIconProfil() {
        //We create the file Chooser
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");


        fc.getExtensionFilters().add(imageFilter);
        //We open the file Chooser
        fileProfilIcon = fc.showOpenDialog(null);
        //We get the image from the file chooser
        Image imageProfilIcon = new Image(fileProfilIcon.toURI().toString());
        //We display the image
        iconProfilImageView.setImage(imageProfilIcon);

    }
}
