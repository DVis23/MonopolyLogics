package ru.vsu.cs.cells;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.List;

public class StartCell extends Cell {
    private final int prize;

    @JsonCreator
    public StartCell(@JsonProperty("cell") int index, @JsonProperty("cost") int prize) {
        setIndex(index);
        this.prize = prize;
    }

    @Override
    public void action(Player player, PlayingField playingField, List<Player> players){
        player.setLiberalValues(player.getLiberalValues() + prize);
    }

    public int getPrize() {
        return prize;
    }
}
