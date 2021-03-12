package isen.project.model;

import isen.project.model.entities.Person;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class AllContactModel {


    private ObservableList<Person> allContact;
    private ObservableList<Person> contactShown;


    public AllContactModel(ObservableList<Person> allPerson) {
        allContact = allPerson;
        contactShown = allPerson;
    }

    /**
     * @param newValue => String which come from the textField filter
     */
    public void filter(String newValue) {

        //We create the list with all the data
        FilteredList<Person> filteredData = new FilteredList<>(allContact, p -> true);

        //For each person we check if it correspond to the filter
        filteredData.setPredicate(person -> {

            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }


            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();


            if (person.getFirstName().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getFirstName().toLowerCase())
                    || person.getLastName().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getLastName().toLowerCase())
                    || person.getPhoneNumber().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getPhoneNumber().toLowerCase())
                    || person.getAddress().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getAddress().toLowerCase())

            ) {
                return true; // Filter matches first name.
            }
            if (person.getCategory() != null && (person.getCategory().getName().toLowerCase().contains(lowerCaseFilter) || lowerCaseFilter.contains(person.getCategory().getName().toLowerCase()))) {
                return true;

            }

            return false; // Does not match.
        });
        contactShown = filteredData;

    }


    public ObservableList<Person> getContactShown() {
        return contactShown;
    }


    public void setAllContact(ObservableList<Person> allContact) {
        this.allContact = allContact;
    }
}
