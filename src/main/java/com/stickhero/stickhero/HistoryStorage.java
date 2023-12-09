/*
 * THIS CLASS IS USED TO STORE THE HISTORY UNITS WHICH ARE USED IN SAVING GAMES.
 * */

package com.stickhero.stickhero;

import javafx.scene.shape.Rectangle;

public class HistoryStorage {
    private static int next_pillar_start;
    private static int next_pillar_width;
    private static Rectangle red_bar;
    private static HomeScreen homeScreen;
    private static int mode;
    private static int reload_score;
    private static int curr_pillar_width;
    private static int n_cherries;
    private static String background;
    public static void StoreData(int curr_pillar_width,int next_pillar_start, int next_pillar_width, Rectangle red_bar, HomeScreen home_screen, int mode, int reload_score, int n_cherries, String Background){
        HistoryStorage.n_cherries = n_cherries;
        HistoryStorage.background = Background;
        HistoryStorage.curr_pillar_width = curr_pillar_width;
        homeScreen = home_screen;
        HistoryStorage.mode = mode;
        HistoryStorage.red_bar = red_bar;
        HistoryStorage.next_pillar_start = next_pillar_start;
        HistoryStorage.next_pillar_width = next_pillar_width;
        HistoryStorage.reload_score = reload_score;
    }

    public static int getNext_pillar_start() {
        return next_pillar_start;
    }

    public static void setNext_pillar_start(int next_pillar_start) {
        HistoryStorage.next_pillar_start = next_pillar_start;
    }

    public static int getNext_pillar_width() {
        return next_pillar_width;
    }

    public static int getCurr_pillar_width() {
        return curr_pillar_width;
    }

    public static void setCurr_pillar_width(int curr_pillar_width) {
        HistoryStorage.curr_pillar_width = curr_pillar_width;
    }

    public static void setNext_pillar_width(int next_pillar_width) {
        HistoryStorage.next_pillar_width = next_pillar_width;
    }

    public static Rectangle getRed_bar() {
        return red_bar;
    }

    public static void setRed_bar(Rectangle red_bar) {
        HistoryStorage.red_bar = red_bar;
    }

    public static HomeScreen getHomeScreen() {
        return homeScreen;
    }

    public static void setHomeScreen(HomeScreen homeScreen) {
        HistoryStorage.homeScreen = homeScreen;
    }

    public static int getMode() {
        return mode;
    }

    public static void setMode(int mode) {
        HistoryStorage.mode = mode;
    }

    public static int getReload_score() {
        return reload_score;
    }

    public static void setReload_score(int reload_score) {
        HistoryStorage.reload_score = reload_score;
    }

    public static void setBackground(String background) {
        HistoryStorage.background = background;
    }

    public static int getN_cherries() {
        return n_cherries;
    }

    public static String getBackground() {
        return background;
    }

    public static void setN_cherries(int n_cherries) {
        HistoryStorage.n_cherries = n_cherries;
    }
}
