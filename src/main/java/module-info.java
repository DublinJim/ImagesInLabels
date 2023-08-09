module com.example.imagesinlabels {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.imagesinlabels to javafx.fxml;
    exports com.example.imagesinlabels;
}