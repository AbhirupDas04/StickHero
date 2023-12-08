package com.stickhero.stickhero;

import javafx.animation.*;
import javafx.application.Platform;
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

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends BackgroundHandler {
    private Hero hero;
    private int gamespeed;
    private int n_cherries;
    private Image image;
    private String image_link;
    private HistoryStorage storage;
    private int score;
    private int level;
    private Rectangle pillar1_rect;
    private Rectangle pillar2_rect;
    private Pillar pillar1;
    private Pillar pillar2;
    private int curr_pillar_width;
    private ImageView view;
    private Rectangle first_red_bar;
    private Rectangle old_stick;
    private InGameMusic inGameMusic;
    private Thread thread;
    private boolean Thread_flag = false;
    private boolean game_over_flag = false;

    private ImageView collectableCherryView = null;
    private int isCherryGenerated = 0;

    Random random = new Random();

    private Text text_score;
    private Text text_rewards;
    private ImageView cherry_pic;
    private Rectangle score_background;
    private Button save_button;

    public GameScreen(Stage stage, HistoryStorage storage){
        super(stage);
        this.hero = new Hero();
        this.selectRandomImage();
        this.gamespeed = 1;
        this.score = 0;
        this.n_cherries = 0;
        this.storage = storage;
        this.level = 0;
        this.inGameMusic = new InGameMusic();
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
        this.image_link = randomImage;
    }

    public ImageView generateCollectableCherry(int curr_pillar_width , int next_pillar_start){      //generates cherry if random flag is 1 else no
        //binary random generated number to decide if cherry will be generated or not(put his in play game)
        //generate cherry in between two pillars, so input variable gap everytime
//        int isCherryGenerated = random.nextInt(0,2);

//        if (isCherryGenerated == 1){
//            Reward collectableCherry = new Reward();
//            int x_pos = random.nextInt(curr_pillar_width , next_pillar_start);
//            collectableCherryView = collectableCherry.generateReward(30 , 30 , x_pos , 490);
//            return collectableCherryView;
//        }
//        else{
//            return null;
//        }
        Reward collectableCherry = new Reward();
        int x_pos = random.nextInt(curr_pillar_width , next_pillar_start);
        collectableCherryView = collectableCherry.generateReward(30 , 30 , x_pos , 490);
        isCherryGenerated = 1;
        return collectableCherryView;
    }
    private void removeCherry(Pane pane) {          //function to remove cherry
        if (collectableCherryView != null) {
            isCherryGenerated = 0;
            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), collectableCherryView);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> pane.getChildren().remove(collectableCherryView));
            fadeOut.play();
        }

    }

    private Timeline animateFallingHeroAfterHit(int distance, double duration) {
        Timeline timeline = new Timeline();

        double rotation = view.getRotate();

        Image heroImage = new Image(getClass().getResourceAsStream("Standing_Hero.png"));
        Image sideOnImage = new Image(getClass().getResourceAsStream("Side_On.png"));

        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO,
                new KeyValue(view.imageProperty(), heroImage),
                new KeyValue(view.rotateProperty(), rotation)));

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(duration),
                new KeyValue(view.translateYProperty(), distance * 3),
                new KeyValue(view.imageProperty(), sideOnImage),
                new KeyValue(view.rotateProperty(), rotation + 360)));

        timeline.play();
        return timeline;
    }

    private Timeline animateHeroFallingAfterMiss(int distance, double duration) {
        Timeline timeline = new Timeline();

        double rotation = view.getRotate();

        Image heroImage = new Image(getClass().getResourceAsStream("Standing_Hero.png"));
        Image sideOnImage = new Image(getClass().getResourceAsStream("Side_On.png"));

        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO,
                new KeyValue(view.imageProperty(), heroImage),
                new KeyValue(view.rotateProperty(), rotation)));

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(duration),
                new KeyValue(view.translateYProperty(), distance * 3),
                new KeyValue(view.imageProperty(), sideOnImage),
                new KeyValue(view.rotateProperty(), rotation - 360)));
