package isen.project.view;

import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddContactViewController{

    PersonDao personDao = new PersonDao();

    @FXML
    private AnchorPane formPane;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private DatePicker birthDateTextField;

    @FXML
    private TextField PhoneTextField;

    @FXML
    private Button validateButton;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    public void handleValidateButton(){
        personDao.addPerson(new Person(birthDatePicker.getValue()));
        formPane.setVisible(false);
    };
}
