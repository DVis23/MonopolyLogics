package ru.vsu.cs.cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.ArrayList;
import java.util.List;

public class RailRoad implements Cell {
    private String name;
    private int cost;
    private int income;
    private Player owner;

    public RailRoad(String name) {
        this.name = name;
        cost = 1200;
        income = cost/4;
        owner = null;
    }

    @Override
    public void action(Player player, PlayingField playingField, ArrayList<Player> players) {
        if (player.getSkipping() == 0) {
            if (owner != null) {
                player.setLiberalValues(player.getLiberalValues() - income);
                owner.setLiberalValues(owner.getLiberalValues() + income);
            }
        }
    }

    public void deleteOwner() {
        owner = null;
        income = cost/4;
    }

    public Player getOwner() {
        return owner;
    }

    public void action2(Player player, PlayingField playingField) {
        if (owner == null) buyRailRoad(player, playingField);
    }

    private int numberRailRoad(Player player, PlayingField playingField) {
        List<RailRoad> railRoads = playingField.getAllRailRoads();
        int cost = 0;
        for (int i = 0; i < railRoads.size(); i++) {
            if(railRoads.get(i).getOwner() != null && railRoads.get(i).getOwner().equals(player)) cost++;
        }
        return cost;
    }

    private void buyRailRoad(Player player, PlayingField playingField) {
        owner = player;
        player.setLiberalValues(player.getLiberalValues() - cost);
        List<RailRoad> railRoads = playingField.getAllRailRoads();
        for (int i = 0; i < railRoads.size(); i++) {
            if(railRoads.get(i).getOwner() != null && railRoads.get(i).getOwner().equals(player)) {
                railRoads.get(i).setIncome(railRoads.get(i).getCost()/4 + railRoads.get(i).getCost()*numberRailRoad(player, playingField)/8);
            }
        }
    }

    public  String getName() {return name;}
    public int getCost() {return cost;}
    public int getIncome() {return income;}
    public void setIncome(int income) {
        this.income = income;
    }
}
