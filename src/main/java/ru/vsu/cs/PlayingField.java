package ru.vsu.cs;

import ru.vsu.cs.cells.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayingField {

    private final Cell [] cells;
    private final HashMap<Street.Color, List<Street>> streetOfTheSameColor;
    private final List <RailRoad> railRoads;
    private final List <UtilityCompany> utilityCompanies;

    public PlayingField(){
        Chance chance = new Chance();
        CommunityChest communityChest = new CommunityChest();
        cells = new Cell[] {
                new StartCell(2000), //0
                new Street("Житная улица", Street.Color.BROWN, 600), //1
                communityChest,//2
                new Street("Нагатинская улица", Street.Color.BROWN, 600),//3
                new Tax(),//4
                new RailRoad("Рижская"),//5
                new Street("Варшавское шоссе", Street.Color.WHITE,1000),//6
                chance,//7
                new Street("Улица Огарева", Street.Color.WHITE,1000),//8
                new Street("Первая Парковая", Street.Color.WHITE,1200),//9
                new Jail(),//10
                new Street("Улица Полянка", Street.Color.ROSE, 1400),//11
                new UtilityCompany("Электростанция"),//12
                new Street("Улица Сретенка", Street.Color.ROSE, 1400),//13
                new Street("Ростовская набережная", Street.Color.ROSE, 1600),//14
                new RailRoad("Курская"),//15
                new Street("Рязанский проспект", Street.Color.ORANGE, 1800),//16
                communityChest,//17
                new Street("Улица Вавилова", Street.Color.ORANGE, 1800),//18
                new Street("Рублевское шоссе", Street.Color.ORANGE, 2000),//19
                new FreeParking(),
                new Street("Улица Тверская", Street.Color.RED, 2000),//21
                chance,
                new Street("Пушкинская улица", Street.Color.RED, 2200),//23
                new Street("Площадь Маяковского", Street.Color.RED, 2400),//24
                new RailRoad("Казанская"),//25
                new Street("Грузинский Вал", Street.Color.YELLOW, 2600),//26
                new Street("Новильский бульвар", Street.Color.YELLOW, 2600),//27
                new UtilityCompany("Водопровод"),//28
                new Street("Смоленская площадь", Street.Color.YELLOW, 2800),//29
                new GoToJail(),
                new Street("Улица Щусева", Street.Color.GREEN, 3000),//31
                new Street("Гоголевский бульвар", Street.Color.GREEN, 3000),//32
                communityChest,
                new Street("Кутузовский проспект", Street.Color.GREEN, 3000),//34
                new RailRoad("Ленинградская"),//35
                chance,
                new Street("Малая Бронная", Street.Color.BLUE, 3500),//37
                new Tax(),
                new Street("Арбат", Street.Color.BLUE, 4000)//39
        };
        streetOfTheSameColor = defineStreet(cells);
        railRoads = defineRailRoad(cells);
        utilityCompanies = defineUtilityCompany(cells);
    }

    public PlayingField(Cell [] cells) {
        this.cells = cells;
        streetOfTheSameColor = defineStreet(cells);
        railRoads = defineRailRoad(cells);
        utilityCompanies = defineUtilityCompany(cells);
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
        }};;
        for (int i = 0; i < cells.length; i++) {
            Cell cell = cells[i];
            if (cell.getClass() == Street.class) {
                List<Street> list = streets.get(((Street)cell).getColor());
                list.add((Street) cell);
                streets.put(((Street)cell).getColor(), list);
            }
        }
        return streets;
    }

    private List<RailRoad> defineRailRoad(Cell [] cells) {
        List<RailRoad> list = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            Cell cell = cells[i];
            if (cell.getClass() == RailRoad.class) {
                list.add((RailRoad) cell);
            }
        }
        return list;
    }

    private List<UtilityCompany> defineUtilityCompany(Cell [] cells) {
        List<UtilityCompany> list = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            Cell cell = cells[i];
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
