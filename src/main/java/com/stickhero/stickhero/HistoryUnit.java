/*
 * THIS CLASS IS USED TO STORE THE CURRENT GAME PROGRESS SO THAT IT CAN BE LOADED LATER.
 * */

package com.stickhero.stickhero;

import javafx.scene.image.Image;

import java.io.Serializable;

public class HistoryUnit implements Serializable {
    private int score;
    private int curr_pillar_width;
    private String background;
    private int next_pillar_width;
    private int gap_bw_pillars;
    public HistoryUnit(int score, int curr_pillar_width, int next_pillar_width, int gap_bw_pillars, String background){
        this.background = background;
        this.score = score;
        this.curr_pillar_width = curr_pillar_width;
        this.gap_bw_pillars = gap_bw_pillars;
        this.next_pillar_width = next_pillar_width;
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

    public int getGap_bw_pillars() {
        return gap_bw_pillars;
    }

    public void setGap_bw_pillars(int gap_bw_pillars) {
        this.gap_bw_pillars = gap_bw_pillars;
    }
}
