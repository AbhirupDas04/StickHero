package com.stickhero.stickhero;

import javafx.scene.image.Image;

public class HistoryUnit {
    private int score;
    private int curr_pillar_width;
    private Image background;
    private int next_pillar_width;
    private int gap_bw_pillars;
    public HistoryUnit(int score, int curr_pillar_width, int next_pillar_width, int gap_bw_pillars, Image background){
        this.background = background;
        this.score = score;
        this.curr_pillar_width = curr_pillar_width;
        this.gap_bw_pillars = gap_bw_pillars;
        this.next_pillar_width = next_pillar_width;
    }
}
