package isen.project.model;

import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

/**
 * The type Home screen model.
 */
public class HomeScreenModel {
    /**
     * The Category dao.
     */
    CategoryDao categoryDao = new CategoryDao();
    /**
     * The Person dao.
     */
    PersonDao personDao = new PersonDao();
    //We store all contact here to avoid multiple call  to the db
    private ObservableList<Person> allContact = FXCollections.observableArrayList();


    private ObservableList<Category> allCategories = FXCollections.observableArrayList();


    /**
     * Add one contact.
     *
     * @param newPerson the new person
     */
    public void addOneContact(Person newPerson) {
        personDao.addPerson(newPerson);
        allContact.add(newPerson);
    }

    /**
     * Add one category category.
     *
     * @param name the name
     * @return the category
     */
    public Category addOneCategory(String name) {
        Category newCategory = categoryDao.addCategory(name);
        allCategories.add(newCategory);
        return newCategory;
    }

    /**
     * Delete one person boolean.
     *
     * @param person the person
     * @return the boolean
     */
    public boolean deleteOnePerson(Person person) {


        if (Boolean.TRUE.equals(personDao.deletePersonById(person.getPersonId()))) {
            allContact.remove(person);
            return true;
        }
        return false;
    }

    /**
     * Delete one category boolean.
     *
     * @param category the category
     * @return the boolean
     */
    public Boolean deleteOneCategory(Category category) {

        if (Boolean.TRUE.equals(categoryDao.deleteCategoryById(category.getId()))) {
            allCategories.remove(category);
            allContact = allContact.stream().map(
                    p ->{
                        if(p.getCategory() != null && p.getCategory().getId() == category.getId()){
                            p.setCategory(null);
                        }
                        return p;
                    }
            ).collect(Collectors.toCollection(FXCollections::observableArrayList));
            return true;
        }
        return false;


    }

    /**
     * Modify one contact.
     *
     * @param index     the index
     * @param newPerson the new person
     */
    public void modifyOneContact(int index, Person newPerson) {
        personDao.modifyPerson(newPerson);
        allContact.set(index, newPerson);
    }

    /**
     * Instantiates a new Home screen model.
     */
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

    /**
     * Gets all contact.
     *
     * @return the all contact
     */
    public ObservableList<Person> getAllContact() {
        return allContact;
    }


    /**
     * Gets all categories.
     *
     * @return the all categories
     */
    public ObservableList<Category> getAllCategories() {
        return allCategories;
    }
}
