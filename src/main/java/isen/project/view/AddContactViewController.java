package isen.project.view;

import isen.project.App;
import isen.project.model.AddContactModel;
import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class AddContactViewController {

    PersonDao personDao = new PersonDao();
    CategoryDao categoryDao = new CategoryDao();

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

    @FXML
    private ComboBox<String> comboBoxCategory;


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
                Category category = new Category();
                if (fileProfilIcon != null) {
                    nameOfSaveFile = model.SaveFile(fileProfilIcon);
                }
                if (comboBoxCategory.getValue()!=null) {
                    category = categoryDao.getCategory(comboBoxCategory.getValue());

                }





                Person newPerson = new Person(lastNameTextField.getText(), firstNameTextField.getText(), nickNameTextField.getText(), phoneTextField.getText(), addressTextField.getText(), emailTextField.getText(), birthDatePicker.getValue(), nameOfSaveFile, category );
                personDao.addPerson(newPerson);
                App.showView("HomeScreen");
            } catch (Exception e) {
                e.printStackTrace();
                //todo we have to delete the image if we can't add into the database

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
        if (fileProfilIcon != null) {
            Image imageProfilIcon = new Image(fileProfilIcon.toURI().toString());
            iconProfilImageView.setImage(imageProfilIcon);
        }
        //We get the image from the file chooser

        //We display the image

    }


    /**
     * Fill combo box category.
     */
    public void fillComboBoxCategory() {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories = categoryDao.listCategories();

        for (Category category : categories) {
            comboBoxCategory.getItems().add(category.getName());
        }
    }

    @FXML
    private void initialize() throws IOException {
        fillComboBoxCategory();

    }
}
