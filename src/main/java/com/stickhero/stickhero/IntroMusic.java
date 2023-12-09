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
public class IntroMusic extends MusicHandler
{
    private MediaPlayer mediaPlayer;

    public void setMediaPlayer(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }

    public void start (Stage primaryStage) {
        Media media = new Media(getClass().getResource("entry_page_music.mp3").toString());

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