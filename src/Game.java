import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gonzalonunez on 3/16/17.
 */

public class Game {
    private Board board;
    private ArrayList<Player> players;

    public Game(Player... playersArray) {
        players = new ArrayList<Player>(Arrays.asList(playersArray));
        board = new Board();
    }

    public void runGameLoop() {
        while (!players.isEmpty()) {
            int idx = 1;
            for (Player player : players) {
                System.out.println("* Player " + idx + "'s turn:");

                GameActionPerformer actionPerformer = player.getGameActionPerformer();

                Board currentBoard = board; // Do we need a copy here? I don't want to pass a modifiable reference of our Board...
                Tile tile = new Tile(TerrainType.JUNGLE, TerrainType.GRASSLANDS, TerrainType.VOLCANO); //TODO: draw tile instead

                //TODO: Confirm that requested tile placement is valid
                TileCoordinate tileCoordinate = actionPerformer.tileAction(tile, currentBoard);

                //TODO: If valid, place tile at this coordinate!

                //TODO: Confirm that the desired build move is valid
                currentBoard = board; // meh
                actionPerformer.buildAction(tile, currentBoard);

                idx++;
            }
        }
    }

    // Should this be public? Right now it's public for testing purposes...
    public void eliminatePlayer(Player player) {
        players.remove(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}