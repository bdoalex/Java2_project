package isen.project.model;

import isen.project.App;
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

public class EditContactModel {

    File fileProfilIcon;

    int posInGlobalList;
    Person actualPerson;
    Boolean setDefaultImage = false;



    OneContactController parentController;

    public void handleValidate(String textFieldLastName, String textFieldFirstName, String textFieldLNickName, String textFieldPhone, String textFieldAddress, String textFieldEmail, LocalDate datePickerBirth, int categoryId) throws IOException {
        String nameOfSaveFile = actualPerson.getNameFileIcon();

        if (fileProfilIcon != null  ) {
            if(!actualPerson.getNameFileIcon().equals(Constant.DEFAULT_IMAGE)){
                if(App.deleteProfilIcon(actualPerson.getNameFileIcon())){

                    nameOfSaveFile = App.saveProfilIcon(fileProfilIcon);
                }
                else{
                    //todo print red toast
                    return;
                }
            }
            else{
                nameOfSaveFile = App.saveProfilIcon(fileProfilIcon);
            }
        }
        else if(setDefaultImage){
            nameOfSaveFile = Constant.DEFAULT_IMAGE;
            if(!actualPerson.getNameFileIcon().equals(Constant.DEFAULT_IMAGE)){
                if(App.deleteProfilIcon(actualPerson.getNameFileIcon())){


                }
                else{
                    //todo print red toast
                    return;
                }
            }
            else{
                nameOfSaveFile = App.saveProfilIcon(fileProfilIcon);
            }
        }
        Person newPerson = new Person( actualPerson.getPersonId(),textFieldLastName,textFieldFirstName,textFieldLNickName,textFieldPhone,textFieldAddress,textFieldEmail,datePickerBirth,nameOfSaveFile,new Category(categoryId));
        parentController.setActualPerson(newPerson);
        parentController.getHomeScreenParentController().getHomeScreenModel().modifyOneContact(posInGlobalList,newPerson);
        PersonDao dao = new PersonDao();
        dao.modifyPerson(newPerson);
        App.closeDialog();
    }

    public Image clickOnImage(){
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

        fc.getExtensionFilters().add(imageFilter);
        //We open the file Chooser
        fileProfilIcon = fc.showOpenDialog(null);
        if (fileProfilIcon != null) {
            setDefaultImage = false;
            Image imageProfilIcon = new Image(fileProfilIcon.toURI().toString());
            return imageProfilIcon;
        }
        return null;
    }


    public void buttonDefault(){
        setDefaultImage=true;
    }

    public OneContactController getParentController() {
        return parentController;
    }

    public void setParentController(OneContactController parentController) {
        this.parentController = parentController;
    }

    public void setPosInGlobalList(int posInGlobalList) {
        this.posInGlobalList = posInGlobalList;
    }

    public void setActualPerson(Person actualPerson) {
        this.actualPerson = actualPerson;
    }

}
