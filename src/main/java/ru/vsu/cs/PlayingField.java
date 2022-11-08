package ru.vsu.cs;

import ru.vsu.cs.cells.*;

import java.util.HashMap;

public class PlayingField {
    private Chance chance = new Chance();
    private CommunityChest communityChest = new CommunityChest();

    private Cell [] cells = new Cell[] {
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

    private final HashMap<Street.Color, Street []> streetOfTheSameColor = new HashMap<>() {{
        put(Street.Color.BROWN, new Street[] {(Street) cells[1], (Street) cells[3]});
        put(Street.Color.WHITE, new Street[] {(Street) cells[6], (Street) cells[8], (Street) cells[9]});
        put(Street.Color.ROSE, new Street[] {(Street) cells[11], (Street) cells[13], (Street) cells[14]});
        put(Street.Color.ORANGE, new Street[] {(Street) cells[16], (Street) cells[18], (Street) cells[19]});
        put(Street.Color.RED, new Street[] {(Street) cells[21], (Street) cells[23], (Street) cells[24]});
        put(Street.Color.YELLOW, new Street[] {(Street) cells[26], (Street) cells[27], (Street) cells[29]});
        put(Street.Color.GREEN, new Street[] {(Street) cells[31], (Street) cells[32], (Street) cells[34]});
        put(Street.Color.BLUE, new Street[] {(Street) cells[37], (Street) cells[39] });
    }};

    private RailRoad [] railRoads = new RailRoad[] {
            (RailRoad) cells[5], (RailRoad) cells[15], (RailRoad) cells[25], (RailRoad) cells[35]
    };

    private UtilityCompany [] utilityCompanies = new UtilityCompany[] {
            (UtilityCompany) cells[12], (UtilityCompany) cells[28]
    };

    public PlayingField(){
    }

    public Street [] getStreetTheSameColor(Street.Color color){
        return streetOfTheSameColor.get(color);
    }
    public RailRoad [] getAllRailRoads() {
        return railRoads;
    }
    public UtilityCompany [] getUtilityCompanies() {
        return utilityCompanies;
    }
    public Cell[] getCells() {
        return cells;
    }
    public int getConstCell() { return cells.length; }
}
