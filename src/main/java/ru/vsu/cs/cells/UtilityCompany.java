package ru.vsu.cs.cells;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.List;

public class UtilityCompany extends Cell {
    private String name;
    private int cost;
    private int income;
    private Player owner;
    private int type;

    @JsonCreator
    public UtilityCompany(@JsonProperty("name") String name, @JsonProperty("cost") int cost,
                          @JsonProperty("cell") int index, @JsonProperty("type") int type) {
        setIndex(index);
        this.cost = cost;
        income = cost/4;
        this.name = name;
        this.type = type;
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
        int numberUtilityCompany = numberUtilityCompany(player, playingField);
        List<UtilityCompany> utilityCompanies = playingField.getUtilityCompanies();
        for (UtilityCompany utilityCompany : utilityCompanies) {
            if (utilityCompany.getOwner() != null && utilityCompany.getOwner().equals(player)) {
                utilityCompany.setIncome(utilityCompany.getCost() / 4 +
                        utilityCompany.getCost() * numberUtilityCompany / 4);
            }
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void action2(Player player, PlayingField playingField) {
        if (owner == null) buyUtilityCompany(player, playingField);
    }

    private void buyUtilityCompany(Player player, PlayingField playingField) {
        owner = player;
        player.setLiberalValues(player.getLiberalValues() - 1200);
        int numberUtilityCompany = numberUtilityCompany(player, playingField);
        List<UtilityCompany> utilityCompanies = playingField.getUtilityCompanies();
        for (UtilityCompany utilityCompany : utilityCompanies) {
            if (utilityCompany.getOwner() != null && utilityCompany.getOwner().equals(player)) {
                utilityCompany.setIncome(utilityCompany.getCost() / 4 +
                        utilityCompany.getCost() * numberUtilityCompany / 4);
            }
        }
    }

    private int numberUtilityCompany(Player player, PlayingField playingField) {
        List<UtilityCompany> utilityCompanies = playingField.getUtilityCompanies();
        int cost = 0;
        for (UtilityCompany utilityCompany : utilityCompanies) {
            if (utilityCompany.getOwner() != null && utilityCompany.getOwner().equals(player)) cost++;
        }
        return cost;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {this.cost = cost;}
    public int getIncome() {
        return income;
    }
    public void setIncome(int income) {this.income = income;}
    public int getType() {return type;}
    public void setType(int type) {this.type = type;}
}
