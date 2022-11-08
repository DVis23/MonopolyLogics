package ru.vsu.cs.cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.ArrayList;
import java.util.Random;

public class Chance implements Cell {
    private int i;

    @Override
    public void action(Player player, PlayingField playingField, ArrayList<Player> players) {
        Random random = new Random();
        i = random.nextInt(5 - 1) + 1;
        if (i == 1) {
            chance1(player);
        } else if (i == 2) {
            chance2(player);
        } else if (i == 3) {
            chance3(player, players);
        }  else {
            chance4(player, players);
        }
    }


    private void chance1(Player player) {
        player.setLiberalValues(player.getLiberalValues() - 2000);
    }
    private void chance2(Player player) {
        player.setLiberalValues(player.getLiberalValues() + 5000);
    }
    private void chance3(Player player, ArrayList<Player> players) {
        player.setLiberalValues(player.getLiberalValues() + (players.size()-1)*500);
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != player) players.get(i).setLiberalValues(players.get(i).getLiberalValues() - 500);
        }
    }
    private void chance4(Player player, ArrayList<Player> players) {
        player.setLiberalValues(player.getLiberalValues() - (players.size()-1)*500);
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != player) players.get(i).setLiberalValues(players.get(i).getLiberalValues() + 500);
        }
    }

    public int getI() {return i;}
}
