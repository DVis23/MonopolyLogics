package ru.vsu.cs.cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.ArrayList;

public class GoToJail extends Cell {

    @Override
    public void action(Player player, PlayingField playingField, ArrayList<Player> players) {
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
