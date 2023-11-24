package com.stickhero.stickhero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class GameScreen extends BackgroundHandler {
    private Hero hero;
    private int gamespeed;
    private int n_cherries;
    private Image image;
    private HistoryStorage storage;
    private Score score;
    private int level;
    public GameScreen(Stage stage, HistoryStorage storage){
        super(stage);
        this.hero = new Hero();
        this.selectRandomImage();
        this.gamespeed = 1;
        this.score = new Score();
        this.n_cherries = 0;
        this.storage = storage;
        this.level = 0;
        super.setMusic(new InGameMusic());
    }
    public void updateGameSpeed(int speed){
        this.gamespeed = speed;
    }
    private String selectRandomIndex(){
        Random random = new Random();
        int rand = random.nextInt(11) + 1;
        String num = Integer.toString(rand);
        return num;
    }
    private void selectRandomImage(){
        String randomImage = "Pic_" + selectRandomIndex() + ".jpg";
        Image image = new Image(getClass().getResourceAsStream(randomImage));

        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));
        this.image = image;
    }

    public void startGame() throws InterruptedException {
        Pane game_pane = this.returnBackground();
        Pillar pillar = new Pillar(100,160,0,490);
        Rectangle rectangle = pillar.getRectangle();

        Random rand = new Random();
        int rand_width = rand.nextInt(70) + 30;
        int rand_posX = rand.nextInt(370 - rand_width) + 130;
        pillar = new Pillar(rand_width,160,rand_posX,490);
        Rectangle rectangle2 = pillar.getRectangle();

        Rectangle rect3 = new Rectangle(10, 5);
        rect3.setFill(Color.RED);
        rect3.setLayoutX(rand_posX + rand_width / 2.0 - 5);
        rect3.setLayoutY(490);

        ImageView view = hero.generateStandingHero(35,35,55,455);

        Rectangle rectangle1 = new Rectangle(70, 40);
        rectangle1.setFill(Color.GRAY);
        rectangle1.setLayoutX(215);
        rectangle1.setLayoutY(50);
        rectangle1.setArcHeight(15);
        rectangle1.setArcWidth(15);
        rectangle1.setOpacity(0.4);

        Text text = new Text();
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 36));
        text.setFill(Color.WHITE);
        text.setLayoutX(239);
        text.setLayoutY(84);
        text.setText("0");

        Reward cherry = new Reward();
        ImageView view1 = cherry.generateReward(30,30,467,35);

        Text text1 = new Text();
        text1.setFont(Font.font("verdana", FontPosture.REGULAR, 16));
        text1.setFill(Color.WHITE);
        text1.setLayoutX(450);
        text1.setLayoutY(56);
        text1.setText("0");

        Button bt = new Button("EXIT");
        bt.setMinSize(50,30);
        bt.setMaxSize(100,60);
        bt.setLayoutX(25);
        bt.setLayoutY(25);
        bt.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-border-radius: 150;-fx-font-size:15;-fx-text-fill:white");

        game_pane.getChildren().addAll(rectangle,bt,rectangle2,rect3,rectangle1,view,view1,text,text1);
        this.setPane(game_pane);

        Scene scene1 = new Scene(game_pane, 500, 650);
        super.setScene(scene1);
        super.getStage().setScene(scene1);
        super.getStage().setResizable(false);
        super.getStage().show();

        EndScreen endScreen = new EndScreen(super.getStage(),new Hero(),this,this.image,storage);

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bt.setVisible(false);
                text1.setVisible(false);
                text.setVisible(false);
                view1.setVisible(false);
                view.setVisible(false);
                rectangle1.setVisible(false);
                endScreen.endGame(game_pane,scene1);
            }
        });

        this.playGame(rand_posX + rand_width - 100);
    }

    public void playGame(int next_pillar_centre){
        Stick stick = new Stick(3, 95, 480);
        Rectangle rectangle4 = stick.generateStick();

        Pane pane = this.getPane();
        pane.getChildren().addAll(rectangle4);

        ImageView view = this.hero.getView();

        Timeline timeline = new Timeline();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                stick.extendLength();
            }
        };
        AnimationTimer timer1 = new AnimationTimer() {
            int deg = 0;
            boolean flag = false;
            @Override
            public void handle(long l) {
                if(flag == false) {
                    rectangle4.getTransforms().add(new Rotate(1, 3, stick.getHeight()));
                    deg++;
                    if (deg == 90) {
                        stop();
                        flag = true;
                        Image image1 = new Image(this.getClass().getResourceAsStream("Standing_Hero.png"));
                        Image image2 = new Image(this.getClass().getResourceAsStream("Side_On.png"));
                        for(int i = 0; i < stick.getHeight() + 150; i++){
                            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + i*10), new KeyValue (view.translateXProperty(), 1 + i*1)));
                            if(i%2==0){
                                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5 + i*10), new KeyValue(view.imageProperty(),image1)));
                            }
                            else{
                                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5 + i*10), new KeyValue(view.imageProperty(),image2)));
                            }
                        }
                        timeline.play();
                    }
                }
            }
        };

        this.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
            boolean flag = false;
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!flag){
                    timer.start();
                    flag = true;
                }
            }
        });

        this.getScene().setOnMouseReleased(e -> {
            timer.stop();
            timer1.start();
        });
    }
}