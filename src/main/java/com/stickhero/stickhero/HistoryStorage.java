package com.stickhero.stickhero;

import java.util.ArrayList;

public class HistoryStorage {
    private static int n_entries = 0;
    private ArrayList<HistoryUnit> historyUnits;
    public void addUnit(HistoryUnit unit){}

    public static int getN_entries() {
        return n_entries;
    }

    public static void setN_entries(int n_entries) {
        HistoryStorage.n_entries = n_entries;
    }

    public ArrayList<HistoryUnit> getHistoryUnits() {
        return historyUnits;
    }

    public void setHistoryUnits(ArrayList<HistoryUnit> historyUnits) {
        this.historyUnits = historyUnits;
    }
}
