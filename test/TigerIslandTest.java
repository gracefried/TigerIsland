import org.junit.Assert;
import org.junit.Test;

public class TigerIslandTest {
    @Test
    public void testAlternateTurns() {
        Player player1 = new Player();
        Player player2 = new Player();
        player2.setTurn();
        Assert.assertEquals(player1.getTurn(), true);
        Assert.assertEquals(player2.getTurn(), false);
    }

    @Test
    public void testNamePlayers() {
        Player player1 = new Player("Taylor");
        Assert.assertEquals(player1.getPlayerName(), "Taylor");
    }

    @Test
    public void createGameBoard() {
        Board gameBoard = new Board();
        Tile newTile = new Tile();
        Assert.assertEquals(true, gameBoard.placeTile(200,200, newTile));
        Assert.assertEquals(true, gameBoard.placeTile(203,203, newTile));
        Assert.assertEquals(false, gameBoard.placeTile(203,202, newTile));

        gameBoard.printBoard();
    }
}
