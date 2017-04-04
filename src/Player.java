import javax.swing.*;

public class Player {
    private GameActionPerformer gameActionPerformer;
    private Inventory inventory;
    private int playerID;
    // The tile the player has just drawn
    // Currently set to public for testing purposes
    // Maybe move this responsibility elsewhere
    Tile tileInHand;

    public Player(GameActionPerformer actionPerformer) {
        inventory = new Inventory();
        gameActionPerformer = actionPerformer;

        tileInHand = null;
    }

    public GameActionPerformer getGameActionPerformer() {
        return gameActionPerformer;
    }
    public int getMeepleSize(){return inventory.getMeepleSize();}
    public int getTigerSize(){return inventory.getTigerSize();}
    public int getTotoroSize(){return inventory.getTotoroSize();}
    public void placeMeeplePiece(){inventory.removeMeeplePiece();}
    public void placeTigerPiece(){inventory.removeTigerPiece();}
    public void placeTotoroPiece(){inventory.removeTotoroPiece();}



}