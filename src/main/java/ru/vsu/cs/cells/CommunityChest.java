package ru.vsu.cs.cells;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.List;
import java.util.Random;

public class CommunityChest extends Cell {
    private int i;

    @JsonCreator
    public CommunityChest(@JsonProperty("cell") int index) {
        setIndex(index);
    }

    @Override
    public void action(Player player, PlayingField playingField, List<Player> players) {
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
    private void communityChest3(Player player, List<Player> players) {
        player.setLiberalValues(player.getLiberalValues() + 3000);
    }
    private void communityChest4(Player player, List<Player> players) {
        player.setLiberalValues(player.getLiberalValues() - (players.size()-1)*1000);
        for (Player value : players) {
            if (value != player) value.setLiberalValues(value.getLiberalValues() + 1000);
        }
    }

    public int getI() {return i;}
}
