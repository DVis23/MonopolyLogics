package ru.vsu.cs;

import org.json.simple.parser.ParseException;
import ru.vsu.cs.cells.RailRoad;
import ru.vsu.cs.cells.Street;
import ru.vsu.cs.cells.UtilityCompany;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<Player> players;
    private GameState gameState;
    private final PlayingField playingField = new PlayingField(ReadingPlayingField.readCells());

    public enum GameState {
        PLAYING,
        GAME_OVER
    }

    public Game(ArrayList<Player> players) throws IOException, ParseException {
        this.players = players;
        gameState = GameState.PLAYING;
    }

    public void playerAction(Player player) {
        if (player.getSkipping() == 0) {
            Random random = new Random();
            int step = random.nextInt(13 - 2) + 2;

            int numberCell = player.getStep() + step;
            if (numberCell == playingField.getConstCell()) numberCell = 0;
            if (numberCell > playingField.getConstCell()) {
                numberCell = numberCell - playingField.getConstCell();
                playingField.getCells()[0].action(player, playingField, players);
            }

            player.setStep(numberCell);
            Cell cell = playingField.getCells()[numberCell];
            cell.action(player, playingField, players);
            if (player.getLiberalValues() < 0) deletePlayer(player);
            if (players.size() == 1) gameState = GameState.GAME_OVER;
        } else player.setSkipping(player.getSkipping() - 1);
    }

    public void deletePlayer(Player player) {
        players.remove(player);
        Cell [] cells = playingField.getCells();
        for (Cell cell : cells) {
            if (cell.getClass() == Street.class) {
                if (((Street) cell).getOwner() == player) {
                    ((Street) cell).deleteOwner(playingField);
                }
            }
            if (cell.getClass() == RailRoad.class) {
                if (((RailRoad) cell).getOwner() == player) {
                    ((RailRoad) cell).deleteOwner();
                }
            }
            if (cell.getClass() == UtilityCompany.class) {
                if (((UtilityCompany) cell).getOwner() == player) {
                    ((UtilityCompany) cell).deleteOwner();
                }
            }
        }
    }


    public GameState getGameState() {
        return gameState;
    }
    public void setGameState() {gameState = GameState.GAME_OVER;}
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public PlayingField getPlayingField() {return playingField;}

}
