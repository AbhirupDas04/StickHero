/*
 * THIS CLASS HANDLES THE WORKING OF THE HOME SCREEN.
 * */



package com.stickhero.stickhero;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class HomeScreen extends BackgroundHandler {
    private Pillar pillar;
    private GameScreen game;
    private HistoryStorage storage;
    private IntroMusic introMusic;

    public void setGame(GameScreen game){
        this.game = game;
    }

    public void setStorage(HistoryStorage storage){
        this.storage = storage;
    }

    public void setPillar(Pillar pillar){
        this.pillar = pillar;
    }

    public void setIntroMusic(IntroMusic introMusic){
        this.introMusic = introMusic;
    }

    public GameScreen getGame(){
        return this.game;
    }

    public Pillar getPillar(){
        return this.pillar;
    }

    public HistoryStorage getStorage(){
        return this.storage;
    }

    public IntroMusic getIntroMusic(){
        return this.introMusic;
    }

    public HomeScreen(Stage stage, GameScreen game){
        super(stage);
        this.game = game;
        super.setMusic(new IntroMusic());

        Image image = new Image(getClass().getResourceAsStream("Pic_10.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));
        this.introMusic = new IntroMusic();
        super.setMusic(new IntroMusic());
    }
    public void openHome(int mode){
        try {
            introMusic.start(super.getStage());
        }
        catch (Exception e) {
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
        bt.setTranslateY(210);

        Image image2 = new Image(this.getClass().getResourceAsStream("Yellow_Button.png"));
        ImageView ReloadButton = new ImageView(image2);
        ReloadButton.setPreserveRatio(true);
        ReloadButton.setFitHeight(100);
        ReloadButton.setFitWidth(200);
        ReloadButton.setLayoutX(150);
        ReloadButton.setLayoutY(350);

        Text reload_text = new Text("Load Save");
        reload_text.setLayoutY(404);
        reload_text.setLayoutX(191);
        reload_text.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, 21));
        reload_text.setFill(Color.BLACK);

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

        Pillar pillar = Pillar.getInstance(100,120,200,530);
        Rectangle rectangle = pillar.getRectangle();

        Hero hero = Hero.getInstance();
        ImageView view = hero.generateStandingHero(35,35,230,495);

        pane.getChildren().addAll(bt ,text,text2,rectangle,view,ReloadButton,reload_text);

        Scene scene1 = new Scene(pane, 500, 650);
        super.getStage().setScene(scene1);
        super.getStage().setResizable(false);
        super.getStage().show();

        HomeScreen homeScreen = this;


        BufferedReader fileInputStream;
        try {
            fileInputStream = new BufferedReader(new FileReader("StickHero\\Game_Details.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int n_entries = -1;
        try {
            n_entries = Integer.parseInt(fileInputStream.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<HistoryUnit> list = null;

        if(n_entries > 0){
            ObjectInputStream in;
            try {
                in = new ObjectInputStream(new FileInputStream("StickHero\\Game_Records.txt"));
                try {
                    list = (ArrayList<HistoryUnit>) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<HistoryUnit> new_list = new ArrayList<>();
        HistoryUnit unit2 = null;
        if(n_entries >= 1){
            for(int i = 0; i < n_entries; i++){
                int max = 0;
                for(HistoryUnit unit : list){
                    if(unit.getScore() >= max){
                        max = unit.getScore();
                        unit2 = unit;
                    }
                }
                new_list.add(unit2);
                list.remove(unit2);
            }
        }

        int finalN_entries = n_entries;
        ReloadButton.setOnMouseClicked(actionEvent -> {
            Pane reload_pane = new Pane();
            reload_pane.setBackground(homeScreen.getBackground());

            Rectangle shade = new Rectangle(500,650);
            shade.setFill(Color.BLACK);
            shade.setOpacity(0.7);

            Image scroll_image = new Image(this.getClass().getResourceAsStream("Scroll.png"));
            ImageView ScrollView = new ImageView(scroll_image);
            ScrollView.setPreserveRatio(true);
            ScrollView.setFitHeight(525);
            ScrollView.setFitWidth(420);
            ScrollView.setLayoutX(40);
            ScrollView.setLayoutY(62.5);

            Button returnButton = new Button("Return");
            returnButton.setMinSize(50,30);
            returnButton.setMaxSize(100,60);
            returnButton.setLayoutX(217);
            returnButton.setLayoutY(430);
            returnButton.setStyle("-fx-background-color:rgb(197, 0, 0);-fx-border-radius: 150;-fx-font-size:15;-fx-text-fill:white");

            Button GameFile1 = new Button("EMPTY");
            GameFile1.setMinSize(200,50);
            GameFile1.setMaxSize(200,50);
            GameFile1.setLayoutY(175);
            GameFile1.setLayoutX(150);
            GameFile1.setStyle("-fx-background-color:rgb(197, 197, 197);-fx-border-radius: 145px;-fx-font-size:15;-fx-text-fill:black;-fx-font-weight:bold");

            if(finalN_entries >= 1){
                GameFile1.setText("GAME 1 - Score: " + new_list.get(0).getScore());
                GameFile1.setOnMouseClicked(mouseEvent -> {
                    HistoryUnit unit = new_list.get(0);
                    introMusic.stop();
                    GameScreen new_game = new GameScreen(homeScreen.getStage());
                    new_game.loadGame(homeScreen,unit);
                });
            }

            Button GameFile2 = new Button("EMPTY");
            GameFile2.setMinSize(200,50);
            GameFile2.setMaxSize(200,50);
            GameFile2.setLayoutY(250);
            GameFile2.setLayoutX(150);
            GameFile2.setStyle("-fx-background-color:rgb(197, 197, 197);-fx-border-radius: 145px;-fx-font-size:15;-fx-text-fill:black;-fx-font-weight:bold");

            if(finalN_entries >= 2){
                GameFile2.setText("GAME 2 - Score: " + new_list.get(1).getScore());
                GameFile2.setOnMouseClicked(mouseEvent -> {
                    HistoryUnit unit = new_list.get(1);
                    introMusic.stop();
                    GameScreen new_game = new GameScreen(homeScreen.getStage());
                    new_game.loadGame(homeScreen,unit);
                });
            }

            Button GameFile3 = new Button("EMPTY");
            GameFile3.setMinSize(200,50);
            GameFile3.setMaxSize(200,50);
            GameFile3.setLayoutY(325);
            GameFile3.setLayoutX(150);
            GameFile3.setStyle("-fx-background-color:rgb(197, 197, 197);-fx-border-radius: 145px;-fx-font-size:15;-fx-text-fill:black;-fx-font-weight:bold");

            if(finalN_entries >= 3){
                GameFile3.setText("GAME 3 - Score: " + new_list.get(2).getScore());
                GameFile3.setOnMouseClicked(mouseEvent -> {
                    HistoryUnit unit = new_list.get(2);
                    introMusic.stop();
                    GameScreen new_game = new GameScreen(homeScreen.getStage());
                    new_game.loadGame(homeScreen,unit);
                });
            }


            reload_pane.getChildren().addAll(shade,ScrollView, returnButton, GameFile1,GameFile2,GameFile3);

            Scene load_scene = new Scene(reload_pane,500, 650);
            homeScreen.getStage().setScene(load_scene);
            homeScreen.getStage().show();

            returnButton.setOnAction(ActionEvent -> {
                homeScreen.getStage().setScene(scene1);
                homeScreen.getStage().show();
            });
        });

        reload_text.setOnMouseClicked(actionEvent -> {
            Pane reload_pane = new Pane();
            reload_pane.setBackground(homeScreen.getBackground());

            Rectangle shade = new Rectangle(500,650);
            shade.setFill(Color.BLACK);
            shade.setOpacity(0.7);

            Image scroll_image = new Image(this.getClass().getResourceAsStream("Scroll.png"));
            ImageView ScrollView = new ImageView(scroll_image);
            ScrollView.setPreserveRatio(true);
            ScrollView.setFitHeight(525);
            ScrollView.setFitWidth(420);
            ScrollView.setLayoutX(40);
            ScrollView.setLayoutY(62.5);

            Button returnButton = new Button("Return");
            returnButton.setMinSize(50,30);
            returnButton.setMaxSize(100,60);
            returnButton.setLayoutX(217);
            returnButton.setLayoutY(430);
            returnButton.setStyle("-fx-background-color:rgb(197, 0, 0);-fx-border-radius: 150;-fx-font-size:15;-fx-text-fill:white");

            Button GameFile1 = new Button("EMPTY");
            GameFile1.setMinSize(200,50);
            GameFile1.setMaxSize(200,50);
            GameFile1.setLayoutY(175);
            GameFile1.setLayoutX(150);
            GameFile1.setStyle("-fx-background-color:rgb(197, 197, 197);-fx-border-radius: 145px;-fx-font-size:15;-fx-text-fill:black;-fx-font-weight:bold");

            if(finalN_entries >= 1){
                GameFile1.setText("GAME 1 - Score: " + new_list.get(0).getScore());
                GameFile1.setOnMouseClicked(mouseEvent -> {
                    HistoryUnit unit = new_list.get(0);
                    introMusic.stop();
                    GameScreen new_game = new GameScreen(homeScreen.getStage());
                    new_game.loadGame(homeScreen,unit);
                });
            }

            Button GameFile2 = new Button("EMPTY");
            GameFile2.setMinSize(200,50);
            GameFile2.setMaxSize(200,50);
            GameFile2.setLayoutY(250);
            GameFile2.setLayoutX(150);
            GameFile2.setStyle("-fx-background-color:rgb(197, 197, 197);-fx-border-radius: 145px;-fx-font-size:15;-fx-text-fill:black;-fx-font-weight:bold");

            if(finalN_entries >= 2){
                GameFile2.setText("GAME 2 - Score: " + new_list.get(1).getScore());
                GameFile2.setOnMouseClicked(mouseEvent -> {
                    HistoryUnit unit = new_list.get(1);
                    introMusic.stop();
                    GameScreen new_game = new GameScreen(homeScreen.getStage());
                    new_game.loadGame(homeScreen,unit);
                });
            }

            Button GameFile3 = new Button("EMPTY");
            GameFile3.setMinSize(200,50);
            GameFile3.setMaxSize(200,50);
            GameFile3.setLayoutY(325);
            GameFile3.setLayoutX(150);
            GameFile3.setStyle("-fx-background-color:rgb(197, 197, 197);-fx-border-radius: 145px;-fx-font-size:15;-fx-text-fill:black;-fx-font-weight:bold");

            if(finalN_entries >= 3){
                GameFile3.setText("GAME 3 - Score: " + new_list.get(2).getScore());
                GameFile3.setOnMouseClicked(mouseEvent -> {
                    HistoryUnit unit = new_list.get(2);
                    introMusic.stop();
                    GameScreen new_game = new GameScreen(homeScreen.getStage());
                    new_game.loadGame(homeScreen,unit);
                });
            }


            reload_pane.getChildren().addAll(shade,ScrollView, returnButton, GameFile1,GameFile2,GameFile3);

            Scene load_scene = new Scene(reload_pane,500, 650);
            homeScreen.getStage().setScene(load_scene);
            homeScreen.getStage().show();

            returnButton.setOnAction(ActionEvent -> {
                homeScreen.getStage().setScene(scene1);
                homeScreen.getStage().show();
            });
        });

        bt.setOnAction(actionEvent -> {
            try {
                introMusic.stop();
                game.startGame(homeScreen, mode);

            } catch (Exception e){
                throw new RuntimeException(e);
            }
        });
    }
}