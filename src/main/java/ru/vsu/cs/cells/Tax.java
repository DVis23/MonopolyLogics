package ru.vsu.cs.cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.ArrayList;

public class Tax extends Cell {

    @Override
    public void action(Player player, PlayingField playingField, ArrayList<Player> players) {
        player.setLiberalValues(player.getLiberalValues() - 500);
    }
}
