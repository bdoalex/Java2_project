package isen.project.model;

import isen.project.App;
import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import isen.project.view.OneContactController;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The type Edit contact model.
 */
public class EditContactModel {

    /**
     * The File profil icon.
     */
    File fileProfilIcon;

    /**
     * The Pos in global list.
     */
    int posInGlobalList;


    /**
     * The Actual person.
     */
    Person actualPerson;
    /**
     * The Set default image.
     */
    Boolean setDefaultImage = false;


    /**
     * The Parent controller.
     */
    OneContactController parentController;
    /**
     * The Category dao.
     */
    CategoryDao categoryDao = new CategoryDao();


    /**
     * Handle validate.
     *
     * @param textFieldLastName  => string
     * @param textFieldFirstName => string
     * @param textFieldLNickName => string
     * @param textFieldPhone     =>     string
     * @param textFieldAddress   =>   string
     * @param textFieldEmail     =>     string
     * @param datePickerBirth    =>    date
     * @param category           the category
     * @throws IOException the io exception
     */
    public void handleValidate(String textFieldLastName, String textFieldFirstName, String textFieldLNickName, String textFieldPhone, String textFieldAddress, String textFieldEmail, LocalDate datePickerBirth, Category category) throws IOException {
        String nameOfSaveFile = actualPerson.getNameFileIcon();

        //if the user change the profil icon
        //this block check if we have to delete the previous icon
        //and if the icon is not the default icon we create the profil icon
        if (fileProfilIcon != null) {
            if (!actualPerson.getNameFileIcon().equals(Constant.DEFAULT_IMAGE)) {
                if (Boolean.FALSE.equals(App.deleteProfilIcon(actualPerson.getNameFileIcon()))) {

                    nameOfSaveFile = App.saveProfilIcon(fileProfilIcon);
                } else {
                    App.showFailureSnackBar("Error");
                    return;
                }
            } else {
                nameOfSaveFile = App.saveProfilIcon(fileProfilIcon);
            }
        }
        //if the new profil icon is the default icon
        else if (Boolean.TRUE.equals(setDefaultImage)) {
            nameOfSaveFile = Constant.DEFAULT_IMAGE;
            if (!actualPerson.getNameFileIcon().equals(Constant.DEFAULT_IMAGE)) {
                if (Boolean.FALSE.equals(App.deleteProfilIcon(actualPerson.getNameFileIcon()))) {
                    App.showFailureSnackBar("Error");
                    return;
                }
            }
        }
        Person newPerson = new Person(actualPerson.getPersonId(), textFieldLastName, textFieldFirstName, textFieldLNickName, textFieldPhone, textFieldAddress, textFieldEmail, datePickerBirth, nameOfSaveFile, category == null ? null : categoryDao.getCategory(category.getName()));
        parentController.setActualPerson(newPerson);
        parentController.getHomeScreenParentController().getHomeScreenModel().modifyOneContact(posInGlobalList, newPerson);
        App.closeDialog();
    }

    /**
     * Click on image to modify
     *
     * @return the new image choose by the user
     */
    public Image clickOnImage() {

        FileChooser fc = new FileChooser();
        //set extension
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

        fc.getExtensionFilters().add(imageFilter);
        //We open the file Chooser
        fileProfilIcon = fc.showOpenDialog(null);
        if (fileProfilIcon != null) {
            setDefaultImage = false;
            return new Image(fileProfilIcon.toURI().toString());

        }
        return null;
    }

    /**
     * Gets actual person.
     *
     * @return the actual person
     */
    public Person getActualPerson() {
        return actualPerson;
    }

    /**
     * Button button to choose default image.
     */
    public void buttonDefault() {
        setDefaultImage = true;
    }

    /**
     * Gets parent controller.
     *
     * @return the parent controller
     */
    public OneContactController getParentController() {
        return parentController;
    }

    /**
     * Sets parent controller.
     *
     * @param parentController the parent controller
     */
    public void setParentController(OneContactController parentController) {
        this.parentController = parentController;
    }

    /**
     * Sets pos in global list.
     *
     * @param posInGlobalList the pos in global list
     */
    public void setPosInGlobalList(int posInGlobalList) {
        this.posInGlobalList = posInGlobalList;
    }

    /**
     * Sets actual person.
     *
     * @param actualPerson the actual person
     */
    public void setActualPerson(Person actualPerson) {
        this.actualPerson = actualPerson;
    }

}
