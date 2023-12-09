/*
 * THIS CLASS IS USED TO STORE THE CURRENT GAME PROGRESS SO THAT IT CAN BE LOADED LATER.
 * */

package com.stickhero.stickhero;

import java.io.Serializable;

public class HistoryUnit implements Serializable {
    private int score;
    private int curr_pillar_width;
    private String background;
    private int next_pillar_width;
    private int next_pillar_start;
    private int n_cherries;
    public HistoryUnit(int score, int curr_pillar_width, int next_pillar_width, int next_pillar_start, String background, int n_cherries){
        this.background = background;
        this.score = score;
        this.curr_pillar_width = curr_pillar_width;
        this.next_pillar_start = next_pillar_start;
        this.next_pillar_width = next_pillar_width;
        this.n_cherries = n_cherries;
    }

    public void setN_cherries(int n_cherries) {
        this.n_cherries = n_cherries;
    }

    public int getN_cherries() {
        return n_cherries;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurr_pillar_width() {
        return curr_pillar_width;
    }

    public void setCurr_pillar_width(int curr_pillar_width) {
        this.curr_pillar_width = curr_pillar_width;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getNext_pillar_width() {
        return next_pillar_width;
    }

    public void setNext_pillar_width(int next_pillar_width) {
        this.next_pillar_width = next_pillar_width;
    }

    public int getNext_pillar_start() {
        return next_pillar_start;
    }

    public void setNext_pillar_start(int next_pillar_start) {
        this.next_pillar_start = next_pillar_start;
    }
}
