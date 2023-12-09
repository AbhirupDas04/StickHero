/*THIS CLASS HANDLES THE END SCREEN SHOWN AFTER THE HERO'S DEATH.*/

package com.stickhero.stickhero;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class EndScreen extends BackgroundHandler {
    private Hero hero;
    private GameScreen game;
    private Image image;
    private HistoryStorage storage;
    private EndingMusic endingMusic;

    public void setHero(Hero hero){
        this.hero = hero;
    }

    public void setGameScreen(GameScreen game){
        this.game = game;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public void setHistoryStorage(HistoryStorage storage){
        this.storage = storage;
    }

    public void setEndingMusic(EndingMusic endingMusic){
        this.endingMusic = endingMusic;
    }

    public Hero getHero(){
        return this.hero;
    }

    public GameScreen getGameScreen(){
        return this.game;
    }

    public Image getImage(){
        return this.image;
    }

    public HistoryStorage getStorage(){
        return this.storage;
    }

    public EndingMusic getEndingMusic(){
        return this.endingMusic;
    }

    public EndScreen(Stage stage, Hero hero, GameScreen game, Image image, HistoryStorage storage){
        super(stage);
        this.hero = hero;
        this.game = game;
        this.image = image;
        this.endingMusic = new EndingMusic();
        super.setMusic(new EndingMusic());
        this.storage = storage;
    }
    public void endGame(Pane pane, Scene scene1, HomeScreen home_scene, int score, GameScreen game){
        try {
            endingMusic.start(super.getStage());
        } catch (Exception e) {
            e.printStackTrace();  // Handle the exception according to your application's needs
        }
        Rectangle rectangle1 = new Rectangle(500, 650);
        rectangle1.setFill(Color.BLACK);
        rectangle1.setLayoutX(0);
        rectangle1.setLayoutY(0);
        rectangle1.setOpacity(0.45);

        Text text = new Text();
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 65));
        text.setFill(Color.WHITE);
        text.setLayoutX(40);
        text.setLayoutY(85);
        text.setText("GAME OVER!");

        Rectangle rectangle2 = new Rectangle(430,200 );
        rectangle2.setFill(Color.WHITE);
        rectangle2.setLayoutX(35);
        rectangle2.setLayoutY(150);
        rectangle2.setArcHeight(30);
        rectangle2.setArcWidth(30);

        Text text2 = new Text();
        text2.setFont(Font.font("verdana", FontPosture.REGULAR, 30));
        text2.setFill(Color.BLACK);
        text2.setLayoutX(195);
        text2.setLayoutY(185);
        text2.setText("SCORE");

        Text text3 = new Text();
        text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 55));
        text3.setFill(Color.BLACK);
        text3.setLayoutX(229);
        text3.setLayoutY(241);
        text3.setText(((Integer)score).toString());

        Text text4 = new Text();
        text4.setFont(Font.font("verdana", FontPosture.REGULAR, 30));
        text4.setFill(Color.BLACK);
        text4.setLayoutX(206);
        text4.setLayoutY(280);
        text4.setText("BEST");


        BufferedReader fileInputStream;
        try {
            fileInputStream = new BufferedReader(new FileReader("StickHero\\Best_Score.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int best = score;

        try {
            best = Integer.parseInt(fileInputStream.readLine());
            if(best < score){
                best = score;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream("StickHero\\Best_Score.txt");

            fileOutputStream.write(Integer.toString(best).getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Text text5 = new Text();
        text5.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 55));
        text5.setFill(Color.BLACK);
        text5.setLayoutX(229);
        text5.setLayoutY(333);
        text5.setText(((Integer)best).toString());

        Rectangle rectangle3 = new Rectangle(200,80 );
        rectangle3.setFill(Color.GRAY);
        rectangle3.setLayoutX(150);
        rectangle3.setLayoutY(430);
        rectangle3.setArcHeight(30);
        rectangle3.setArcWidth(30);

        Image image2 = new Image(this.getClass().getResourceAsStream("home-button.png"));
        ImageView view = new ImageView(image2);
        view.setPreserveRatio(true);
        view.setFitHeight(50);
        view.setFitWidth(50);
        view.setLayoutX(183);
        view.setLayoutY(445);

        Image image3 = new Image(this.getClass().getResourceAsStream("reload.png"));
        ImageView view2 = new ImageView(image3);
        view2.setPreserveRatio(true);
        view2.setFitHeight(67);
        view2.setFitWidth(67);
        view2.setLayoutX(250);
        view2.setLayoutY(437);

        Button Resume_Button = new Button();
        Resume_Button.setStyle("-fx-background-color:rgb(0, 190, 0);-fx-border-radius: 150;-fx-font-size:15;-fx-text-fill:black;-fx-font-weight:bold");
        Resume_Button.setMinSize(120,60);
        Resume_Button.setMaxSize(120,60);
        Resume_Button.setText("Resume!\n(-5 Cherries)");
        Resume_Button.setLayoutX(190);
        Resume_Button.setLayoutY(550);
        Resume_Button.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(rectangle1,rectangle2,rectangle3,view2,view,text,text2,text3,text4,text5, Resume_Button);

        super.getStage().setScene(scene1);
        super.getStage().show();

        Stage stage = super.getStage();

        view.setOnMouseClicked(mouseEvent -> {
            endingMusic.stop();
            home_scene.openHome(1);
        });

        view2.setOnMouseClicked(mouseEvent -> {
            endingMusic.stop();
            GameScreen new_game = new GameScreen(home_scene.getStage());
            new_game.startGame(home_scene,1);
        });

        Resume_Button.setOnMouseClicked(mouseEvent -> {
            if(HistoryStorage.getN_cherries() >= 5){
                GameScreen new_game = new GameScreen(home_scene.getStage());
                new_game.restartGame(home_scene);
            }
        });
    }
}