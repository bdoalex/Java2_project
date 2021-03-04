package isen.project.model;

import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HomeScreenModel {


    //We store all contact here to avoid multiple call  to the db
    private ObservableList<Person> allContact = FXCollections.observableArrayList();

    PersonDao personDao = new PersonDao();


    public HomeScreenModel() {
        populateList();
    }

    public void populateList() {
        if (personDao.getPersons() != null) {
            allContact = personDao.getPersons();
        }
    }

    public ObservableList<Person> getAllContact() {
        return allContact;
    }
}
