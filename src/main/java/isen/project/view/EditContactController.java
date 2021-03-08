package isen.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.model.EditContactModel;
import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

public class EditContactController {

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
        //textFieldCategory.setText(actualPerson.getCategory().get);


        if (actualPerson.getBirthDate() != null) {
            datePickerBirth.setValue(actualPerson.getBirthDate());
        }
        textFieldEmail.setText(actualPerson.getEmailAddress());
        textFieldLNickName.setText(actualPerson.getNickName());
        textFieldAddress.setText(actualPerson.getAddress());
        textFieldPhone.setText(actualPerson.getPhoneNumber());
        Image image = new Image("file:\\" + Constant.URL_TO_IMAGE + actualPerson.getNameFileIcon());
        imageViewProfilIcon.setImage(image);
        comboBoxCategory.setPromptText(actualPerson.getCategory().getName());


    }



    public void setParentController(OneContactController parentController) {
        model.setParentController(parentController);
    }


    public void handleButtonCancel() {
        App.closeDialog();
    }

    public void handleButtonAccept() throws IOException {
        model.handleValidate(textFieldLastName.getText(),
                textFieldFirstName.getText(),
                textFieldLNickName.getText(),
                textFieldPhone.getText(),
                textFieldAddress.getText(),
                textFieldEmail.getText(),
                datePickerBirth.getValue(),
                comboBoxCategory.getValue());
    }

    public void handleClickOnView() {
        Image newImage = model.clickOnImage();
        if (newImage != null) {
            imageViewProfilIcon.setImage(newImage);
        }

    }

    public void handleButtonDefault() {

        model.buttonDefault();
        File file = new File(Constant.URL_TO_IMAGE + Constant.DEFAULT_IMAGE);

        Image imageProfilIcon = new Image(file.toURI().toString());
        imageViewProfilIcon.setImage(imageProfilIcon);
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
    public void initialize() {
        fillComboBoxCategory();
    }



}
