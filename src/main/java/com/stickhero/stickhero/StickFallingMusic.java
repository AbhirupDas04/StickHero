/*
 * THIS CLASS HANDLES THE WORKING OF STICK FALLING MUSIC
 * */


package com.stickhero.stickhero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StickFallingMusic extends MusicHandler
{
    private MediaPlayer mediaPlayer;

    public void setMediaPlayer(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }

    public void start (Stage primaryStage) {
        Media media = new Media(getClass().getResource("stick_falling_sound.mp3").toString());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        primaryStage.show();
//        mediaPlayer.setOnEndOfMedia(() -> {
//            mediaPlayer.seek(Duration.ZERO);
//        });

        mediaPlayer.play();
    }
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }


}