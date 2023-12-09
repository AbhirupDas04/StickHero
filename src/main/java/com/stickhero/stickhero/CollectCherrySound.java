/*
 * THIS CLASS HANDLES THE WORKING OF CHERRY COLLECTION MUSIC
 * */


package com.stickhero.stickhero;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.MediaView;
public class CollectCherrySound extends MusicHandler
{
    private MediaPlayer mediaPlayer;

    public void setMediaPlayer(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }

    public void start (Stage primaryStage) {
        Media media = new Media(getClass().getResource("cherry_collection_sound.mp3").toString());

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