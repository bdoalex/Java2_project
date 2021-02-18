package isen.project.util;

import isen.project.model.entities.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;


/**
 * @author Alexandre BARBOSA DE OLIVEIRA & Flavien DESSE
 * allows you to customize the display of cells in the listview
 */
public class PersonListViewCell extends ListCell<Person>{

    private FXMLLoader mLLoader;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text textListCell;

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

            textListCell.setText(person.getFirstName());
            setGraphic(anchorPane);
        }

    }
}