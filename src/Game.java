/**
 * Created by gonzalonunez on 3/16/17.
 */

public class Game {
    private Player[] players;

    public Game(Player[] players) {
        this.players = players;
    }

    public void runGameLoop() {
        for (Player player : players) {
            GameAction action = player.action();
            //TODO: perform player actions here. Provide a new tile, and current board.
        }
    }
}