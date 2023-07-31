package com.example.imagesinlabels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public AnchorPane rootPane;
    public ImageView drumImg1;
    public ImageView drumImg2;
    public ImageView drumImg3;
    public Button btnHold1;
    public Button btnHold2;
    public Button btnHold3;
    public Button btnBet;
    public Button btnSpin;
    private final ArrayList<Image> imageList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        imageList.add(new Image("bell.png"));
        imageList.add(new Image("cherry.png"));
        imageList.add(new Image("grapes.png"));
        imageList.add(new Image("lemon.png"));
        imageList.add(new Image("melon.png"));
        imageList.add(new Image("orange.png"));
        imageList.add(new Image("seven.png"));
        imageList.add(new Image("strawberry.png"));



        drumImg1.setImage(imageList.get(0));
        drumImg2.setImage(imageList.get(0));
        drumImg3.setImage(imageList.get(0));

        randomReel();

    }  //end init

    private void spinUp(){

    }

    private int randomReel() {
        Random random = new Random();
        int rand1= random.nextInt(2 + 1);
        return rand1;
    }

    public void SpinUp(ActionEvent actionEvent) {
        drumImg1.setImage(imageList.get(randomReel()));
        drumImg2.setImage(imageList.get(randomReel()));
        drumImg3.setImage(imageList.get(randomReel()));


    }
}//end
