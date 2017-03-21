/**
 * Created by gonzalonunez on 3/16/17.
 */

public class Game {
    private Board board;

    private Player[] players;

    public Game(Player... players) {
        this.players = players;
        board = new Board();
    }

    public void runGameLoop() {
        while (players.length != 0) {
            int idx = 1;
            for (Player player : players) {
                System.out.println("* Player " + idx + "'s turn:");

                GameActionPerformer actionPerformer = player.getGameActionPerformer();

                Board currentBoard = board; // Do we need a copy here? I don't want to pass a modifiable reference of our Board...
                Tile tile = new Tile(TerrainType.JUNGLE, TerrainType.GRASSLANDS, TerrainType.VOLCANO); //TODO: draw tile instead

                //TODO: Confirm that requested tile placement is valid
                TileCoordinate tileCoordinate = actionPerformer.tileAction(tile, currentBoard);

                //TODO: If valid, place tile at this coordinate

                //TODO: Confirm that the desired build move is valid
                currentBoard = board;
                actionPerformer.buildAction(tile, currentBoard);

                idx++;
            }
        }
    }
}