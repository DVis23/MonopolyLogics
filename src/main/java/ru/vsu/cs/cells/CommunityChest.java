package ru.vsu.cs.cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.ArrayList;
import java.util.Random;

public class CommunityChest implements Cell {
    private int i;

    @Override
    public void action(Player player, PlayingField playingField, ArrayList<Player> players) {
        Random random = new Random();
        i = random.nextInt(5 - 1) + 1;
        if (i == 1) {
            communityChest1(player);
        } else if (i == 2) {
            communityChest2(player);
        } else if (i == 3) {
            communityChest3(player, players);
        }  else {
            communityChest4(player, players);
        }
    }
    private void communityChest1(Player player) {
        player.setLiberalValues(player.getLiberalValues() - 2500);
    }
    private void communityChest2(Player player) {
        player.setLiberalValues(player.getLiberalValues() + 1000);
    }
    private void communityChest3(Player player, ArrayList<Player> players) {
        player.setLiberalValues(player.getLiberalValues() + 3000);
    }
    private void communityChest4(Player player, ArrayList<Player> players) {
        player.setLiberalValues(player.getLiberalValues() - (players.size()-1)*1000);
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != player) players.get(i).setLiberalValues(players.get(i).getLiberalValues() + 1000);
        }
    }

    public int getI() {return i;}
}
