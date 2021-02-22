package isen.project.view;

import isen.project.App;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddContactViewController {

    PersonDao personDao = new PersonDao();


    @FXML
    private AnchorPane formPane;

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

    /**
     * Save the person in the DB when you click on the add button
     * delete the form to add a person
     */
    @FXML
    public void handleValidateButton() {
        Person newPerson = new Person(lastNameTextField.getText(), firstNameTextField.getText(), nickNameTextField.getText(),phoneTextField.getText(), addressTextField.getText(),emailTextField.getText(), birthDatePicker.getValue());
        if(lastNameTextField.getText().isEmpty() || firstNameTextField.getText().isEmpty() || nickNameTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Champs manquant");//toDo : gérer en fonction de l'erreur renvoyé
            alert.showAndWait();

        }
        else{
            personDao.addPerson(newPerson);
            App.showView("HomeScreen");

        }
    }

    /**
     * Display the view to add a contact
     * @throws IOException
     */
    @FXML
    public void handleBackButton() throws IOException {
        App.showView("HomeScreen");
    }
}
