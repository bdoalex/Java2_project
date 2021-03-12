package isen.project.model;

import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HomeScreenModel {


    //We store all contact here to avoid multiple call  to the db
    private ObservableList<Person> allContact = FXCollections.observableArrayList();


    private ObservableList<Category> allCategories = FXCollections.observableArrayList();


    public void addOneContact(Person newPerson) {
        allContact.add(newPerson);
    }

    public void addOneCateogry(Category newCategory) {
        allCategories.add(newCategory);
    }

    public void deleteOnePerson(Person person){
        allContact.remove(person);
    }

    public void deleteOneCategory(Category category){
        allCategories.remove(category);
    }

    public void modifyOneContact(int index, Person newPerson) {
        allContact.set(index, newPerson);
    }

    public HomeScreenModel() {
        populateList();
    }

    /**
     * get the data from the db
     */
    public void populateList() {
        PersonDao personDao = new PersonDao();
        CategoryDao categoryDao = new CategoryDao();

        allContact = personDao.getPersons();
        allCategories = categoryDao.listCategories();
    }

    public ObservableList<Person> getAllContact() {
        return allContact;
    }


    public ObservableList<Category> getAllCategories() {
        return allCategories;
    }
}
