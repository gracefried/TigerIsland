import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javafx.geometry.Point3D;

public class Board {
    private Hexagon[][] boardStorage;

    private int nextTileID = 1;

    private int dimensions = 200;

    private int minBoardX = -3;
    private int maxBoardX = 3;

    private int minBoardY = -3;
    private int maxBoardY = 3;

    private ArrayList<Settlement> settlementList;

    public Board() {
        int dim = dimensions;

        boardStorage = new Hexagon[dim][dim];

        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                boardStorage[i][j] = new Hexagon(TerrainType.EMPTY, 0,0);
            }
        }

        // Place first tile

        Point point = boardPointForOffset(new Point(0, 0));
        hexagonAtPoint(point).setTerrainType(TerrainType.VOLCANO);

        point = boardPointForOffset(new Point(0, -1));
        hexagonAtPoint(point).setTerrainType(TerrainType.JUNGLE);

        point = boardPointForOffset(new Point(1, -1));
        hexagonAtPoint(point).setTerrainType(TerrainType.LAKE);

        point = boardPointForOffset(new Point(0, 1));
        hexagonAtPoint(point).setTerrainType(TerrainType.GRASSLANDS);

        point = boardPointForOffset(new Point(-1, 1));
        hexagonAtPoint(point).setTerrainType(TerrainType.ROCKY);

        Point[] points = new Point[] {
                boardPointForOffset(new Point(0, 0)),
                boardPointForOffset(new Point(0, -1)),
                boardPointForOffset(new Point(1, -1)),
                boardPointForOffset(new Point(0, 1)),
                boardPointForOffset(new Point(-1, 1))
        };

        for (Point p : points) {
            Hexagon hex = hexagonAtPoint(p);
            hex.incrementLevel();
            hex.setTileID(nextTileID);
            hex.setOccupied(false);

            minBoardX = java.lang.Math.min(minBoardX, (int)p.getX());
            minBoardY = java.lang.Math.min(minBoardY, (int)p.getY());
        }

        nextTileID++;
    }

    // Didn't want to make a new PointUtils class rn sorry not sorry
    static public Point pointTranslatedByPoint(Point point, Point offset) {
        Point copy = new Point(point);
        copy.translate(offset.x, offset.y);
        return copy;
    }

    public Point centerOfBoard() {
        return new Point(dimensions/2, dimensions/2);
    }

    public Point boardPointForOffset(Point offset) {
        return Board.pointTranslatedByPoint(centerOfBoard(), offset);
    }

    public void placeTile(Tile tileToPlace, Point3D centerOffset) {
        Point axialOffset = Board.cubeToAxial(centerOffset);

        HexagonNeighborsCalculator calc = new HexagonNeighborsCalculator(tileToPlace);
        HashMap<HexagonPosition, Point> abOffsets = calc.offsetsForAB();

        Point points[] = new Point[3];
        points[0] = boardPointForOffset(axialOffset);
        points[1] = Board.pointTranslatedByPoint(points[0], abOffsets.get(HexagonPosition.A));
        points[2] = Board.pointTranslatedByPoint(points[0], abOffsets.get(HexagonPosition.B));

        hexagonAtPoint(points[0]).setTerrainType(TerrainType.VOLCANO);
        hexagonAtPoint(points[1]).setTerrainType(tileToPlace.getTerrainTypeForPosition(HexagonPosition.A));
        hexagonAtPoint(points[2]).setTerrainType(tileToPlace.getTerrainTypeForPosition(HexagonPosition.B));

        for (Point point : points) {
            Hexagon hex = hexagonAtPoint(point);
            hex.incrementLevel();
            hex.setTileID(nextTileID);
            hex.setOccupied(false);

            minBoardX = java.lang.Math.min(minBoardX, (int)point.getX());
            minBoardY = java.lang.Math.min(minBoardY, (int)point.getY());
        }

        nextTileID++;
    }

    public HashMap<Point, Boolean> offsetsAtEdgeOfCurrentlyPlayedBoard() {
        // This does a BFS from the center of the board in order to find all the empty hexagons at the edge where we can place a tile.
        HashMap<Point, Boolean> validOffsets = new HashMap<>();
        HashMap<Point, Boolean> visited = new HashMap<>();

        ArrayList<Point> queue = new ArrayList<>();
        queue.add(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point offset = queue.remove(0);

            Point point = boardPointForOffset(offset);
            Hexagon hex = hexagonAtPoint(point);

            // If this hex is empty, we're at the edge of played board. We can potentially place there.
            if (visited.get(offset) == null && hex.getTerrainType() == TerrainType.EMPTY) {
                validOffsets.put(offset, true);
            }

            visited.put(offset, true);

            ArrayList<Point> appliedNeighborOffsets = new ArrayList<>();
            for (Point neighborOffset : HexagonNeighborsCalculator.hexagonNeighborOffsets()) {
                appliedNeighborOffsets.add(Board.pointTranslatedByPoint(offset, neighborOffset));
            }

            for (Point neighborOffset : appliedNeighborOffsets) {
                Point neighborPoint = boardPointForOffset(neighborOffset);
                Hexagon neighborHex = hexagonAtPoint(neighborPoint);

                if (neighborHex.getTerrainType() == TerrainType.EMPTY) {
                    validOffsets.put(neighborOffset, true);
                    visited.put(neighborOffset, true);
                } else if (visited.get(neighborOffset) == null) {
                    queue.add(neighborOffset);
                }
            }
        }

        //TODO: Add spots where we can potentially stack

        return validOffsets;
    }

    public boolean canPlaceTileAtOffset(Tile tile, Point offset) {
        Set<Point> edgePoints = offsetsAtEdgeOfCurrentlyPlayedBoard().keySet();
        if (!edgePoints.contains(offset)) {
            return false;
        }

        HexagonNeighborsCalculator calc = new HexagonNeighborsCalculator(tile);
        HashMap<HexagonPosition, Point> abOffsets = calc.offsetsForAB();

        Point pointA = boardPointForOffset(Board.pointTranslatedByPoint(offset, abOffsets.get(HexagonPosition.A)));
        Point pointB = boardPointForOffset(Board.pointTranslatedByPoint(offset, abOffsets.get(HexagonPosition.B)));

        Hexagon hexA = hexagonAtPoint(pointA);
        Hexagon hexB = hexagonAtPoint(pointB);

        return hexA.getTerrainType() == TerrainType.EMPTY && hexB.getTerrainType() == TerrainType.EMPTY;
    }

    /***** CONVERSIONS *****/

    /*
    # convert cube to axial
    q = x
    r = z
    */

    public static Point cubeToAxial(Point3D cube) {
        return new Point((int)cube.getX(), (int)cube.getZ());
    }

    /*
    # convert axial to cube
    x = q
    z = r
    y = -x-z
    */

    public static Point3D axialToCube(Point point) {
        return new Point3D(point.getX(),-point.getX()-point.getY(), point.getY());
    }

    /***** GETTERS *****/

    public Hexagon hexagonAtPoint(Point p) {
        return boardStorage[p.x][p.y];
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

    public int getMinX() {
        return minBoardX;
    }

    public int getMinY() {
        return minBoardY;
    }

    public int getMaxX() {
        return maxBoardX;
    }

    public int getMaxY() {
        return maxBoardY;
    }

    /*
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

    public ArrayList<Settlement> updateSettlements() {

        ArrayList<Hexagon> visited = new ArrayList();
        ArrayList<Settlement> listOfSettlements = new ArrayList();
        for (int ii = minBoardX; ii < maxBoardX; ii++) {
            for (int jj = minBoardY; jj < maxBoardY; jj++) {
                if(gameBoard.get(jj).get(ii).getOccupied() && !visited.contains(gameBoard.get(jj).get(ii))) {
                    Settlement newSettlement = new Settlement();
                    Hexagon visitingHexagon = gameBoard.get(jj).get(ii);
                    int visitingHexagonX = ii;
                    int visitingHexagonY = jj;
                    int isEvenOrOdd;
                    if(visitingHexagonY % 2 == 0){
                        isEvenOrOdd = 0;
                    }
                    else{
                        isEvenOrOdd = 1;
                    }


                    Hexagon tempHex = hexagonAtPoint(new Point(ii, jj));
                    int playerID = tempHex.getPiece().getPlayerID();
                    ArrayList<Hexagon> queue = new ArrayList();

                    queue.add(visitingHexagon);
                    while(!queue.isEmpty()) {
                        Hexagon hexUp = gameBoard.get(visitingHexagonY-1).get(visitingHexagonX);
                        Hexagon hexDown = gameBoard.get(visitingHexagonY+1).get(visitingHexagonX);
                        Hexagon hexUpRight = gameBoard.get(visitingHexagonY-1).get(visitingHexagonX+1);
                        Hexagon hexUpLeft = gameBoard.get(visitingHexagonY-1).get(visitingHexagonX-1);
                        Hexagon hexLeft = gameBoard.get(visitingHexagonY).get(visitingHexagonX-1);
                        Hexagon hexRight = gameBoard.get(visitingHexagonY).get(visitingHexagonX+1);
                        Hexagon hexDownLeft = gameBoard.get(visitingHexagonY+1).get(visitingHexagonX-1);
                        Hexagon hexDownRight = gameBoard.get(visitingHexagonY+1).get(visitingHexagonX+1);
                        //put all of its neightbors into the queue
                        //Check if it's even
                        if(isEvenOrOdd == 0) {
                            if(hexUp.getOccupied()){
                                if(!visited.contains(hexUp)) {
                                    if (hexUp.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexUp);
                                    }
                                }
                            }
                            else{
                                visited.add(hexUp);
                            }
                            if(hexUpRight.getOccupied()) {
                                if (!visited.contains(hexUpRight)){
                                    if (hexUpRight.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexUpRight);
                                    }
                                }
                            }
                            else{
                                visited.add(hexUpRight);
                            }
                            if(hexLeft.getOccupied()) {
                                if( !visited.contains(hexLeft)) {
                                    if (hexLeft.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexLeft);
                                    }
                                }
                            }
                            else{
                                visited.add(hexLeft);
                            }
                            if(hexRight.getOccupied()) {
                                if(!visited.contains(hexRight)) {
                                    if (hexRight.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexRight);
                                    }
                                }
                            }
                            else{
                                visited.add(hexRight);
                            }
                            if(hexDownRight.getOccupied()) {
                                if(!visited.contains(hexDownRight)) {
                                    if (hexDownRight.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexDownRight);
                                    }
                                }
                            }
                            else{
                                visited.add(hexDownRight);
                            }
                            if(hexDown.getOccupied()) {
                                if (!visited.contains(hexDown)){
                                    if (hexDown.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexDown);
                                    }
                                }
                            }
                            else{
                                visited.add(hexDown);
                            }
                        }
                        //if it's not even, then it's odd
                        else{
                            if(hexUp.getOccupied()) {
                                if(!visited.contains(hexUp)) {
                                    if (hexUp.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexUp);
                                    }
                                }
                            }
                            else{
                                visited.add(hexUp);
                            }
                            if(hexLeft.getOccupied()) {
                                if (!visited.contains(hexLeft)){
                                    if (hexLeft.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexLeft);
                                    }
                                }
                            }
                            else{
                                visited.add(hexLeft);
                            }
                            if(hexRight.getOccupied()) {
                                if(!visited.contains(hexRight)) {
                                    if (hexRight.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexRight);
                                    }
                                }
                            }
                            else{
                                visited.add(hexRight);
                            }
                            if(hexDown.getOccupied()) {
                                if(!visited.contains(hexDown)) {
                                    if (hexDown.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexDown);
                                    }
                                }
                            }
                            else{
                                visited.add(hexDown);
                            }
                            if(hexDownLeft.getOccupied()) {
                                if(!visited.contains(hexDownLeft)) {
                                    if (hexDownLeft.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexDownLeft);
                                    }
                                }
                            }
                            else{
                                visited.add(hexDownLeft);
                            }
                            if(hexUpLeft.getOccupied()) {
                                if(!visited.contains(hexUpLeft)) {
                                    if (hexUpLeft.getPiece().getPlayerID() == playerID) {
                                        queue.add(hexUpLeft);
                                    }
                                }
                            }
                            else{
                                visited.add(hexUpLeft);
                            }
                        }

                        //What we do when we visit
                        //Add our visitingHexagon to the visited list
                        visited.add(visitingHexagon);
                        //update settlementCoordinates
                        newSettlement.occupiedHexes.add(new Point(visitingHexagonX, visitingHexagonY));
                        //pop visitingHexagon out of our queue
                        queue.remove(0);
                        //update our visitingHexagon to the front of the queue
                        visitingHexagon = queue.get(0);

                        //update visitingHexagonX and visitingHexagonY
                        if(visitingHexagon == hexUp){
                            visitingHexagonY--;
                        }
                        else if(visitingHexagon == hexDown){
                            visitingHexagonY++;
                        }
                        else if(visitingHexagon == hexLeft){
                            visitingHexagonX--;
                        }
                        else if(visitingHexagon == hexRight){
                            visitingHexagonX++;
                        }
                        else if(visitingHexagon == hexDownLeft){
                            visitingHexagonY++;
                            visitingHexagonX--;
                        }
                        else if(visitingHexagon == hexDownRight){
                            visitingHexagonY++;
                            visitingHexagonX++;
                        }
                        else if(visitingHexagon == hexUpLeft){
                            visitingHexagonY--;
                            visitingHexagonX--;
                        }
                        else if(visitingHexagon == hexUpRight){
                            visitingHexagonY--;
                            visitingHexagonX++;
                        }
                    }
                    listOfSettlements.add(newSettlement);
                }

            }
        }
        settlementList = listOfSettlements;
        return listOfSettlements;
    }
    */
}