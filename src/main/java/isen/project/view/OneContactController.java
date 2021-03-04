package isen.project.view;

import com.jfoenix.controls.JFXButton;
import isen.project.ParentController;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;


public class OneContactController extends ParentController {


    Person actualPerson;

    @FXML
    Circle iconProfilCircle;

    @FXML
    JFXButton buttonBack;



    @FXML
    Text phoneNumber;

    @FXML
    Text name;


    @FXML
    Text nickName;

    @FXML
    Text adress;

    @FXML
    Text email;

    @FXML
    Text birthDate;

    @FXML
    Text category;

    public void setActualPerson(Person actualPerson) {
        this.actualPerson = actualPerson;




        phoneNumber.setText(actualPerson.getPhoneNumber());
        name.setText(actualPerson.getFirstName() + " " +actualPerson.getLastName());

        nickName.setText(actualPerson.getNickName());
        adress.setText(actualPerson.getAddress());
        email.setText(actualPerson.getEmailAddress());
        birthDate.setText(actualPerson.getBirthDate().toString());

        FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/isen/project/view/ContactListCell.fxml"));
        mLLoader.setController(this);
        try {
            mLLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = new Image("file:\\" + Constant.URL_TO_IMAGE + actualPerson.getNameFileIcon());
        iconProfilCircle.setFill(new ImagePattern(image));



    }

    public void handleButtonBack(ActionEvent event) {
        try {
            homeScreenParentController.changeViewToAllContact();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
