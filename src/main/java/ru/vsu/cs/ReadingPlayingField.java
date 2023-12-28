package ru.vsu.cs;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import ru.vsu.cs.cells.*;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class ReadingPlayingField {

    public static Cell [] readCells(String dir) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Street> streetsLists = objectMapper.readValue(
                new File(dir + "\\street.json"), new TypeReference<List<Street>>() {});
        List<RailRoad> railRoadList = objectMapper.readValue(
                new File(dir + "\\rail_road.json"), new TypeReference<List<RailRoad>>() {});
        List<UtilityCompany> utilityCompanyList = objectMapper.readValue(
                new File(dir + "\\utility_company.json"), new TypeReference<List<UtilityCompany>>() {});
        List<Tax> taxList = objectMapper.readValue(
                new File(dir + "\\tax.json"), new TypeReference<List<Tax>>() {});
        List<Chance> chanceList = objectMapper.readValue(
                new File(dir + "\\chance.json"), new TypeReference<List<Chance>>() {});
        List<CommunityChest> communityChestList = objectMapper.readValue(
                new File(dir + "\\community_chest.json"), new TypeReference<List<CommunityChest>>() {});
        List<FreeParking> freeParkingList = objectMapper.readValue(
                new File(dir + "\\free_parking.json"), new TypeReference<List<FreeParking>>() {});
        List<GoToJail> goToJailList= objectMapper.readValue(
                new File(dir + "\\go_to_jail.json"), new TypeReference<List<GoToJail>>() {});
        List<Jail> jailList = objectMapper.readValue(
                new File(dir + "\\jail.json"), new TypeReference<List<Jail>>() {});
        StartCell startCell = objectMapper.readValue(
                new File(dir + "\\start_cell.json"), new TypeReference<StartCell>() {});

        int cellsCount = streetsLists.size() + railRoadList.size() + utilityCompanyList.size() +
                taxList.size() + chanceList.size() + communityChestList.size() +
                freeParkingList.size() + goToJailList.size() + jailList.size() + 1;

        Cell [] cells = new Cell[cellsCount];

        cells[startCell.getIndex()] = startCell;

        for (Street street : streetsLists) cells[street.getIndex()] = street;
        for (RailRoad railRoad : railRoadList) cells[railRoad.getIndex()] = railRoad;
        for (UtilityCompany utilityCompany : utilityCompanyList) cells[utilityCompany.getIndex()] = utilityCompany;
        for (Tax tax : taxList) cells[tax.getIndex()] = tax;
        for (Chance chance : chanceList) cells[chance.getIndex()] = chance;
        for (CommunityChest communityChest : communityChestList) cells[communityChest.getIndex()] = communityChest;
        for (FreeParking freeParking : freeParkingList) cells[freeParking.getIndex()] = freeParking;
        for (GoToJail goToJail : goToJailList) cells[goToJail.getIndex()] = goToJail;
        for (Jail jail : jailList) cells[jail.getIndex()] = jail;

    return cells;
    }
}
