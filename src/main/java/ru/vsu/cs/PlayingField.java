package ru.vsu.cs;

import ru.vsu.cs.cells.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayingField {

    private  Cell [] cells;
    private  HashMap<Street.Color, List<Street>> streetOfTheSameColor;
    private  List <RailRoad> railRoads;
    private  List <UtilityCompany> utilityCompanies;

    public PlayingField(Cell [] cells) {
        this.cells = cells;
        streetOfTheSameColor = defineStreet(cells);
        railRoads = defineRailRoad(cells);
        utilityCompanies = defineUtilityCompany(cells);
    }

    public PlayingField() {
    }

    private HashMap<Street.Color, List<Street>> defineStreet(Cell [] cells) {
        HashMap<Street.Color, List<Street>> streets = new HashMap<>() {{
            put(Street.Color.BROWN, new ArrayList<>());
            put(Street.Color.WHITE, new ArrayList<>());
            put(Street.Color.ROSE, new ArrayList<>());
            put(Street.Color.ORANGE, new ArrayList<>());
            put(Street.Color.RED, new ArrayList<>());
            put(Street.Color.YELLOW, new ArrayList<>());
            put(Street.Color.GREEN, new ArrayList<>());
            put(Street.Color.BLUE, new ArrayList<>());
        }};
        for (Cell cell : cells) {
            if (cell.getClass() == Street.class) {
                List<Street> list = streets.get(((Street) cell).getColor());
                list.add((Street) cell);
                streets.put(((Street) cell).getColor(), list);
            }
        }
        return streets;
    }

    private List<RailRoad> defineRailRoad(Cell [] cells) {
        List<RailRoad> list = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.getClass() == RailRoad.class) {
                list.add((RailRoad) cell);
            }
        }
        return list;
    }

    private List<UtilityCompany> defineUtilityCompany(Cell [] cells) {
        List<UtilityCompany> list = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.getClass() == UtilityCompany.class) {
                list.add((UtilityCompany) cell);
            }
        }
        return list;
    }

    public List<Street> getStreetTheSameColor(Street.Color color){
        return streetOfTheSameColor.get(color);
    }
    public List <RailRoad> getAllRailRoads() {
        return railRoads;
    }
    public List <UtilityCompany> getUtilityCompanies() {
        return utilityCompanies;
    }
    public Cell[] getCells() {
        return cells;
    }
    public int getConstCell() { return cells.length; }
}
