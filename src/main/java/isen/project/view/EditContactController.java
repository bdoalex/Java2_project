package isen.project.view;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import isen.project.App;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.io.FilenameUtils;
public class EditContactController {


    public JFXTextField textFieldLastName;
    public JFXTextField textFieldFirstName;
    public JFXTextField textFieldCategory;
    public JFXTextField textFieldLNickName;
    public JFXTextField textFieldAddress;
    public JFXTextField textFieldPhone;
    public DatePicker datePickerBirth;
    public JFXTextField textFieldEmail;
    public ImageView imageViewProfilIcon;

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
