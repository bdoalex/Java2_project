package isen.project.util;

import isen.project.model.entities.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;


/**
 * @author Alexandre BARBOSA DE OLIVEIRA & Flavien DESSE
 * allows you to customize the display of cells in the listview
 */
public class PersonListViewCell extends ListCell<Person>{

    private FXMLLoader mLLoader;

    @FXML
    private ListView parentListView;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text nameContact;

    @FXML
    private Text telContact;

    @FXML
    private ImageView imageContact;

    public PersonListViewCell(ListView list) {
        parentListView = list;
    }



    /**
     * @param person to display in the cell
     * @param empty cell or not
     */
    @Override
    protected void updateItem(Person person, boolean empty) {



        super.updateItem(person, empty);

        if(empty || person==null){
            setText(null);
            setGraphic(null);
        }
        else{
            mLLoader = new FXMLLoader(getClass().getResource("/isen/project/view/ContactListCell.fxml"));
            mLLoader.setController(this);
            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image image = new Image("file:\\"+Constant.URL_TO_IMAGE+person.getNameFileIcon());


            imageContact.setImage(image);
            nameContact.setText(person.getFirstName());
            telContact.setText(person.getPhoneNumber());
            anchorPane.prefWidthProperty().bind(parentListView.widthProperty().subtract(50));

            setGraphic(anchorPane);
        }

    }
}