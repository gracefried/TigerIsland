import java.awt.Point;

/**
 * Created by gonzalonunez on 3/16/17.
 */

public class Game {
    private Board board;
    private Deck tileDeck = new Deck();

    public Game(Deck deck) {
        board = new Board();
        tileDeck = deck;
    }

    public Game() {
        board = new Board();
    }

    public void applyTileAction(Tile tile, Point point) {

    }

    public void applyBuildAction(BuildAction buildAction) {
        /*

        Point3D point = buildAction.getCoordinates();
        Integer id = buildAction.getID();
        TerrainType terrainType = buildAction.getTerrainType();

        switch (buildAction.getType()) {
            case FOUND_SETTLEMENT:
                board.foundSettlementAtPoint(point, id);
            case EXPAND_SETTLEMENT:
                board.expandSettlement(point, terrainType, id);
            case TIGER_PLAYGROUND:
                board.buildTigerPlayground(point, id);
            case TOTORO_SANCTUARY:
                board.buildTotoroSanctuary(point, id);
        }

        */
    }

    public Board getGameBoard() {
        return board;
    }
}