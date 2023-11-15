package com.stickhero.stickhero;

import javafx.scene.image.Image;

public class HistoryUnit {
    private Score score;
    private int curr_pillar_width;
    private Image background;
    private int next_pillar_width;
    private int gap_bw_pillars;
    private int level;
    public HistoryUnit(Score score, int curr_pillar_width, int next_pillar_width, int gap_bw_pillars, Image background, int level){
        this.background = background;
        this.score = score;
        this.curr_pillar_width = curr_pillar_width;
        this.gap_bw_pillars = gap_bw_pillars;
        this.next_pillar_width = next_pillar_width;
        this.level = level;
    }
}
