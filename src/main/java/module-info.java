module isen.java2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens isen.java2 to javafx.fxml;
    exports isen.java2;
}