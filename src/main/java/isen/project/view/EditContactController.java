package isen.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.ParentController;
import isen.project.model.EditContactModel;
import isen.project.model.daos.CategoryDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

public class EditContactController extends ParentController {

    @FXML
    public GridPane containerGridPane;

    @FXML
    public JFXTextField textFieldLastName;
    @FXML
    public JFXTextField textFieldFirstName;
    @FXML
    public JFXComboBox<String> comboBoxCategory;
    @FXML
    public JFXTextField textFieldLNickName;
    @FXML
    public JFXTextField textFieldAddress;
    @FXML
    public JFXTextField textFieldPhone;
    @FXML
    public JFXDatePicker datePickerBirth;
    @FXML
    public JFXTextField textFieldEmail;
    @FXML
    public ImageView imageViewProfilIcon;

    EditContactModel model = new EditContactModel();
    CategoryDao categoryDao = new CategoryDao();


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


    public void setOneContactController(OneContactController parentController) {
        model.setParentController(parentController);
    }


    @FXML
    public void handleButtonCancel() {
        App.closeDialog();
    }

    @FXML
    public void handleButtonAccept() throws IOException {
        Category category;
        if (comboBoxCategory.getValue() == null) {
            category = null;
        } else {
            category = categoryDao.getCategory(comboBoxCategory.getValue());
        }
        model.handleValidate(textFieldLastName.getText(),
                textFieldFirstName.getText(),
                textFieldLNickName.getText(),
                textFieldPhone.getText(),
                textFieldAddress.getText(),
                textFieldEmail.getText(),
                datePickerBirth.getValue(),
                category);
    }

    @FXML
    public void handleClickOnView() {
        Image newImage = model.clickOnImage();
        if (newImage != null) {
            imageViewProfilIcon.setImage(newImage);
        }

    }

    @FXML
    public void handleButtonDefault() {

        model.buttonDefault();
        File file = new File(Constant.URL_TO_IMAGE + Constant.DEFAULT_IMAGE);

        Image imageProfilIcon = new Image(file.toURI().toString());
        imageViewProfilIcon.setImage(imageProfilIcon);
    }



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

            comboBoxCategory.getItems().addAll(change.getList().stream().map(Category::getName).collect(Collectors.toList()));

        });

    }

    @FXML
    public void initialize() {
        comboBoxCategory.getItems().addAll(categoryDao.listCategories().stream().map(Category::getName).collect(Collectors.toList()));

    }


}
