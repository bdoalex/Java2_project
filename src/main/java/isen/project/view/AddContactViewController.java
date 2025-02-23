package isen.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.model.daos.CategoryDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import isen.project.util.ParentController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;

/**
 * The type Add contact view controller.
 */
public class AddContactViewController extends ParentController {

    /**
     * The Category dao.
     */
    CategoryDao categoryDao = new CategoryDao();
    /**
     * The Set default image.
     */
    Boolean setDefaultImage = false;


    /**
     * The File profil icon.
     */
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
    private JFXComboBox<Category> comboBoxCategory;


    /**
     * Save the person in the DB when you click on the add button
     * delete the form to add a person
     *
     * @throws IOException the io exception
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
                    category = categoryDao.getCategory(comboBoxCategory.getValue().getName());
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

                homeScreenParentController.getHomeScreenModel().addOneContact(newPerson);
                App.showSuccessSnackBar("Person added");
                App.showView("Homescreen");

            } catch (Exception e) {
                e.printStackTrace();
                //todo we have to delete the image if we can't add into the database

            }


        }
    }

    /**
     * Handle button cancel.
     */
    @FXML
    void handleButtonCancel() {
        App.showView("Homescreen");

    }

    /**
     * Handle button default.
     */
    @FXML
    void handleButtonDefault() {
        File file = new File(Constant.URL_TO_IMAGE + Constant.DEFAULT_IMAGE);
        Image imageProfilIcon = new Image(file.toURI().toString());
        imageViewProfilIcon.setImage(imageProfilIcon);
    }

    /**
     * Handle click on view.
     */
    @FXML
    void handleClickOnView() {
        Image newImage = clickOnImage();
        if(newImage != null){
            imageViewProfilIcon.setImage(newImage);
        }
    }

    /**
     * Click on image image.
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

        homeScreenParentController.getHomeScreenModel().getAllCategories().addListener((ListChangeListener<Category>) change -> {
            comboBoxCategory.getItems().clear();

            comboBoxCategory.getItems().addAll(homeScreenParentController.getHomeScreenModel().getAllCategories());

        });
        comboBoxCategory.getItems().addAll(homeScreenParentController.getHomeScreenModel().getAllCategories());


        comboBoxCategory.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category category) {

                if (category != null) {
                    return category.getName();
                }
                return "";

            }

            @Override
            public Category fromString(String s) {
                return null;
            }
        });
    }

    /**
     * Handle click on add category.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void handleClickOnAddCategory() throws IOException {
        FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/isen/project/view/AddCategoryPopUp.fxml"));
        VBox layout = mLLoader.load();

        AddCategoryPopUpController controller = mLLoader.getController();
        controller.setParentController(homeScreenParentController);


        App.showDialog(layout);
    }


    @FXML
    private void initialize() {
        File file = new File(Constant.URL_TO_IMAGE + Constant.DEFAULT_IMAGE);
        Image imageProfilIcon = new Image(file.toURI().toString());
        imageViewProfilIcon.setImage(imageProfilIcon);
    }
}
