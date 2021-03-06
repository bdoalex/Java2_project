package isen.project.view;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.apache.commons.io.FilenameUtils;
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

    public JFXDatePicker datePickerBirth;
    @FXML
    public JFXTextField textFieldEmail;
    @FXML
    public ImageView imageViewProfilIcon;

    int posInGlobalList;
    Person actualPerson;
    OneContactController parentController;


    public void setActualPerson(Person actualPerson , int posInGlobalList) {
        this.posInGlobalList=posInGlobalList;
        textFieldLastName.setText(actualPerson.getLastName());
        textFieldFirstName.setText(actualPerson.getFirstName());
        //textFieldCategory.setText(actualPerson.getCategory().get);

        JFXDatePicker datePickerBirth = new JFXDatePicker();


        containerGridPane.add(datePickerBirth,1,7);

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

    public void handleButtonAccept(ActionEvent actionEvent) {


        Person newPerson = new Person( actualPerson.getPersonId(),textFieldLastName.getText(),textFieldFirstName.getText(),textFieldLNickName.getText(),textFieldPhone.getText(),textFieldAddress.getText(),textFieldEmail.getText(),datePickerBirth.getValue(),FilenameUtils.getName(imageViewProfilIcon.getImage().getUrl()),actualPerson.getCategory());
        parentController.setActualPerson(newPerson);
        parentController.getHomeScreenParentController().getHomeScreenModel().modifyOneContact(posInGlobalList,newPerson);
        PersonDao dao = new PersonDao();
        dao.modifyPerson(newPerson);
        App.closeDialog();
    }
}
