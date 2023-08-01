package com.example.imagesinlabels;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final ArrayList<Image> imageList = new ArrayList<>();
    public AnchorPane rootPane;
    public ImageView drumImg1;
    public ImageView drumImg2;
    public ImageView drumImg3;
    public Button btnHold1;
    public Button btnHold2;
    public Button btnHold3;
    public Button btnBet;
    public Button btnSpin;
    public Text betAmtTxt;
    public Text jkPotTxt;
    public ImageView upImgView;
    public ImageView downImgView;
    private int credit = 10;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image backgroundImage = new Image("background.PNG");

        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Set to REPEAT if you want to repeat the image
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );




        Background background = new Background(backgroundImg);

rootPane.setBackground(background);

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

        btnSpin.setStyle("#btnSpin");
        btnSpin.setOnAction(e -> spinUp());


        Image upImg = new Image("upArrow.png");
        Image downImg = new Image("downArrow.png");

        upImgView.setImage(upImg);
        downImgView.setImage(downImg);

        betAmtTxt.setText(String.valueOf(credit));

    }  //end init


    @FXML
    private int randomReel() {
        Random random = new Random();
        return random.nextInt(2 + 1);
    }


    public void spinUp() {
        int reel1 = randomReel();
        int reel2 = randomReel();
        int reel3 = randomReel();

        drumImg1.setImage(imageList.get(reel1));
        drumImg2.setImage(imageList.get(reel2));
        drumImg3.setImage(imageList.get(reel3));
        jkPotTxt.setText("Spin!!!");
        validateWin(reel1, reel2, reel3);
    }

    private void validateWin(int reel1, int reel2, int reel3) {
        if (reel1 == reel2 && reel3 == reel2) {
            jkPotTxt.setText("WINNER !!!!");
        }
    }
}//end
