public class Board {
    Hexagon[][] gameBoard = new Hexagon[400][400];
    private int nextTileID = 1;

    public Board() {
        for (int ii = 0; ii < 400; ii++) {
            for (int jj = 0; jj < 400; jj++) {
                gameBoard[ii][jj] = new Hexagon();
                if (ii % 2 == 0 && jj % 2 == 0) {
                    gameBoard[ii][jj].setSpaceAsValid(true);
                } else if (ii % 2 == 1 && jj % 2 == 1) {
                    gameBoard[ii][jj].setSpaceAsValid(true);
                } else {
                    gameBoard[ii][jj].setSpaceAsValid(false);
                }
            }
        }
    }

    public boolean placeTile(Tile tileToPlace, int xPosition, int yPosition) {
        if (!gameBoard[yPosition][xPosition].getSpaceIsValid()) {
            return false;
        } else {
            /*
                No rule enforcement here except for placing in a valid location
             */

            for (int ii = yPosition; ii < yPosition+2; ii++) {
                for (int jj = xPosition; jj < xPosition+3; jj++) {
                    gameBoard[ii][jj].setLevel(1);
                    gameBoard[ii][jj].setTileID(nextTileID);
                    gameBoard[ii][jj].setOccupied(false);
                }
            }
            this.nextTileID++;

            TerrainType left = tileToPlace.getTerrainTypeForPosition(HexagonPosition.LEFT);
            TerrainType right = tileToPlace.getTerrainTypeForPosition(HexagonPosition.RIGHT);
            TerrainType top = tileToPlace.getTerrainTypeForPosition(HexagonPosition.TOP);

            gameBoard[yPosition][xPosition].setTerrainType(left);
            gameBoard[yPosition][xPosition+2].setTerrainType(right);
            gameBoard[yPosition+1][xPosition+1].setTerrainType(top);
            return true;
        }
    }

    public void printBoard() {
        System.out.println("Terrain, TileID, Level");
        for (int ii = 200; ii < 204; ii++) {
            for (int jj = 200; jj < 210; jj++) {
                System.out.print("(" + gameBoard[ii][jj].getTerrainType() + ", " + gameBoard[ii][jj].getTileID() + ", " + gameBoard[ii][jj].getLevel() + ")");
            }
            System.out.println();
        }
    }

    public TerrainType getTerrainTypeAtPosition(int xPosition, int yPosition) {
        return gameBoard[yPosition][xPosition].getTerrainType();
    }

    public int getLevelAtPosition(int xPosition, int yPosition) {
        return gameBoard[yPosition][xPosition].getLevel();
    }

    public int getTileIDAtPosition(int xPosition, int yPosition) {
        return gameBoard[yPosition][xPosition].getTileID();
    }
}