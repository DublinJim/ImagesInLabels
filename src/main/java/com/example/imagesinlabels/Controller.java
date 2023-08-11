package com.example.imagesinlabels;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    public ColumnConstraints reelGrid;
    public HBox spinHbox;
    public Label lblUpBet;
    public Label lblDownBet;
    public Label lblCredits;
    public Text txtCredits;
    public Label lblAmtBet;
    public Text txtAmtBet;
    public ImageView imgTestWinner;
    private int credit = 10;
    private boolean onWinning;
    private int winnings;
    private Timeline flashAnimation1;
    private Timeline flashAnimation2;
    private Timeline flashAnimation3;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //begin /////////////////////////////////////////////////////


        writeOut("log file "+getClass().getName());


        imgTestWinner.setVisible(false);
        Image backgroundImage = new Image("background2.PNG");
        Background background = getBackground(backgroundImage);
        rootPane.setBackground(background);



        Image reelsPic = new Image("marquee.png");
        Background reelBackGround = getBackground(reelsPic);
        spinHbox.setBackground(reelBackGround);

        jkPotTxt.setText("Play Now");
        jkPotTxt.setStyle("-fx-font-size: 24; -fx-text-fill: red;");

        // Create the Timeline to control the blinking animation
        winnerFlash(getTimelineText(jkPotTxt));

Logger.getLogger(getClass().getName()).log(Level.INFO,"this is logged "+background);


        imageList.add(new Image("bell.png"));
        imageList.add(new Image("cherry.png"));
        imageList.add(new Image("grapes.png"));
        imageList.add(new Image("lemon.png"));
        imageList.add(new Image("melon.png"));
        imageList.add(new Image("orange.png"));
        imageList.add(new Image("seven.png"));
        imageList.add(new Image("strawberry.png"));
        imageList.add(new Image("coin.png"));

       // Logger.getLogger(getClass().getName()).log(Level.INFO,"this is logged "+imageList);


        drumImg1.setImage(imageList.get(0));
        drumImg2.setImage(imageList.get(0));
        drumImg3.setImage(imageList.get(0));

        btnSpin.setStyle("#btnSpin");
        btnSpin.setOnAction(e -> spinUp());


        imagesToNodes("upArrow.png", lblUpBet);
        imagesToNodes("downArrow.png", lblDownBet);
        lblAmtBet.setText("");
        imagesToNodes("bettingImg.PNG", lblAmtBet);

        lblUpBet.setOnMouseClicked(mouseEvent -> betUp());
        lblDownBet.setOnMouseClicked(mouseEvent -> betDown());

        betAmtTxt.setText(String.valueOf(credit));
        btnBet.setOnAction(event -> placeBet());


    }  //end init

    private static void writeOut(String errorString) {
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(errorString);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Timeline flashHoldBtns(Button button, Timeline timeline) {
        if (timeline == null) {
            timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.5), event -> button.setVisible(!button.isVisible()))
            );
        }
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.stop();
        timeline.play();

        return timeline;
    }

    private void winnerFlash(Timeline imgTestWinner) {
        imgTestWinner.setCycleCount(Animation.INDEFINITE);
        imgTestWinner.play();
    }

    private Timeline getTimelineText(Text textNode) {
        return new Timeline(
                new KeyFrame(Duration.ZERO, event -> textNode.setVisible(true)),
                new KeyFrame(Duration.seconds(0.5), event -> textNode.setVisible(false)),
                new KeyFrame(Duration.seconds(1.0), event -> textNode.setVisible(true))
        );
    }

    private Timeline getTimelineForImage(ImageView imageView) {
        return new Timeline(
                new KeyFrame(Duration.ZERO, event -> imageView.setVisible(true)),
                new KeyFrame(Duration.seconds(0.5), event -> imageView.setVisible(false)),
                new KeyFrame(Duration.seconds(1.0), event -> imageView.setVisible(true))
        );
    }


    private void betUp() {

        if (!onWinning) {
            if (credit < 10) {
                credit++;
                betAmtTxt.setText(String.valueOf(credit));
            } else if (credit == 10) {
                betAmtTxt.setText(String.valueOf(credit));
            }
        }
    }

    private void betDown() {
        if (!onWinning) {
            if (credit > 0) {
                credit--;
                betAmtTxt.setText(String.valueOf(credit));
            } else if (credit == 0) betAmtTxt.setText("0");
        }
    }

    private void imagesToNodes(String imageName, Label label) {
        Image image = new Image(imageName);
        ImageView imageView = new ImageView(image);
        label.setGraphic(imageView);
    }

    private void placeBet() {

    }

    @FXML
    private int randomReel() {
        Random random = new Random();

        return random.nextInt( imageList.size());
    }


    public void spinUp() {
        int reel1 = randomReel();
        int reel2 = randomReel();
        int reel3 = randomReel();
        Logger.getLogger(getClass().getName()).log(Level.INFO,"this is logged "+reel1);
        drumImg1.setImage(imageList.get(reel1));
        drumImg2.setImage(imageList.get(reel2));
        drumImg3.setImage(imageList.get(reel3));
        jkPotTxt.setText("Spin!!");

        flashAnimation1 = flashHoldBtns(btnHold1, flashAnimation1);
        flashAnimation2 = flashHoldBtns(btnHold2, flashAnimation2);
        flashAnimation3 = flashHoldBtns(btnHold3, flashAnimation3);


        validateWin(reel1, reel2, reel3);
    }

    private void validateWin(int reel1, int reel2, int reel3) {
        if (reel1 == reel2 && reel3 == reel2) {
            onWinning = true;
            jkPotTxt.setText("WINNER !!!!");
            turnOffButtons();
            stopAllHoldBtnsFlash();
            winnings = credit * 3;
            txtCredits.setText(String.valueOf(winnings));
        }
    }

    private void stopAllHoldBtnsFlash() {
        flashAnimation1.stop();
        flashAnimation2.stop();
        flashAnimation3.stop();
    }

    private void turnOffButtons() {
        btnBet.setDisable(true);
        btnSpin.setDisable(true);

    }


    private static Background getBackground(Image image) {
        BackgroundImage backgroundImg = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT, // Set to REPEAT if you want to repeat the image
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)

        );

        return new Background(backgroundImg);
    }


}//end
