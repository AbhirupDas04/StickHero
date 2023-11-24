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
import javafx.util.Duration;
public class EndingMusic extends MusicHandler
{
    private MediaPlayer mediaPlayer;
    public void start (Stage primaryStage) throws Exception {
        String musicPath = "src/main/resources/com/stickhero/stickhero/end_screen_music.mp3";

        Media media = new Media(new File(musicPath).toURI().toString());

        mediaPlayer = new MediaPlayer(media);

//        MediaView mediaView = new MediaView(mediaPlayer);
//        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setAutoPlay(true);
//        primaryStage.setTitle("Playing Audio");
        primaryStage.show();
        mediaPlayer.setOnEndOfMedia(() -> {
            // Repeat the music when it reaches the end
            mediaPlayer.seek(Duration.ZERO);
        });

        mediaPlayer.play();
    }
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

//    public static void main(String[] args) {
//        launch(args);
//    }

}