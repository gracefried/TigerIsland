import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Board {
    private ArrayList<ArrayList<Hexagon>> gameBoard = new ArrayList<>();
    private SettlementManager settlementManager = new SettlementManager();
    private int nextTileID = 1;
    private int minBoardX = 3;
    private int maxBoardX = 397;
    private int minBoardY = 3;
    private int maxBoardY = 397;

    public Board() {
        for (int ii = 0; ii < 400; ii++) {
            ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();

            for (int jj = 0; jj < 400; jj++) {
                Hexagon hex = new Hexagon();

                if (ii % 2 == 0 && jj % 2 == 0) {
                    hex.setSpaceAsValid(true);
                } else if (ii % 2 == 1 && jj % 2 == 1) {
                    hex.setSpaceAsValid(true);
                } else {
                    hex.setSpaceAsValid(false);
                }

                hexagons.add(hex);
            }

            gameBoard.add(hexagons);
        }
    }

    // Copy constructor
    public Board(Board aBoard) {
        gameBoard = aBoard.getGameBoardCopy();
        nextTileID = aBoard.nextTileID;
        minBoardX = aBoard.minBoardX;
        maxBoardX = aBoard.maxBoardX;
        minBoardY = aBoard.minBoardY;
        maxBoardY = aBoard.maxBoardY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Board other = (Board)obj;

        return other.gameBoard.equals(gameBoard) &&
                other.gameBoard != gameBoard &&
                other.nextTileID == nextTileID &&
                other.minBoardY == minBoardY &&
                other.maxBoardY == maxBoardY &&
                other.minBoardX == minBoardX &&
                other.maxBoardX == maxBoardX;
    }

    public void placeTile(Tile tileToPlace, Point tileCoordinate) throws IllegalArgumentException {
        HashMap<Point, Boolean> validCoordinates = validPositionsForNewTile(tileToPlace);

        if (validCoordinates.get(tileCoordinate) == null) {
            throw new IllegalArgumentException("{ " + tileCoordinate.getX() + ", " + tileCoordinate.getY() + " } is not a playable spot!");
        }

        HexagonNeighborsCalculator calc = new HexagonNeighborsCalculator(tileCoordinate, tileToPlace.getOrientation(), tileToPlace.getAnchorPosition());
        HashMap<HexagonPosition, Point> points = calc.pointsForTerrainHexagons();

        Point leftPoint = points.get(HexagonPosition.LEFT);
        Point midPoint = points.get(HexagonPosition.MIDDLE);
        Point rightPoint = points.get(HexagonPosition.RIGHT);

        TerrainType left = tileToPlace.getTerrainTypeForPosition(HexagonPosition.LEFT);
        TerrainType right = tileToPlace.getTerrainTypeForPosition(HexagonPosition.RIGHT);
        TerrainType middle = tileToPlace.getTerrainTypeForPosition(HexagonPosition.MIDDLE);

        hexagonAtPoint(leftPoint).setTerrainType(left);
        hexagonAtPoint(rightPoint).setTerrainType(right);
        hexagonAtPoint(midPoint).setTerrainType(middle);

        for (Point point : calc.neighborsWithinTile()) {
            Hexagon hex = hexagonAtPoint(point);
            hex.incrementLevel();
            hex.setOccupied(false);
            hex.setTileID(nextTileID);
        }

        nextTileID++;

        //trying to fin the min and max to print from

        //This case is just the first tile case. Checking the min and max is different in only this case I believe
        if(minBoardX == 3 && minBoardY == 3 && maxBoardX == 397 && maxBoardY == 397){
            for (int ii = minBoardX - 3; ii < maxBoardX+3; ii++) {
                for (int jj = minBoardY; jj < maxBoardY; jj++) {
                    if(gameBoard.get(jj).get(ii).getTileID() != 0){
                        if(ii < maxBoardX){
                            maxBoardX = ii;
                        }
                        if(jj < maxBoardY){
                            maxBoardY = jj;
                        }
                        if(ii > minBoardX){
                            minBoardX = ii;
                        }
                        if(jj > minBoardY){
                            minBoardY = jj;
                        }
                    }
                }
            }
        }
        else {
            for (int ii = minBoardX - 3; ii < maxBoardX + 3; ii++) {
                for (int jj = minBoardY-3; jj < maxBoardY+3; jj++) {
                    if (gameBoard.get(jj).get(ii).getTileID() != 0) {
                        if (ii > maxBoardX) {
                            maxBoardX = ii;
                        }
                        if (jj > maxBoardY) {
                            maxBoardY = jj;
                        }
                        if (ii < minBoardX) {
                            minBoardX = ii;
                        }
                        if (jj < minBoardY) {
                            minBoardY = jj;
                        }
                    }
                }
            }
        }
        //making sure we don't go less than 0 or greater than 400
        if(minBoardX >= 3){
            minBoardX = minBoardX - 3;
        }
        if(minBoardY >= 3){
            minBoardY = minBoardX - 3;
        }
        if(maxBoardX <= 397){
            maxBoardX = maxBoardX + 3;
        }
        if(maxBoardY <= 397){
            maxBoardY = maxBoardX + 3;
        }

    }

    public HashMap<Point, Boolean> validPositionsForNewTile(Tile tile) {
        HashMap<Point, Boolean> validPoints = new HashMap<>();

        Board boardCopy = new Board(this);
        FirstTilePlacementRule firstTilePR = new FirstTilePlacementRule(tile, boardCopy);
        TilePlacementRule placementRule = new TilePlacementRule(tile, boardCopy);

        List<TilePlacementRule> rules = Arrays.asList(firstTilePR, placementRule);

        for (int y = 3; y <= 396; y++) {
            for (int x = 3; x <= 396; x++) {
                Point point = new Point(x, y);
                for (TilePlacementRule rule : rules) {
                    if (rule.tileMeetsConditionsForRuleToApply() && rule.pointIsValid(point)) {
                        validPoints.put(point, true);
                    }
                }
            }
        }

        return validPoints;
    }

    public ArrayList<ArrayList<Hexagon>> getGameBoardCopy() {
        return new ArrayList<ArrayList<Hexagon>>(gameBoard);
    }

    private Hexagon hexagonAtPoint(Point p) {
        return gameBoard.get(p.y).get(p.x);
    }

    public Hexagon copyOfHexagonAtPoint(Point p) {
        return new Hexagon(hexagonAtPoint(p));
    }


    public TerrainType getTerrainTypeAtPoint(Point point) {
        return hexagonAtPoint(point).getTerrainType();
    }


    public int getLevelAtPoint(Point point) {
        return hexagonAtPoint(point).getLevel();
    }

    public int getTileIDAtPoint(Point point) {
        return hexagonAtPoint(point).getTileID();
    }

    public int getNextTileID() {
        return nextTileID;
    }

    public Point getPointOfHexagon(Hexagon hexagon){
        for (int ii = minBoardX - 3; ii < maxBoardX + 3; ii++) {
            for (int jj = minBoardY - 3; jj < maxBoardY + 3; jj++) {
                if (gameBoard.get(jj).get(ii) == hexagon) {
                    Point point = new Point(ii, jj);
                    return point;
                }
            }
        }
        Point fakePoint = new Point(0,0);
        return fakePoint;
    }

    public Point getPointOfWhereVolcanoWillBePlaced(Tile tile){
        TileOrientation tileOrientation = tile.getOrientation();
        //if(tileOrientation ==  tileOrientation.BOTTOMHEAVY && )
        //TODO: how do I do this
        return new Point(0, 0);
    }

    // Founds a new settlement at the specified hexagon
    // Puts one villager at the location and creates a new settlement object
    public void foundSettlementAtPoint(Point point, int id){
        hexagonAtPoint(point).addPiece(new Meeple(id));
        settlementManager.addNewSettlement(new Settlement(point));
    }

    // Places a Totoro at the specified hexagon
    // Assumes that the location is a valid spot
    public void buildTotoroSanctuary(Point point, int id){
        // Add a Totoro to the hexagon
        hexagonAtPoint(point).addPiece(new Totoro(id));

        // Find the particular settlement this point belongs to and add the point to the settlement
        // as well as setting the Totoro to be true;
        Settlement settlementToUpdate = settlementManager.getSettlementFromPoint(point);
        settlementToUpdate.addPointToSettlement(point);
        settlementToUpdate.addTotoro();
    }

    // Places a Tiger at the specified hexagon
    // Assumes that the location is a valid spot
    public void buildTigerPlayground(Point point, int id){
        // Add a Tiger to the hexagon
        hexagonAtPoint(point).addPiece(new Tiger(id));

        // Find the particular settlement this point belongs to and add the point to the settlement
        // as well as setting the Tiger to be true;
        Settlement settlementToUpdate = settlementManager.getSettlementFromPoint(point);
        settlementToUpdate.addPointToSettlement(point);
        settlementToUpdate.addTiger();
    }
}