package ru.vsu.cs.cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.ArrayList;
import java.util.List;

public class UtilityCompany extends Cell {
    private final String name;
    private final int cost;
    private int income;
    private Player owner;

    public UtilityCompany(String name) {
        cost = 1500;
        income = cost/4;
        this.name = name;
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
        if (owner == null) buyUtilityCompany(player, playingField);
    }

    private void buyUtilityCompany(Player player, PlayingField playingField) {
        owner = player;
        player.setLiberalValues(player.getLiberalValues() - 1200);

        List<UtilityCompany> utilityCompanies = playingField.getUtilityCompanies();
        for (UtilityCompany utilityCompany : utilityCompanies) {
            if (utilityCompany.getOwner() != null && utilityCompany.getOwner().equals(player)) {
                utilityCompany.setIncome(utilityCompany.getCost() / 4 +
                        utilityCompany.getCost() * numberUtilityCompany(player, playingField) / 4);
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

    public int getCost() {
        return cost;
    }
    public int getIncome() {
        return income;
    }
    public void setIncome(int income) {this.income = income;}
}
