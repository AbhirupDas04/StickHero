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
import javafx.scene.transform.Translate;
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
    private Rectangle pillar1_rect;
    private Rectangle pillar2_rect;
    private Pillar pillar1;
    private Pillar pillar2;
    private int curr_pillar_width;
    private ImageView view;
    private Rectangle first_red_bar;
    private Rectangle old_stick;
    public GameScreen(Stage stage, HistoryStorage storage){
        super(stage);
        this.hero = new Hero();
        this.selectRandomImage();
        this.gamespeed = 1;
        this.score = new Score();
        this.n_cherries = 0;
        this.storage = storage;
        this.level = 0;
//        super.setMusic(new InGameMusic());
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
        this.pillar1_rect = rectangle;
        this.curr_pillar_width = 100;
        this.pillar1 = pillar;

        Random rand = new Random();
        int rand_width = rand.nextInt(70) + 30;
        int rand_posX = rand.nextInt(370 - rand_width) + 130;
        Pillar pillar2 = new Pillar(rand_width,160,rand_posX,490);
        Rectangle rectangle2 = pillar2.getRectangle();

        this.pillar2_rect = rectangle2;
        this.pillar2 = pillar2;

        Rectangle rect3 = new Rectangle(10, 5);
        rect3.setFill(Color.RED);
        rect3.setLayoutX(rand_posX + rand_width / 2.0 - 5);
        rect3.setLayoutY(490);

        view = hero.generateStandingHero(35,35,55,455);

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

        this.playGame(rand_posX,rand_width,rect3);
    }

    public void playGame(int next_pillar_start,int next_pillar_width, Rectangle red_bar){
        GameScreen game = this;
        Stick stick = new Stick(3, curr_pillar_width - 5, 490);
        Rectangle rectangle4 = stick.generateStick();

        Pane pane = this.getPane();

        Random rand = new Random();
        int rand_width = rand.nextInt(70) + 30;
        int rand_posX = rand.nextInt(370 - (next_pillar_width + 30)) + next_pillar_start + next_pillar_width + 30;
        int act_rand_posX = rand_posX;
        int gap = act_rand_posX - 30 - next_pillar_start - next_pillar_width;
        if (rand_posX <= 500) {
            rand_posX = 500;
        }
        Pillar pillar3 = new Pillar(rand_width, 160, rand_posX, 490);
        Rectangle rectangle2 = pillar3.getRectangle();

        Rectangle rect3 = new Rectangle(10, 5);
        rect3.setFill(Color.RED);
        rect3.setLayoutX(rand_posX + rand_width / 2.0 - 5);
        rect3.setLayoutY(490);

        pane.getChildren().addAll(rectangle2, rectangle4, rect3);

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
                if (flag == false) {
                    rectangle4.getTransforms().add(new Rotate(1, 3, stick.getHeight()));
                    deg++;
                    if (deg == 90) {
                        stop();
                        fn();
                    }
                }
            }

            public void fn(){
                timeline.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        curr_pillar_width = next_pillar_width;
                        pillar1 = pillar2;
                        pillar1_rect = pillar2_rect;
                        pillar1_rect.setTranslateX(0);
                        pillar1_rect.setLayoutX(0);
                        pillar1.setX_pos(0);
                        pillar2 = pillar3;
                        pillar2_rect = rectangle2;
                        pillar2_rect.setTranslateX(0);
                        pillar2_rect.setLayoutX(gap + 30 + pillar1.getWidth());
                        pillar2.setX_pos(gap + 30 + pillar1.getWidth());
                        rect3.setTranslateX(0);
                        rect3.setLayoutX(gap + 30 + pillar1.getWidth() + pillar3.getWidth()/2.0 - 5);
                        first_red_bar = red_bar;
                        first_red_bar.setTranslateX(0);
                        first_red_bar.setLayoutX(pillar1.getWidth()/2.0 - 5);
                        view.setTranslateX(0);
                        view.setLayoutX(pillar1.getWidth() - 43);
                        old_stick = rectangle4;
                        game.playGame(gap + 30 + pillar1.getWidth(),pillar3.getWidth(),rect3);
                    }
                });

                flag = true;
                Image image1 = new Image(this.getClass().getResourceAsStream("Standing_Hero.png"));
                Image image2 = new Image(this.getClass().getResourceAsStream("Side_On.png"));
                int distance =  (pillar2.getX_pos() + pillar2.getWidth() - 45 - (int)view.getLayoutX());
                for (int i = 0; i < distance; i++) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + i * 10), new KeyValue(view.translateXProperty(), 1 + i * 1)));
                    if (i % 20 == 0) {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5 + i * 10), new KeyValue(view.imageProperty(), image1)));
                    } else if (i % 20 == 10) {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5 + i * 10), new KeyValue(view.imageProperty(), image2)));
                    }
                }
                int temp_cur = distance;
                for (int j = distance; j < distance + pillar2.getX_pos(); j++) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(pillar2_rect.translateXProperty(), -(1 + (j - distance)))));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(pillar1_rect.translateXProperty(), -(1 + (j - distance)))));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(rectangle4.translateXProperty(), -(1 + (j - distance)))));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(red_bar.translateXProperty(), -(1 + (j - distance)))));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(view.translateXProperty(), temp_cur)));
                    if(first_red_bar != null){
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(first_red_bar.translateXProperty(), -(1 + (j - distance)))));
                    }
                    if(old_stick != null){
                        old_stick.setVisible(false);
                    }
                    temp_cur -= 1;
                }
                for (int k = distance; k < distance + pillar3.getX_pos() - (pillar2.getWidth() + gap + 30); k++) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + k * 10), new KeyValue(rectangle2.translateXProperty(), -(1 + (k - distance)))));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + k * 10), new KeyValue(rect3.translateXProperty(), -(1 + (k - distance)))));
                }
                timeline.play();
            }
        };

        this.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
            boolean flag = false;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!flag) {
                    timer.start();
                    flag = true;
                }
            }
        });

        this.getScene().setOnMouseReleased(e -> {
            timer.stop();
            timer1.start();
        });

        this.getScene().setOnMouseClicked(e -> {
            if(timeline.getStatus() == Animation.Status.RUNNING){
                Rotate flipRotation = new Rotate(180,0,35,0,Rotate.X_AXIS);
                view.getTransforms().addAll(flipRotation);
            }
        });
    }
}