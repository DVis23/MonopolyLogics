package ru.vsu.cs.cells;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.List;

public class FreeParking extends Cell {

    @JsonCreator
    public FreeParking(@JsonProperty("cell") int index) {
        setIndex(index);
    }

    @Override
    public void action(Player player, PlayingField playingField, List<Player> players) {
        //отдыхаем
    }

}
