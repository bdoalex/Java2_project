package isen.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.util.ParentController;
import isen.project.model.EditContactModel;
import isen.project.model.daos.CategoryDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * The type Edit contact controller.
 */
public class EditContactController extends ParentController {

    /**
     * The Container grid pane.
     */
    @FXML
    public GridPane containerGridPane;

    /**
     * The Text field last name.
     */
    @FXML
    public JFXTextField textFieldLastName;
    /**
     * The Text field first name.
     */
    @FXML
    public JFXTextField textFieldFirstName;
    /**
     * The Combo box category.
     */
    @FXML
    public JFXComboBox<Category> comboBoxCategory;
    /**
     * The Text field l nick name.
     */
    @FXML
    public JFXTextField textFieldLNickName;
    /**
     * The Text field address.
     */
    @FXML
    public JFXTextField textFieldAddress;
    /**
     * The Text field phone.
     */
    @FXML
    public JFXTextField textFieldPhone;
    /**
     * The Date picker birth.
     */
    @FXML
    public JFXDatePicker datePickerBirth;
    /**
     * The Text field email.
     */
    @FXML
    public JFXTextField textFieldEmail;
    /**
     * The Image view profil icon.
     */
    @FXML
    public ImageView imageViewProfilIcon;

    /**
     * The Model.
     */
    EditContactModel model = new EditContactModel();
    /**
     * The Category dao.
     */
    CategoryDao categoryDao = new CategoryDao();


    /**
     * Sets actual person.
     *
     * @param actualPerson    the actual person
     * @param posInGlobalList the pos in global list
     */
    public void setActualPerson(Person actualPerson, int posInGlobalList) {
        model.setPosInGlobalList(posInGlobalList);
        model.setActualPerson(actualPerson);
        textFieldLastName.setText(actualPerson.getLastName());
        textFieldFirstName.setText(actualPerson.getFirstName());


        if (actualPerson.getBirthDate() != null) {
            datePickerBirth.setValue(actualPerson.getBirthDate());
        }
        textFieldEmail.setText(actualPerson.getEmailAddress());
        textFieldLNickName.setText(actualPerson.getNickName());
        textFieldAddress.setText(actualPerson.getAddress());
        textFieldPhone.setText(actualPerson.getPhoneNumber());
        Image image = new Image("file:\\" + Constant.URL_TO_IMAGE + actualPerson.getNameFileIcon());
        imageViewProfilIcon.setImage(image);
        if (actualPerson.getCategory() != null) {
            comboBoxCategory.setPromptText(actualPerson.getCategory().getName());
        }


    }


    /**
     * Sets one contact controller.
     *
     * @param parentController the parent controller
     */
    public void setOneContactController(OneContactController parentController) {
        model.setParentController(parentController);
    }


    /**
     * Handle button cancel.
     */
    @FXML
    public void handleButtonCancel() {
        App.closeDialog();
    }

    /**
     * Handle button accept.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void handleButtonAccept() throws IOException {
        Category category = comboBoxCategory.getValue();
        model.handleValidate(textFieldLastName.getText(),
                textFieldFirstName.getText(),
                textFieldLNickName.getText(),
                textFieldPhone.getText(),
                textFieldAddress.getText(),
                textFieldEmail.getText(),
                datePickerBirth.getValue(),
                category);
    }

    /**
     * set new profil icon.
     */
    @FXML
    public void handleClickOnView() {
        Image newImage = model.clickOnImage();
        if (newImage != null) {
            imageViewProfilIcon.setImage(newImage);
        }

    }

    /**
     * Handle button default.
     */
    @FXML
    public void handleButtonDefault() {

        model.buttonDefault();
        File file = new File(Constant.URL_TO_IMAGE + Constant.DEFAULT_IMAGE);

        Image imageProfilIcon = new Image(file.toURI().toString());
        imageViewProfilIcon.setImage(imageProfilIcon);
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

    /**
     * Fill combo box category.
     */
    public void fillComboBoxCategory() {

        homeScreenParentController.getHomeScreenModel().getAllCategories().addListener((ListChangeListener<Category>) change -> {
            comboBoxCategory.getItems().clear();

            comboBoxCategory.getItems().addAll(homeScreenParentController.getHomeScreenModel().getAllCategories());

        });
        comboBoxCategory.getItems().addAll(homeScreenParentController.getHomeScreenModel().getAllCategories());

        comboBoxCategory.getSelectionModel().select(model.getActualPerson().getCategory());

        comboBoxCategory.setConverter(new StringConverter<Category>() {
            @Override
            public String toString(Category category) {

                if(category != null){
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
     * Initialize.
     */
    @FXML
    public void initialize() {


    }


}
