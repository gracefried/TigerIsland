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
        Tile tile = new Tile(volcano, grasslands, jungle);

        gameBoard.placeTile(tile, 200, 200);
        gameBoard.printBoard();
    }
}
