/*
 * THIS CLASS HANDLES THE WORKING OF COLLECTING CHERRIES MUSIC
 * */


package com.stickhero.stickhero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RewardMusic extends MusicHandler
{
    private MediaPlayer mediaPlayer;

    public void setMediaPlayer(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }

    public void start (Stage primaryStage) {
        Media media = new Media(getClass().getResource("reward_music.mp3").toString());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        primaryStage.show();

        mediaPlayer.play();
    }
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }


}