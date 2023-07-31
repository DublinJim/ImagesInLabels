module com.example.imagesinlabels {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.imagesinlabels to javafx.fxml;
    exports com.example.imagesinlabels;
}