package isen.project.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import isen.project.App;
import isen.project.model.HomeScreenModel;
import isen.project.model.entities.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Alexandre BARBOSA DE OLIVEIRA & Flavien DESSE
 * allows you to customize the display of cells in the listview
 */
public class CategoriesListViewCell extends ListCell<Category> {


    @FXML
    private final ListView<Category> parentListView;

    @FXML
    private HomeScreenModel homeScreenModel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text nameCategory;

    @FXML
    private FontIcon trashIcon;

    public CategoriesListViewCell(ListView<Category> list, HomeScreenModel homeScreenModel) {
        parentListView = list;
        this.homeScreenModel = homeScreenModel;
    }


    /**
     * @param category to display in the cell
     * @param empty    cell or not
     */
    @Override
    protected void updateItem(Category category, boolean empty) {
        super.updateItem(category, empty);
        FXMLLoader mLLoader;


        if (empty || category == null) {
            setText(null);
            setGraphic(null);
        } else {
            mLLoader = new FXMLLoader(getClass().getResource("/isen/project/view/CategoriesListCell.fxml"));
            mLLoader.setController(this);
            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            anchorPane.prefWidthProperty().bind(parentListView.widthProperty().subtract(50));
            nameCategory.setText(category.getName());
            trashIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {


                long numberContactTouched = homeScreenModel.getAllContact().stream().filter( p -> {
                    return p.getCategory() != null && p.getCategory().getId() == category.getId();
                }).count();
                JFXDialogLayout content = new JFXDialogLayout();
                content.setBody(new Text("Are you sure you want to delete " + category.getName() + "? \n" + numberContactTouched + " contact will be concerned"));


                List<Button> allActions = new ArrayList<>();

                JFXButton validate = new JFXButton("YES");
                validate.setStyle("-jfx-button-type: RAISED; -fx-text-fill: white; -fx-background-color:#4CAF50;-fx-cursor: hand;");


                JFXButton deny = new JFXButton("NO");
                deny.setStyle("-jfx-button-type: RAISED; -fx-text-fill: white; -fx-background-color:#f44336;-fx-cursor: hand;");


                validate.setOnAction(event -> {


                    try {
                        if (Boolean.TRUE.equals(homeScreenModel.deleteOneCategory(category))) {
                            App.showSuccessSnackBar("Success");
                        } else {
                            App.showFailureSnackBar("Echec");
                        }
                        App.closeDialog();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                deny.setOnAction(event -> App.closeDialog());

                allActions.add(validate);
                allActions.add(deny);

                content.setActions(allActions);
                App.showDialog(content);




















            });


            setGraphic(anchorPane);
        }

    }
}

