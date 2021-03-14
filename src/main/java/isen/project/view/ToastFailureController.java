package isen.project.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * The type Toast failure controller.
 */
public class ToastFailureController {
    /**
     * The Text field.
     */
    @FXML
    public Text textField;


    /**
     * Set text of the toast.
     *
     * @param text the text
     */
    public void setText(String text){
        textField.setText(text);
    }


}
