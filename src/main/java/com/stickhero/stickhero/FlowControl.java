package com.stickhero.stickhero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

public class FlowControl extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HistoryStorage storage = new HistoryStorage();
        GameScreen game = new GameScreen(primaryStage,storage);
        HomeScreen home = new HomeScreen(primaryStage,game, storage);

        home.openHome();

        ObjectInputStream in = null;

//        try {
//            in = new ObjectInputStream(new FileInputStream("Game_Records.txt"));
//            if(in == null){
//                System.out.println("fail");
//            }
//            else{
//                HistoryUnit unit;
//                while((unit = (HistoryUnit) in.readObject()) != null){
//                    try {
//                        unit = (HistoryUnit) in.readObject();
//                    } catch (IOException | ClassNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    System.out.println(unit.getCurr_pillar_width());
//                }
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}
