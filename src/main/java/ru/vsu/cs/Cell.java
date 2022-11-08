package ru.vsu.cs;

import java.util.ArrayList;

public interface Cell {
    void action(Player player, PlayingField playingField, ArrayList<Player> players);
}
