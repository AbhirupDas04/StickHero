package com.stickhero.stickhero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeScreen extends BackgroundHandler {
    private Pillar pillar;
    private GameScreen game;
    private HistoryStorage storage;
    private IntroMusic introMusic;

    public HomeScreen(Stage stage, GameScreen game, HistoryStorage storage){
        super(stage);
        this.game = game;
        super.setMusic(new IntroMusic());
        this.storage = storage;

        Image image = new Image(getClass().getResourceAsStream("Pic_10.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));
        this.introMusic = new IntroMusic();
        super.setMusic(new IntroMusic());
    }
    public void openHome() throws InterruptedException{
        try {
            introMusic.start(super.getStage());
        } catch (Exception e) {
            e.printStackTrace();  // Handle the exception according to your application's needs
        }

        Pane pane = this.returnBackground();


        Button bt = new Button();
        bt.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-font-size:30;-fx-text-fill:white");
        bt.setShape(new Circle(125));
        bt.setMinSize(125,125);
        bt.setMaxSize(125,125);
        bt.setText("PLAY");
        bt.setTranslateX(185);
        bt.setTranslateY(260);

        Rectangle settingsButtonBackground = new Rectangle(85 , 32);
        settingsButtonBackground.setFill(Color.DARKOLIVEGREEN);
        settingsButtonBackground.setArcWidth(20);
        settingsButtonBackground.setArcHeight(20);
        settingsButtonBackground.setTranslateX(14.2);
        settingsButtonBackground.setTranslateY(398);


        Image settingsButtonImage = new Image(getClass().getResourceAsStream("settings_button.png"));
        ImageView settingsButtonImageView = new ImageView(settingsButtonImage);
        settingsButtonImageView.setFitWidth(50);
        settingsButtonImageView.setFitHeight(50);

        Text settingsButtonText = new Text("SETTINGS");
        settingsButtonText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13)); // Adjust the font size
        settingsButtonText.setFill(Color.WHITE);

        Button settingsButton = new Button();
        settingsButton.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-font-size:40;-fx-text-fill:white");
        settingsButton.setGraphic(settingsButtonImageView);
        settingsButton.setGraphicTextGap(60);
        settingsButton.setStyle("-fx-background-color: transparent;");
//        settingsButton.setText("SETTINGS");
        settingsButton.setTranslateX(12);
        settingsButton.setTranslateY(402);
        settingsButton.setGraphic(settingsButtonText);


//        Rectangle reloadSavedGameButtonBackground = new Rectangle(200 , 32);
//        reloadSavedGameButtonBackground.setFill(Color.DARKOLIVEGREEN);
//        reloadSavedGameButtonBackground.setArcWidth(20);
//        reloadSavedGameButtonBackground.setArcHeight(20);
//        reloadSavedGameButtonBackground.setTranslateX(155);
//        reloadSavedGameButtonBackground.setTranslateY(400);
//
//
//        Image reloadSavedGameButtonImage = new Image(getClass().getResourceAsStream("reload_saved_game_button.png"));
//        ImageView reloadSavedGameButtonImageView = new ImageView(settingsButtonImage);
//        settingsButtonImageView.setFitWidth(50);
//        settingsButtonImageView.setFitHeight(50);
//
//        Text reloadSavedGameButtonText = new Text("RELOAD SAVED GAME");
//        reloadSavedGameButtonText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13)); // Adjust the font size
//        reloadSavedGameButtonText.setFill(Color.WHITE);
//
//        Button reloadSavedGameButton = new Button();
//        reloadSavedGameButton.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-font-size:40;-fx-text-fill:white");
//        reloadSavedGameButton.setGraphic(settingsButtonImageView);
//        reloadSavedGameButton.setGraphicTextGap(60);
//        reloadSavedGameButton.setStyle("-fx-background-color: transparent;");
////        settingsButton.setText("SETTINGS");
//        reloadSavedGameButton.setTranslateX(165);
//        reloadSavedGameButton.setTranslateY(403);
//        reloadSavedGameButton.setGraphic(reloadSavedGameButtonText);

        Rectangle reloadSavedGameButtonBackground = new Rectangle(170 , 32);
        reloadSavedGameButtonBackground.setFill(Color.DARKOLIVEGREEN);
        reloadSavedGameButtonBackground.setArcWidth(20);
        reloadSavedGameButtonBackground.setArcHeight(20);
        reloadSavedGameButtonBackground.setTranslateX(14);
        reloadSavedGameButtonBackground.setTranslateY(450);


        Image reloadSavedGameButtonImage = new Image(getClass().getResourceAsStream("reload_saved_game_button.png"));
        ImageView reloadSavedGameButtonImageView = new ImageView(settingsButtonImage);
        settingsButtonImageView.setFitWidth(50);
        settingsButtonImageView.setFitHeight(50);

        Text reloadSavedGameButtonText = new Text("RELOAD SAVED GAME");
        reloadSavedGameButtonText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13)); // Adjust the font size
        reloadSavedGameButtonText.setFill(Color.WHITE);

        Button reloadSavedGameButton = new Button();
        reloadSavedGameButton.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-font-size:40;-fx-text-fill:white");
        reloadSavedGameButton.setGraphic(settingsButtonImageView);
        reloadSavedGameButton.setGraphicTextGap(60);
        reloadSavedGameButton.setStyle("-fx-background-color: transparent;");
//        settingsButton.setText("SETTINGS");
        reloadSavedGameButton.setTranslateX(10);
        reloadSavedGameButton.setTranslateY(453);
        reloadSavedGameButton.setGraphic(reloadSavedGameButtonText);

        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70));
        text.setLayoutX(130);
        text.setLayoutY(100);
        text.setText("STICK");

        Text text2 = new Text();
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70));
        text2.setLayoutX(140);
        text2.setLayoutY(160);
        text2.setText("HERO");

        Pillar pillar = new Pillar(100,120,200,530);
        Rectangle rectangle = pillar.getRectangle();

        Hero hero = new Hero();
        ImageView view = hero.generateStandingHero(35,35,230,495);

        pane.getChildren().addAll(bt , reloadSavedGameButtonBackground , reloadSavedGameButton , settingsButtonBackground,settingsButton,text,text2,rectangle,view);

        Scene scene1 = new Scene(pane, 500, 650);
        super.getStage().setScene(scene1);
        super.getStage().setResizable(false);
        super.getStage().show();

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    introMusic.stop();
                    game.startGame();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
