import javax.swing.*;

public class Player {
    private GameActionPerformer gameActionPerformer;
    private int numberOfVillagers;
    private int numberOfTotoros;
    private int numberOfTigers;

    // The tile the player has just drawn
    // Currently set to public for testing purposes
    // Maybe move this responsibility elsewhere
    Tile tileInHand;

    public Player(GameActionPerformer actionPerformer) {
        gameActionPerformer = actionPerformer;
        numberOfVillagers = 20;
        numberOfTotoros = 3;
        numberOfTigers = 2;
        tileInHand = null;
    }

    public GameActionPerformer getGameActionPerformer() {
        return gameActionPerformer;
    }

    public int getNumberOfVillagers(){
        return numberOfVillagers;
    }

    public void removeVillagerFromInventory(int numToRemove){
        numberOfVillagers -= numToRemove;
    }
}