//                new KeyValue(view.translateXProperty(), distance)));
        timeline.play();
        return timeline;
    }


    public void startGame(HomeScreen home_screen, int mode){
        try {
            inGameMusic.start(super.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        this.score_background = rectangle1;

        Text text = new Text();
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 36));
        text.setFill(Color.WHITE);
        text.setLayoutX(239);
        text.setLayoutY(84);
        text.setText("0");
        this.text_score = text;

        Reward cherry = new Reward();
        ImageView view1 = cherry.generateReward(30,30,467,35);
        this.cherry_pic = view1;

        Text text1 = new Text();
        text1.setFont(Font.font("verdana", FontPosture.REGULAR, 16));
        text1.setFill(Color.WHITE);
        text1.setLayoutX(450);
        text1.setLayoutY(56);
        text1.setText("0");
        this.text_rewards = text1;

        Button bt = new Button("SAVE");
        bt.setMinSize(50,30);
        bt.setMaxSize(100,60);
        bt.setLayoutX(25);
        bt.setLayoutY(25);
        bt.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-border-radius: 150;-fx-font-size:15;-fx-text-fill:white");
        this.save_button = bt;

        game_pane.getChildren().addAll(rectangle,bt,rectangle2,rect3,rectangle1,view,view1,text,text1);
        this.setPane(game_pane);

        Scene scene1 = new Scene(game_pane, 500, 650);
        super.setScene(scene1);
        super.getStage().setScene(scene1);
        super.getStage().setResizable(false);
        super.getStage().show();

        EndScreen endScreen = new EndScreen(super.getStage(),new Hero(),this,this.image,storage);

        this.playGame(rand_posX,rand_width,rect3, home_screen, mode);
    }

    public void playGame(int next_pillar_start,int next_pillar_width, Rectangle red_bar, HomeScreen home_screen, int mode){
        if(mode == 1){
            score = 0;
        }
        GameScreen game = this;
        game_over_flag = false;
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

        collectableCherryView = generateCollectableCherry(curr_pillar_width , next_pillar_start - 10);

        if (isCherryGenerated != 0){
            pane.getChildren().addAll(rectangle2, rectangle4, rect3, collectableCherryView);
        }
        else{
            collectableCherryView = null;
            pane.getChildren().addAll(rectangle2, rectangle4, rect3);
        }

//        pane.getChildren().addAll(rectangle2, rectangle4, rect3);

        Timeline timeline = new Timeline();
        Timeline end_timeline = new Timeline();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                stick.extendLength();
            }
        };

        Scene scene2 = super.getScene();
        Stage stage = super.getStage();
        AnimationTimer timer1 = new AnimationTimer() {
            int deg = 0;
            boolean flag = false;
            AnimationTimer timer2 = this;

            @Override
            public void handle(long l) {
                if (flag == false) {
                    if (deg == 90) {
                        stop();
                        fn();
                    }
                    else{
                        rectangle4.getTransforms().add(new Rotate(1, 3, stick.getHeight()));
                        deg++;
                        if(deg == 89){
                            rectangle4.getTransforms().add(new Rotate(1, 3, stick.getHeight()));
                            deg++;
                        }
                    }
                }
            }

            public void fn(){
                end_timeline.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Thread_flag = true;
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
                        view.setLayoutX(pillar1.getWidth() - 44);
                        old_stick = rectangle4;

                        collectableCherryView = generateCollectableCherry(curr_pillar_width , next_pillar_start);
                        if (isCherryGenerated == 1){
                            collectableCherryView.setTranslateX(0);
                            collectableCherryView.setLayoutX(gap + 30 + pillar1.getWidth());
                        }

                        game.playGame(gap + 30 + pillar1.getWidth(),pillar3.getWidth(),rect3,home_screen,0);
                    }
                });
                flag = true;
                Image image1 = new Image(this.getClass().getResourceAsStream("Standing_Hero.png"));
                Image image2 = new Image(this.getClass().getResourceAsStream("Side_On.png"));

                int distance;

                if(stick.getHeight() < pillar2.getX_pos() - pillar1.getWidth() - pillar1.getX_pos() || stick.getHeight() > pillar2.getX_pos() -  pillar1.getX_pos() ){
                    Thread_flag = true;
                    distance =  stick.getHeight() + 30;
                    for (int i = 0; i < distance; i++) {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + i * 10), new KeyValue(view.translateXProperty(), 1 + i * 1)));
                        if (i % 20 == 0) {
                            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5 + i * 10), new KeyValue(view.imageProperty(), image1)));
                        } else if (i % 20 == 10) {
                            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5 + i * 10), new KeyValue(view.imageProperty(), image2)));
                        }
                    }

                    timeline.play();
                    timeline.setOnFinished(actionEvent -> {
                        Timeline timeline1 = animateHeroFallingAfterMiss(distance , 7);
                        timeline1.setOnFinished(actionEvent2 -> {
                            inGameMusic.stop();
                            text_rewards.setVisible(false);
                            text_score.setVisible(false);
                            cherry_pic.setVisible(false);
                            view.setVisible(false);
                            score_background.setVisible(false);
                            save_button.setVisible(false);

                            game_over_flag = true;

                            EndScreen endScreen = new EndScreen(stage,new Hero(),game,game.image,storage);
                            endScreen.endGame(pane,scene2,home_screen,score);
                        });
                    });
                }

                else{
                    score++;
                    text_score.setText(((Integer)score).toString());
                    distance =  (pillar2.getX_pos() + pillar2.getWidth() - 45 - (int)view.getLayoutX());
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
                        end_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(pillar2_rect.translateXProperty(), -(1 + (j - distance)))));
                        end_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(pillar1_rect.translateXProperty(), -(1 + (j - distance)))));
                        end_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(rectangle4.translateXProperty(), -(1 + (j - distance)))));
                        end_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(red_bar.translateXProperty(), -(1 + (j - distance)))));
                        end_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(view.translateXProperty(), temp_cur)));
                        if(first_red_bar != null){
                            end_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(first_red_bar.translateXProperty(), -(1 + (j - distance)))));
                        }
                        if(old_stick != null){
                            old_stick.setVisible(false);
                        }
                        if (collectableCherryView != null) {
                            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + j * 10), new KeyValue(collectableCherryView.translateXProperty(),-(1 + (j - distance)) )));
                        }

                        temp_cur -= 1;
                    }
                    for (int k = distance; k < distance + pillar3.getX_pos() - (pillar2.getWidth() + gap + 30); k++) {
                        end_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + k * 10), new KeyValue(rectangle2.translateXProperty(), -(1 + (k - distance)))));
                        end_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + k * 10), new KeyValue(rect3.translateXProperty(), -(1 + (k - distance)))));
                        if (collectableCherryView != null) {
                            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10 + k * 10), new KeyValue(collectableCherryView.translateXProperty(), -(1 + (k - distance)))));
                        }
                    }

                    class Test extends Thread{
                        @Override
                        public void run() {
                            while(true){
                                if(Thread_flag){
                                    Thread_flag = false;
                                    break;
                                }
                                if(timeline.getStatus() == Animation.Status.RUNNING){
                                    if(view.getTranslateX() >= (pillar2.getX_pos() - pillar1.getX_pos() + 10 - pillar1.getWidth()) && hero.isUpsideDown()){
                                        timeline.stop();
                                        end_timeline.stop();
                                        Platform.runLater(() -> {
                                            Timeline timeline1 = animateFallingHeroAfterHit(distance , 7);
                                            timeline1.setOnFinished(actionEvent -> {
                                                inGameMusic.stop();
                                                text_rewards.setVisible(false);
                                                text_score.setVisible(false);
                                                cherry_pic.setVisible(false);
                                                view.setVisible(false);
                                                score_background.setVisible(false);
                                                save_button.setVisible(false);

                                                game_over_flag = true;

                                                EndScreen endScreen = new EndScreen(stage,new Hero(),game,game.image,storage);
                                                endScreen.endGame(pane,scene2,home_screen,score);
                                            });
                                        });
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    Test t = new Test();
                    thread = new Thread(t);
                    thread.start();

                    timeline.play();
                    end_timeline.play();
                }
            }
        };

        this.getScene().setOnMousePressed(new EventHandler<>() {
            boolean flag = false;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!flag && timeline.getStatus() == Animation.Status.STOPPED && !game_over_flag && end_timeline.getStatus() == Animation.Status.STOPPED) {
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
                if(hero.isUpsideDown()){
                    hero.setUpsideDown(false);
                }
                else{
                    hero.setUpsideDown(true);
                }
            }
        });

        int finalRand_posX = rand_posX;
        this.save_button.setOnAction(actionEvent -> {
            BufferedReader fileInputStream;
            try {
                fileInputStream = new BufferedReader(new FileReader("Game_Details.txt"));
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

            ArrayList<HistoryUnit> list;

            if(n_entries == 0){
                list = new ArrayList<>();
            }
            else{
                ObjectInputStream in;
                try {
                    in = new ObjectInputStream(new FileInputStream("Game_Records.txt"));
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

            HistoryUnit historyUnit = new HistoryUnit(score, pillar1.getWidth(), pillar2.getWidth(), finalRand_posX, image_link);
            list.add(historyUnit);

            ObjectOutputStream outputStream = null;
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream("Game_Records.txt"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                outputStream.writeObject(list);
                n_entries+=1;

                FileOutputStream fileOutputStream;
                fileOutputStream = new FileOutputStream("Game_Details.txt");
                Integer i1 = n_entries;

                fileOutputStream.write(i1.toString().getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}