package isen.project.model;

import isen.project.model.entities.Person;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class AllContactModel {



    private ObservableList<Person> allContact = FXCollections.observableArrayList();
    private ObservableList<Person> contactShown;


    public AllContactModel(ObservableList<Person> allPerson) {
        allContact = allPerson;
        contactShown = allPerson;
    }

    public void filter(String newValue) {


        FilteredList<Person> filteredData = new FilteredList<>(allContact, p -> true);
        filteredData.setPredicate(person -> {

            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }


            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();



            if (person.getFirstName().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getFirstName().toLowerCase())) {
                return true; // Filter matches first name.
            } else if (person.getLastName().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getLastName().toLowerCase())) {
                return true; // Filter matches last name.
            } else if (person.getPhoneNumber().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getPhoneNumber().toLowerCase())) {
                return true;
            } else if (person.getCategory().getName().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getCategory().getName().toLowerCase())) {
                return true;
            } else if (person.getAddress().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getAddress().toLowerCase())) {
                return true;
            }
            return false; // Does not match.
        });
        contactShown = filteredData;

    }


    public ObservableList<Person> getContactShown() {
        return contactShown;
    }

    public void modifyOneContact(int index, Person newPerson) {
        allContact.set(index,newPerson);
        contactShown=allContact;
    }
}
