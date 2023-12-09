/*
* THIS CLASS IS USED TO STORE THE HISTORY UNITS WHICH ARE USED IN SAVING GAMES.
* */

package com.stickhero.stickhero;

import java.util.ArrayList;

public class HistoryStorage {
    private ArrayList<HistoryUnit> historyUnits;

    public void addUnit(HistoryUnit unit){}

    public ArrayList<HistoryUnit> getHistoryUnits() {
        return historyUnits;
    }

    public void setHistoryUnits(ArrayList<HistoryUnit> historyUnits) {
        this.historyUnits = historyUnits;
    }
}
