import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gonzalonunez on 3/21/17.
 */
public class GameTests {
    class MockActionPerformer implements GameActionPerformer {
        public MockActionPerformer() {}

        public Coordinate tileAction(Tile tile, Board board) {
            return new Coordinate(0, 0);
        }

        public BuildAction buildAction(Board board) {
            return new CreateSettlementAction();
        }
    }

    @Test
    public void testGameCreatedWithPlayers() {
        MockActionPerformer actionPerformer = new MockActionPerformer();

        Player p1 = new Player(actionPerformer);
        Player p2 = new Player(actionPerformer);

        Player[] originalPlayers = {p1, p2};

        Game game = new Game(originalPlayers);

        Assert.assertArrayEquals(originalPlayers, game.getPlayers().toArray());
    }

    @Test
    public void testGameEliminatesPlayer() {
        MockActionPerformer actionPerformer = new MockActionPerformer();

        Player p1 = new Player(actionPerformer);
        Player p2 = new Player(actionPerformer);

        Player[] originalPlayers = {p1, p2};
        Player[] onlyPlayer2 = {p2};
        Player[] noPlayers = {};

        Game game = new Game(originalPlayers);

        game.eliminatePlayer(p1);
        Assert.assertArrayEquals(onlyPlayer2, game.getPlayers().toArray());

        game.eliminatePlayer(p1);
        Assert.assertArrayEquals(onlyPlayer2, game.getPlayers().toArray());

        game.eliminatePlayer(p2);
        Assert.assertArrayEquals(noPlayers, game.getPlayers().toArray());
    }
}