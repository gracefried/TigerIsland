/**
 * Created by hugh on 4/2/17.
 * This class is responsible for managing all the settlements on the board
 * It keeps a list of settlements - which are a list of Points
 */

import java.awt.*;
import java.util.ArrayList;

public class SettlementManager {
    private ArrayList<Settlement> listOfSettlements = new ArrayList<>();

    public SettlementManager() {

    }

    /* TODO: BFS STUFF
    public int calculateSettlementSizeAtPoint(Point pointToCheck){
        TerrainType pointTerrainType = gameBoard.getTerrainTypeAtPoint(pointToCheck);

        Queue<Point> adjacentSpaces = new LinkedList<Point>();
        ArrayList<Point> visitedSpaces = new ArrayList<Point>();

        adjacentSpaces.add(pointToCheck);
        visitedSpaces.add(pointToCheck);

        while(!adjacentSpaces.isEmpty()){

        }
    }
    */

    public void addNewSettlement(Settlement settlementToAdd){
        listOfSettlements.add(settlementToAdd);
    }

    // Given a point, runs through every settlement and checks if the point is in a settlement
    // and returns that size
    public int calculateSettlementSizeAtPoint(Point pointToCheck){
        for(Settlement s : listOfSettlements){
            if (s.pointExistsInThisSettlement(pointToCheck)){
                return s.getSettlementSize();
            }
        }
        return 0;
    }

    // Given a point, checks the list of settlements and checks every settlement list of points
    // for the point specified. Otherwise returns null.
    public Settlement getSettlementFromPoint(Point pointTocheck){
        for(Settlement s : listOfSettlements){
            if(s.pointExistsInThisSettlement(pointTocheck)){
                return s;
            }
        }
        return null;
    }

    // Given two settlements, it takes the list of points in the second settlement
    // and adds them to the list in the first settlement
    public void mergeSettlement(Settlement settlementToAddTo, Settlement settlementToDelete){
        settlementToAddTo.getPointsInSettlement().addAll(settlementToDelete.getPointsInSettlement());
        listOfSettlements.remove(settlementToDelete);
    }

    // Overloaded method that merges to settlements given two points
    public void mergeSettlement(Point pointInNewSettlement, Point pointInOldSettlememnt){
        // Do nothing if the two points are the same
        if(pointInNewSettlement.equals(pointInOldSettlememnt)){
            return;
        }

        Settlement settlementToAddTo = null;
        Settlement settlementToDelete = null;
        for(Settlement s : listOfSettlements){
            if(s.pointExistsInThisSettlement(pointInNewSettlement)){
                settlementToAddTo = s;
            }
            if(s.pointExistsInThisSettlement(pointInOldSettlememnt)){
                 settlementToDelete = s;
            }
        }
        // If the settlements found in the list are the same, that means the points are in the same settlement
        if(settlementToAddTo == settlementToDelete){
            return;
        }

        // This may throw an error if one of the settlements is not found
        mergeSettlement(settlementToAddTo, settlementToDelete);
    }

    public void updateSettlements(Board board) {
        /*
        Hexagon[][] gameBoard = board.boardStorage;
        ArrayList<Hexagon> visited = new ArrayList<>();
        ArrayList<Settlement> updatedListOfSettlements = new ArrayList<>();

        int nextTileID = 1;
        int minBoardX = 3;
        int maxBoardX = 397;
        int minBoardY = 3;
        int maxBoardY = 397;

        for (int ii = minBoardX; ii < maxBoardX; ii++) {
            for (int jj = minBoardY; jj < maxBoardY; jj++) {
                if(gameBoard.get(jj).get(ii).getOccupied() && !visited.contains(gameBoard.get(jj).get(ii))) {
                    Settlement newSettlement = new Settlement(new Point(jj, ii));
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


                    Hexagon tempHex = board.copyOfHexagonAtPoint(new Point(ii, jj));
                    int playerID = tempHex.getPiece().getPlayerID();
                    ArrayList<Hexagon> queue = new ArrayList<>();

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
                        //put all of its neighbors into the queue
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
                        newSettlement.getPointsInSettlement().add(new Point(visitingHexagonX, visitingHexagonY));
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
                            visitingHexagonX
                                    --;
                        }
                        else if(visitingHexagon == hexUpRight){
                            visitingHexagonY--;
                            visitingHexagonX++;
                        }
                    }
                    updatedListOfSettlements.add(newSettlement);
                }

            }
        }
        this.listOfSettlements = updatedListOfSettlements;
        */
    }

}
