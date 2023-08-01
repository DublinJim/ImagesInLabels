package com.example.imagesinlabels;

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



    private int randomReel() {
        Random random = new Random();
        return random.nextInt(2 + 1);
    }

    public void SpinUp() {
        int reel1 = randomReel();
        int reel2 = randomReel();
        int reel3 = randomReel();

        drumImg1.setImage(imageList.get(reel1));
        drumImg2.setImage(imageList.get(reel2));
        drumImg3.setImage(imageList.get(reel3));
        if (reel1 == reel2 && reel3 == reel2) {
            System.out.println("Jackpot!");
        }

    }
}//end
