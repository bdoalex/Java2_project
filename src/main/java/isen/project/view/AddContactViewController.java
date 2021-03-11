package isen.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.ParentController;
import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class AddContactViewController extends ParentController {

    PersonDao personDao = new PersonDao();
    CategoryDao categoryDao = new CategoryDao();
    Boolean setDefaultImage = false;


    File fileProfilIcon;

    @FXML
    private ImageView imageViewProfilIcon;

    @FXML
    private JFXTextField textFieldLastName;

    @FXML
    private JFXTextField textFieldFirstName;

    @FXML
    private JFXTextField textFieldLNickName;

    @FXML
    private JFXTextField textFieldAddress;

    @FXML
    private JFXTextField textFieldPhone;

    @FXML
    private JFXTextField textFieldEmail;

    @FXML
    private JFXDatePicker datePickerBirth;

    @FXML
    private JFXComboBox<String> comboBoxCategory;


    /**
     * Save the person in the DB when you click on the add button
     * delete the form to add a person
     */
    @FXML
    void handleButtonAccept() throws IOException {
        Category category;
        String nameOfSaveFile = Constant.DEFAULT_IMAGE;


        if (textFieldLastName.getText().isEmpty() || textFieldFirstName.getText().isEmpty() || textFieldLNickName.getText().isEmpty()) {
           App.showFailureSnackBar("You need to fill all form");

        } else {
            try {
                //We check if we can create the photo and if we can add it to the databse
                //if not we throw an exception




                if (fileProfilIcon != null) {
                    nameOfSaveFile = App.saveProfilIcon(fileProfilIcon);
                }

                if (comboBoxCategory.getValue() == null) {
                    category = null;
                } else {
                    category = categoryDao.getCategory(comboBoxCategory.getValue());
                }


                Person newPerson = new Person(
                        textFieldLastName.getText(),
                        textFieldFirstName.getText(),
                        textFieldLNickName.getText(),
                        textFieldPhone.getText(),
                        textFieldAddress.getText(),
                        textFieldEmail.getText(),
                        datePickerBirth.getValue(),
                        nameOfSaveFile, category );
                personDao.addPerson(newPerson);
                homeScreenParentController.getHomeScreenModel().addOneContact(newPerson);
                App.showSuccessSnackBar("Person added");
                App.showView("Homescreen");

            } catch (Exception e) {
                e.printStackTrace();
                //todo we have to delete the image if we can't add into the database

            }


        }
    }

    @FXML
    void handleButtonCancel() {
        App.showView("Homescreen");

    }

    @FXML
    void handleButtonDefault() {
        File file = new File(Constant.URL_TO_IMAGE + Constant.DEFAULT_IMAGE);
        Image imageProfilIcon = new Image(file.toURI().toString());
        imageViewProfilIcon.setImage(imageProfilIcon);
    }

    @FXML
    void handleClickOnView() {
        Image newImage = clickOnImage();
        if(newImage != null){
            imageViewProfilIcon.setImage(newImage);
        }
    }

    /**
     *
     * @return the new image choose by the user
     */
    public Image clickOnImage(){

        FileChooser fc = new FileChooser();
        //set extension
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

        fc.getExtensionFilters().add(imageFilter);
        //We open the file Chooser
        fileProfilIcon = fc.showOpenDialog(null);
        if (fileProfilIcon != null) {
            setDefaultImage = false;
            return new Image(fileProfilIcon.toURI().toString());
        }
        return null;
    }


    /**
     * Fill combo box category.
     */
    public void fillComboBoxCategory() {
        ObservableList<Category> categories;
        categories = categoryDao.listCategories();

        for (Category category : categories) {
            comboBoxCategory.getItems().add(category.getName());
        }
    }

    @FXML
    private void initialize() {
        fillComboBoxCategory();
        File file = new File(Constant.URL_TO_IMAGE + Constant.DEFAULT_IMAGE);
        Image imageProfilIcon = new Image(file.toURI().toString());
        imageViewProfilIcon.setImage(imageProfilIcon);

    }
}
