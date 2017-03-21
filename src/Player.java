import javax.swing.*;

public class Player {
    private GameActionPerformer gameActionPerformer;

    public Player(GameActionPerformer actionPerformer) {
        gameActionPerformer = actionPerformer;
    }

    public GameActionPerformer getGameActionPerformer() {
        return gameActionPerformer;
    }
}