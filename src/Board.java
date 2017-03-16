public class Board {
    int[][][] gameBoard = new int[400][400][5];
    //3rd dimension is Level, Terrain, TileID, Meeples (T/F), Valid Space (T/F)
    private int tileID = 1;

    public Board() {
        //Loop through gameBoard
        //At odd indices you cannot place a hex (valid space == false)
        //Whole board is level 0
        for (int ii = 0; ii < 400; ii++) {
            for (int jj = 0; jj < 400; jj++) {
                gameBoard[jj][jj][0] = 0;
                if (ii % 2 == 0 && jj % 2 == 0) {
                    gameBoard[jj][jj][4] = 1;
                } else if (ii % 2 == 1 && jj % 2 == 1){
                    gameBoard[ii][jj][4] = 1;
                } else {
                    gameBoard[ii][jj][4] = 0;
                }
            }
        }
    }

    //Anchor position of a tile is the upper left corner and tiles will be
    //place starting from there
    public boolean placeTile(int xPosition, int yPosition, Tile newTile) {
        if (gameBoard[yPosition][xPosition][4] == 0) {
            return false;
        } else {
            for (int ii = yPosition; ii < yPosition+2; ii++) {
                for (int jj = xPosition; jj < xPosition+3; jj++) {
                    gameBoard[ii][jj][0] += 1;
                    gameBoard[ii][jj][2] = this.tileID;
                    gameBoard[ii][jj][3] = 0;
                }
            }

            this.tileID++;
            gameBoard[yPosition][xPosition][1] = newTile.getTile1();
            gameBoard[yPosition][xPosition+2][1] = newTile.getTile2();
            gameBoard[yPosition+1][xPosition+1][1] = newTile.getTile3();
            return true;
        }
    }

    public void printBoard() {
        for (int ii = 190; ii < 210; ii++) {
            for (int jj = 190; jj < 210; jj++) {
                System.out.print(gameBoard[ii][jj][2]);
            }
            System.out.println();
        }
    }
}
