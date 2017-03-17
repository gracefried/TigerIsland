import org.junit.Test;

/**
 * Created by taylo on 3/16/2017.
 */
public class BoardTests {
    @Test
    public void createBoardAndPlaceTileAndPrint() {
        Board gameBoard = new Board();

        TerrainType volcano = TerrainType.VOLCANO;
        TerrainType grasslands = TerrainType.GRASSLANDS;
        TerrainType jungle = TerrainType.JUNGLE;
        Tile tile1 = new Tile(volcano, grasslands, jungle);
        Tile tile2 = new Tile(grasslands, volcano, jungle);

        gameBoard.placeTile(tile1, 200, 200);
        gameBoard.printBoard();

        System.out.println();
        gameBoard.placeTile(tile2, 204, 200);
        gameBoard.printBoard();

        System.out.println();
        gameBoard.placeTile(tile2, 200, 200);
        gameBoard.printBoard();
    }
}
