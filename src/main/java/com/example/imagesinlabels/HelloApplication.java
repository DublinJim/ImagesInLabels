package com.example.imagesinlabels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {




        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fruitMachineView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Images and labels etc");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("marquee.png"));
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}