package ru.vsu.cs;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.vsu.cs.cells.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadingPlayingField {

    public static Cell [] readCells() throws IOException, ParseException {
        Object streetsObj = new JSONParser().parse(new FileReader("configs\\street.json"));
        Object railRoadsObj = new JSONParser().parse(new FileReader("configs\\rail_road.json"));
        Object utilityCompaniesObj = new JSONParser().parse(new FileReader("configs\\utility_company.json"));
        Object taxesObj = new JSONParser().parse(new FileReader("configs\\tax.json"));
        Object simpleCellsObj = new JSONParser().parse(new FileReader("configs\\simple_cells.json"));
        Object startCellObj = new JSONParser().parse(new FileReader("configs\\start_cell.json"));

        JSONObject streetsJSON = (JSONObject) streetsObj;
        JSONObject railRoadsJSON = (JSONObject) railRoadsObj;
        JSONObject utilityCompaniesJSON = (JSONObject) utilityCompaniesObj;
        JSONObject taxesJSON = (JSONObject) taxesObj;
        JSONObject simpleCellsJSON = (JSONObject) simpleCellsObj;
        JSONObject startCellJSON = (JSONObject) startCellObj;

        JSONArray streetsJsonArray = (JSONArray) streetsJSON.get("Street");
        JSONArray railRoadsJsonArray = (JSONArray) railRoadsJSON.get("RailRoad");
        JSONArray utilityCompaniesJsonArray = (JSONArray) utilityCompaniesJSON.get("UtilityCompany");
        JSONArray taxesJsonArray = (JSONArray) taxesJSON.get("tax");

        JSONArray chancesJsonArray = (JSONArray) simpleCellsJSON.get("Chance");
        JSONArray communityChestsJsonArray = (JSONArray) simpleCellsJSON.get("CommunityChest");
        JSONArray goToJailsJsonArray = (JSONArray) simpleCellsJSON.get("GoToJail");
        JSONArray jailsJsonArray = (JSONArray) simpleCellsJSON.get("Jail");
        JSONArray freeParkingLotsJsonArray = (JSONArray) simpleCellsJSON.get("FreeParking");

        int cellsCount = streetsJsonArray.size() + railRoadsJsonArray.size() + taxesJsonArray.size() +
                utilityCompaniesJsonArray.size() + chancesJsonArray.size() + communityChestsJsonArray.size() +
                goToJailsJsonArray.size() + jailsJsonArray.size() + freeParkingLotsJsonArray.size() + 1;

        Cell [] cells = new Cell[cellsCount];

        Long startCostLong = (Long) startCellJSON.get("cost");
        int startCost = startCostLong.intValue();
        StartCell startCell = new StartCell(startCost);

        cells[0] = startCell;

        for (Object o : streetsJsonArray) {
            JSONObject streetJson = (JSONObject) o;

            Long countLong = (Long) streetJson.get("cell");
            int count = countLong.intValue();

            Long costLong = (Long) streetJson.get("cost");
            int cost = costLong.intValue();

            String colorStr = (String) streetJson.get("color");
            String name = (String) streetJson.get("name");
            Street.Color color = Street.colorFromString(colorStr);

            Street street = new Street(name, color, cost);
            cells[count] = street;
        }

        for (Object o : railRoadsJsonArray) {
            JSONObject railRoadJson = (JSONObject) o;

            Long countLong = (Long) railRoadJson.get("cell");
            int count = countLong.intValue();

            Long costLong = (Long) railRoadJson.get("cost");
            int cost = costLong.intValue();

            String name = (String) railRoadJson.get("name");

            RailRoad railRoad = new RailRoad(name, cost);
            cells[count] = railRoad;
        }

        for (Object o : utilityCompaniesJsonArray) {
            JSONObject utilityCompanyJson = (JSONObject) o;

            Long countLong = (Long) utilityCompanyJson.get("cell");
            int count = countLong.intValue();

            Long costLong = (Long) utilityCompanyJson.get("cost");
            int cost = costLong.intValue();

            String name = (String) utilityCompanyJson.get("name");

            UtilityCompany utilityCompany = new UtilityCompany(name, cost);
            cells[count] = utilityCompany;
        }

        for (Object o : taxesJsonArray) {
            JSONObject taxJson = (JSONObject) o;

            Long countLong = (Long) taxJson.get("cell");
            int count = countLong.intValue();
            Long costLong = (Long) taxJson.get("cost");
            int cost = costLong.intValue();

            Tax tax = new Tax(cost);
            cells[count] = tax;
        }

        if (chancesJsonArray.size() != 0) {
            Chance chance = new Chance();
            for (Object o : chancesJsonArray) {
                Long countLong = (Long) o;
                int count = countLong.intValue();
                cells[count] = chance;
            }
        }
        if (communityChestsJsonArray.size() != 0) {
            CommunityChest communityChest = new CommunityChest();
            for (Object o : communityChestsJsonArray) {
                Long countLong = (Long) o;
                int count = countLong.intValue();
                cells[count] = communityChest;
            }
        }

        for (Object o : goToJailsJsonArray) {
            Long countLong = (Long) o;
            int count = countLong.intValue();
            GoToJail goToJail = new GoToJail();
            cells[count] = goToJail;
        }
        for (Object o : jailsJsonArray) {
            Long countLong = (Long) o;
            int count = countLong.intValue();
            Jail jail = new Jail();
            cells[count] = jail;
        }
        for (Object o : freeParkingLotsJsonArray) {
            Long countLong = (Long) o;
            int count = countLong.intValue();
            FreeParking freeParking = new FreeParking();
            cells[count] = freeParking;
        }


    return cells;
    }
}
