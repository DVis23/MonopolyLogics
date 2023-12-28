package ru.vsu.cs.cells;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.List;

public class RailRoad extends Cell {
    private String name;
    private int cost;
    private int income;
    private Player owner;

    @JsonCreator
    public RailRoad(@JsonProperty("cell") int index, @JsonProperty("name")String name,
                    @JsonProperty("cost") int cost) {
        setIndex(index);
        this.name = name;
        this.cost = cost;
        income = cost/4;
        owner = null;
    }

    @Override
    public void action(Player player, PlayingField playingField, List<Player> players) {
        if (player.getSkipping() == 0) {
            if (owner != null) {
                player.setLiberalValues(player.getLiberalValues() - income);
                owner.setLiberalValues(owner.getLiberalValues() + income);
            }
        }
    }

    public void deleteOwner(Player player, PlayingField playingField) {
        owner = null;
        income = cost/4;
        int numberRailRoad = numberRailRoad(player, playingField);
        List<RailRoad> railRoads = playingField.getAllRailRoads();
        for (RailRoad railRoad : railRoads) {
            if (railRoad.getOwner() != null && railRoad.getOwner().equals(player)) railRoad.setIncome(railRoad.getCost()
                    / 4 + railRoad.getCost() * numberRailRoad / 8);
        }
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
        for (RailRoad railRoad : railRoads) {
            if (railRoad.getOwner() != null && railRoad.getOwner().equals(player)) cost++;
        }
        return cost;
    }

    private void buyRailRoad(Player player, PlayingField playingField) {
        owner = player;
        player.setLiberalValues(player.getLiberalValues() - cost);
        int numberRailRoad = numberRailRoad(player, playingField);
        List<RailRoad> railRoads = playingField.getAllRailRoads();
        for (RailRoad railRoad : railRoads) {
            if (railRoad.getOwner() != null && railRoad.getOwner().equals(player)) railRoad.setIncome(railRoad.getCost()
                    / 4 + railRoad.getCost() * numberRailRoad / 8);

        }
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getCost() {return cost;}
    public void setCost(int cost) {this.cost = cost;}
    public int getIncome() {return income;}
    public void setIncome(int income) {
        this.income = income;
    }
}
