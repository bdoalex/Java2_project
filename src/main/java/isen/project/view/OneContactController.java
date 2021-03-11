package isen.project.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import isen.project.App;
import isen.project.ParentController;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


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

        String unk = "Unknown";
        
        if (actualPerson.getPhoneNumber().equals("")){
            phoneNumber.setText(unk);
        }else{
            phoneNumber.setText(actualPerson.getPhoneNumber());
        }

        if (actualPerson.getAddress().equals("")){
            adress.setText(unk);
        }else{
            adress.setText(actualPerson.getAddress());
        }

        if (actualPerson.getEmailAddress().equals("")){
            email.setText(unk);
        }else{
            email.setText(actualPerson.getEmailAddress());
        }

        if (actualPerson.getBirthDate()==null){
            birthDate.setText(unk);
        }else{
            birthDate.setText(actualPerson.getBirthDate().toString());
        }
        if (actualPerson.getCategory()==null){
            category.setText(unk);
        }else{
            category.setText(actualPerson.getCategory().getName());

        }

        name.setText(actualPerson.getFirstName() + " " + actualPerson.getLastName());
        nickName.setText(actualPerson.getNickName());



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

    public void handleButtonBack() {
        try {
            homeScreenParentController.changeViewToAllContact();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void handleClickOnTrash() {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setBody(new Text("Are you sure you want to delete " + actualPerson.getFirstName() + " " + actualPerson.getLastName() + " from your contacts ? "));


        List<Button> allActions = new ArrayList<>();

        JFXButton validate = new JFXButton("YES");
        validate.setStyle("-jfx-button-type: RAISED; -fx-text-fill: white; -fx-background-color:#4CAF50;-fx-cursor: hand;");


        JFXButton deny = new JFXButton("NO");
        deny.setStyle("-jfx-button-type: RAISED; -fx-text-fill: white; -fx-background-color:#f44336;-fx-cursor: hand;");


        validate.setOnAction(event -> {
            PersonDao personDao = new PersonDao();
            if (Boolean.TRUE.equals(personDao.deletePersonById(actualPerson.getPersonId()))) {
                homeScreenParentController.reloadFromDb();
            }
            App.closeDialog();
            try {
                getHomeScreenParentController().changeViewToAllContact();
                App.showSuccessSnackBar("Success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deny.setOnAction(event -> App.closeDialog());

        allActions.add(validate);
        allActions.add(deny);

        content.setActions(allActions);
        App.showDialog(content);
    }

    public void handleClickOnEditProfile() throws IOException {




        ObservableList<Person> users= homeScreenParentController.getHomeScreenModel().getAllContact();

        int indexOpt = IntStream.range(0,users.size())
                .filter(i -> actualPerson.getPersonId() == users.get(i).getPersonId())
                .findFirst().getAsInt();


        FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/isen/project/view/EditContact.fxml"));
        VBox layout = mLLoader.load();

        EditContactController controller = mLLoader.getController();

        controller.setActualPerson(actualPerson,indexOpt);
        controller.setParentController(this);

        App.showDialog(layout);
    }
}
