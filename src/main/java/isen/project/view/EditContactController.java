package isen.project.view;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class EditContactController {

    @FXML
    public GridPane containerGridPane;

    @FXML
    public JFXTextField textFieldLastName;
    @FXML
    public JFXTextField textFieldFirstName;
    @FXML
    public JFXTextField textFieldCategory;
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

    File fileProfilIcon;

    int posInGlobalList;
    Person actualPerson;
    OneContactController parentController;


    public void setActualPerson(Person actualPerson , int posInGlobalList) {
        this.posInGlobalList=posInGlobalList;
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

        this.actualPerson = actualPerson;
    }

    @FXML
    public void initialize(){

    }

    public void setParentController(OneContactController parentController) {
        this.parentController = parentController;
    }


    public void handleButtonCancel(ActionEvent actionEvent) {
        App.closeDialog();
    }

    public void handleButtonAccept(ActionEvent actionEvent) throws IOException {
        String nameOfSaveFile = Constant.DEFAULT_IMAGE;
        System.out.println(actualPerson.getNameFileIcon().equals(Constant.DEFAULT_IMAGE));
        System.out.println(actualPerson.getNameFileIcon());
        if (fileProfilIcon != null) {
            if(!actualPerson.getNameFileIcon().equals(Constant.DEFAULT_IMAGE)){
                if(App.deleteProfilIcon(actualPerson.getNameFileIcon())){

                    nameOfSaveFile = App.saveProfilIcon(fileProfilIcon);
                }
                else{
                    //todo print red toast
                    return;
                }
            }
            else{
                nameOfSaveFile = App.saveProfilIcon(fileProfilIcon);
            }


        }
        Person newPerson = new Person( actualPerson.getPersonId(),textFieldLastName.getText(),textFieldFirstName.getText(),textFieldLNickName.getText(),textFieldPhone.getText(),textFieldAddress.getText(),textFieldEmail.getText(),datePickerBirth.getValue(),nameOfSaveFile,actualPerson.getCategory());
        parentController.setActualPerson(newPerson);
        parentController.getHomeScreenParentController().getHomeScreenModel().modifyOneContact(posInGlobalList,newPerson);
        PersonDao dao = new PersonDao();
        dao.modifyPerson(newPerson);
        App.closeDialog();
    }

    public void handleClickOnView(MouseEvent mouseEvent) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");


        fc.getExtensionFilters().add(imageFilter);
        //We open the file Chooser
        fileProfilIcon = fc.showOpenDialog(null);
        if (fileProfilIcon != null) {
            Image imageProfilIcon = new Image(fileProfilIcon.toURI().toString());
            imageViewProfilIcon.setImage(imageProfilIcon);
        }
    }
}
