package ru.vsu.cs.cells;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.List;
import java.util.Random;

public class Chance extends Cell {
    private int i;

    @JsonCreator
    public Chance(@JsonProperty("cell") int index){
        setIndex(index);
    }

    @Override
    public void action(Player player, PlayingField playingField, List<Player> players) {
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
    private void chance3(Player player, List<Player> players) {
        player.setLiberalValues(player.getLiberalValues() + (players.size()-1)*500);
        for (Player value : players) {
            if (value != player) value.setLiberalValues(value.getLiberalValues() - 500);
        }
    }
    private void chance4(Player player, List<Player> players) {
        player.setLiberalValues(player.getLiberalValues() - (players.size()-1)*500);
        for (Player value : players) {
            if (value != player) value.setLiberalValues(value.getLiberalValues() + 500);
        }
    }

    public int getI() {return i;}
}
