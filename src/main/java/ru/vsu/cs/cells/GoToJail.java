package ru.vsu.cs.cells;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.List;

public class GoToJail extends Cell {

    @JsonCreator
    public GoToJail(@JsonProperty("cell") int index) {
        setIndex(index);
    }

    @Override
    public void action(Player player, PlayingField playingField, List<Player> players) {
        Cell [] cells = playingField.getCells();
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getClass() == Jail.class) {
                player.setStep(i);
                player.setSkipping(3);
                break;
            }
        }
    }

}
