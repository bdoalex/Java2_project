package isen.project.util;

import isen.project.model.HomeScreenModel;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;


/**
 * @author Alexandre BARBOSA DE OLIVEIRA & Flavien DESSE
 * allows you to customize the display of cells in the listview
 */
public class CategoriesListViewCell extends ListCell<Category>{




    @FXML
    private final ListView<Category> parentListView;

    @FXML
    private  HomeScreenModel homeScreenModel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text nameCategory;

    @FXML
    private FontIcon trashIcon;

    public CategoriesListViewCell(ListView<Category> list,HomeScreenModel homeScreenModel) {
        parentListView = list;
        this.homeScreenModel = homeScreenModel;
    }


    /**
     * @param category to display in the cell
     * @param empty cell or not
     */
    @Override
    protected void updateItem(Category category, boolean empty) {
        super.updateItem(category, empty);
        FXMLLoader mLLoader;


        if(empty || category==null){
            setText(null);
            setGraphic(null);
        }
        else{
            mLLoader = new FXMLLoader(getClass().getResource("/isen/project/view/CategoriesListCell.fxml"));
            mLLoader.setController(this);
            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            anchorPane.prefWidthProperty().bind(parentListView.widthProperty().subtract(50));
            nameCategory.setText(category.getName());
            trashIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    homeScreenModel.deleteOneCategory(category);
                }
            });


            setGraphic(anchorPane);
        }

    }
}

