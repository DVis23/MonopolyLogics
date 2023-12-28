package ru.vsu.cs.cells;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.List;

public class Tax extends Cell {
    private int cost;

    @JsonCreator
    public Tax(@JsonProperty("cell") int index, @JsonProperty("cost") int cost){
        setIndex(index);
        this.cost = cost;
    }

    @Override
    public void action(Player player, PlayingField playingField, List<Player> players) {
        player.setLiberalValues(player.getLiberalValues() - 500);
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {this.cost = cost;}
}
