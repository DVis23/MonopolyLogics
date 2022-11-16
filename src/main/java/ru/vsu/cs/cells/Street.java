package ru.vsu.cs.cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import java.util.ArrayList;
import java.util.List;

public class Street extends Cell {

    private String name;
    private Color color;
    private int numberHotel;
    private int cost;
    private int costHotel;
    private int income;
    private Player owner;

    public Street(String name, Color color,  int cost) {
        this.name = name;
        this.color = color;
        this.cost = cost;
        income = cost/4;
        costHotel = cost/2;
        numberHotel = 0;
        owner = null;
    }

    @Override
    public void action(Player player, PlayingField playingField, ArrayList<Player> players){
        if (player.getSkipping() == 0) {
            if (owner != null) {
                player.setLiberalValues(player.getLiberalValues() - income);
                owner.setLiberalValues(owner.getLiberalValues() + income);
            }
        }
    }

    public void deleteOwner(PlayingField playingField) {
        owner = null;
        List<Street> streets = playingField.getStreetTheSameColor(color);
        for (Street street : streets) {
            street.deleteHotel();
        }

    }
    public Player getOwner() {
        return owner;
    }

    public void action2(Player player, PlayingField playingField) {
        if (owner == null) buyStreet(player, playingField);
    }

    public boolean availabilityOfStreets(Player player, PlayingField playingField){
        List<Street> streets = playingField.getStreetTheSameColor(color);
        for (Street street : streets) {
            if (street.getOwner() == null || !player.equals(street.getOwner())) return false;
        }
        return true;
    }

    public void buyStreet(Player player, PlayingField playingField) {
        owner = player;
        player.setLiberalValues(player.getLiberalValues() - cost);
        if (availabilityOfStreets(player, playingField)) {
            List<Street> streets = playingField.getStreetTheSameColor(color);
            for (Street street : streets) {
                street.setIncome(street.getCost() / 2);
            }

        }
    }

    public void buyHotel(Player player, PlayingField playingField) {
        if (availabilityOfStreets(player, playingField)) {
            numberHotel++;
            owner.setLiberalValues(owner.getLiberalValues() - costHotel);
            income = income + cost / 8 * numberHotel;
        }
    }

    private void deleteHotel() {
        numberHotel = 0;
        income = cost/4;
    }

    public enum Color {
        BROWN,
        WHITE,
        ROSE,
        ORANGE,
        RED,
        YELLOW,
        GREEN,
        BLUE
    }

    public static Color colorFromString(String str){
        return switch (str) {
            case "BROWN" -> Color.BROWN;
            case "WHITE" -> Color.WHITE;
            case "ROSE" -> Color.ROSE;
            case "ORANGE" -> Color.ORANGE;
            case "RED" -> Color.RED;
            case "YELLOW" -> Color.YELLOW;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            default -> null;
        };
    }

    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
    public int getIncome() {
        return income;
    }
    public void setIncome(int income) {this.income = income;}
    public int getNumberHotel() {
        return numberHotel;
    }
    public int getCostHotel() {
        return costHotel;
    }
}
