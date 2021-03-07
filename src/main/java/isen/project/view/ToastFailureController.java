package isen.project.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ToastFailureController {
    @FXML
    public Text textField;


    public void setText(String text){
        textField.setText(text);
    }


}
