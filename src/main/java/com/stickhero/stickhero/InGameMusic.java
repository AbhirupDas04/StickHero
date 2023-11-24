package com.stickhero.stickhero;

//public class InGameMusic extends MusicHandler {
//}
import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
public class InGameMusic extends Application
{
    public void start (Stage primaryStage) throws Exception {
        String musicPath = "src/main/resources/com/stickhero/stickhero/life-of-a-wandering-wizard-15549.mp3";

        Media media = new Media(new File(musicPath).toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setAutoPlay(true);
        primaryStage.setTitle("Playing Audio");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}