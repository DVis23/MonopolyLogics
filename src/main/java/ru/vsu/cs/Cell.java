package ru.vsu.cs;

import java.util.List;

public abstract class Cell {
    private int index;

    public Cell() {
    }

    public void action(Player player, PlayingField playingField, List<Player> players){
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
