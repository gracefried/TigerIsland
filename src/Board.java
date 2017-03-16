public class Board {
    private Hexagon[][] gameBoard = new Hexagon[400][400];
    private int nextTileID = 1;

    public Board() {
        for (int ii = 0; ii < 400; ii++) {
            for (int jj = 0; jj < 400; jj++) {
                gameBoard[jj][jj] = new Hexagon();
                if (ii % 2 == 0 && jj % 2 == 0) {
                    gameBoard[jj][jj].setSpaceAsValid(true);
                } else if (ii % 2 == 1 && jj % 2 == 1) {
                    gameBoard[jj][jj].setSpaceAsValid(true);
                } else {
                    gameBoard[jj][jj].setSpaceAsValid(false);
                }
            }
        }
    }

    public boolean placeTile(Tile tileToPlace, int xPosition, int yPosition) {
        if (gameBoard[yPosition][xPosition].getSpaceIsValid()) {
            return false;
        } else {
            gameBoard[yPosition][xPosition].setLevel(1);
            gameBoard[yPosition][xPosition].setTerrainType(tileToPlace.getTerrainTypeForPosition(HexagonPosition.LEFT));
            gameBoard[yPosition][xPosition].setTileID(this.nextTileID);
            gameBoard[yPosition][xPosition].setOccupied(false);

            gameBoard[yPosition][xPosition+2].setLevel(1);
            gameBoard[yPosition][xPosition+2].setTerrainType(tileToPlace.getTerrainTypeForPosition(HexagonPosition.RIGHT));
            gameBoard[yPosition][xPosition+2].setTileID(this.nextTileID);
            gameBoard[yPosition][xPosition+2].setOccupied(false);

            gameBoard[yPosition+1][xPosition+1].setLevel(1);
            gameBoard[yPosition+1][xPosition+1].setTerrainType(tileToPlace.getTerrainTypeForPosition(HexagonPosition.TOP));
            gameBoard[yPosition+1][xPosition+1].setTileID(this.nextTileID);
            gameBoard[yPosition+1][xPosition+1].setOccupied(false);

            this.nextTileID++;
            return true;
        }
    }

    public void printBoard() {
        System.out.println(gameBoard[200][200].getLevel());
        System.out.println(gameBoard[200][200].getTerrainType());
        System.out.println(gameBoard[200][200].getTileID());
        System.out.println(gameBoard[200][200].getOccupied());
//
//        for (int ii = 200; ii < 202; ii++) {
//            for (int jj = 200; jj < 203; jj++) {
//                System.out.print("(" + gameBoard[ii][jj].getTerrainType() + ", " + gameBoard[ii][jj].getTileID() + ") ");
//            }
//            System.out.println();
//        }
    }
}
