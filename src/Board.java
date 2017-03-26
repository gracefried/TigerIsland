import java.util.ArrayList;

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
        //TODO: Place tiles starting from tile's anchor position (6 different cases)

        if (!gameBoard[yPosition][xPosition].getSpaceIsValid()) {
            return false;
        } else {

            //    No rule enforcement here except for placing in a valid location


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
            TerrainType middle = tileToPlace.getTerrainTypeForPosition(HexagonPosition.MIDDLE);

            gameBoard[yPosition][xPosition].setTerrainType(left);
            gameBoard[yPosition][xPosition+2].setTerrainType(right);
            gameBoard[yPosition+1][xPosition+1].setTerrainType(middle);
            return true;
        }
    }

    public ArrayList<Coordinate> determineValidPositionsForNewTile(Tile tileToBePlaced) {
        ArrayList<Coordinate> validCoordinateList = new ArrayList<>();

        //If checking the first tile to be placed
        if (this.nextTileID == 1) {
            Coordinate validCoordinate = new Coordinate(200, 200);
            validCoordinateList.add(validCoordinate);
            return validCoordinateList;
        }

        //TODO: Replace bounds of xCoordinate and yCoordinate in the for loop with min and max bounds of board in play
        for (int yCoordinate = 3; yCoordinate <= 396; yCoordinate++) {
            for (int xCoordinate = 3; xCoordinate <= 396; xCoordinate++) {

                //If the current x,y position is not a valid place to put the anchor continue
                if (!gameBoard[yCoordinate][xCoordinate].getSpaceIsValid())
                    continue;

                if (tileToBePlaced.getOrientation() == TileOrientation.TOPHEAVY) {

                    if (tileToBePlaced.getAnchorPosition() == HexagonPosition.MIDDLE) {
                        //Checks if positions within the new tile are going to overlap existing tiles
                        if (gameBoard[yCoordinate-1][xCoordinate-1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate-1][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate-1][xCoordinate+1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate-1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate+1].getTileID() != 0)
                            continue;

                        //Check if new tile is adjacent to existing tiles
                        //If we found one no need to check the rest
                        if (gameBoard[yCoordinate-2][xCoordinate-2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-2][xCoordinate].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-2][xCoordinate+2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate][xCoordinate+2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate+1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate-1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate][xCoordinate-2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        }

                    } else if (tileToBePlaced.getAnchorPosition() == HexagonPosition.LEFT) {
                        //Checks if positions within the new tile are going to overlap existing tiles
                        if (gameBoard[yCoordinate+1][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate+1][xCoordinate+1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate+1][xCoordinate+2].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate+1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate+2].getTileID() != 0)
                            continue;

                        //Check if new tile is adjacent to existing tiles
                        //If we found one no need to check the rest
                        if (gameBoard[yCoordinate-1][xCoordinate-1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-1][xCoordinate+1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-1][xCoordinate+3].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate-1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate+3].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+2][xCoordinate].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+2][xCoordinate+2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        }

                    } else if (tileToBePlaced.getAnchorPosition() == HexagonPosition.RIGHT) {
                        //Checks if positions within the new tile are going to overlap existing tiles
                        if (gameBoard[yCoordinate+1][xCoordinate-2].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate+1][xCoordinate-1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate+1][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate-2].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate-1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate].getTileID() != 0)
                            continue;

                        //Check if new tile is adjacent to existing tiles
                        //If we found one no need to check the rest
                        if (gameBoard[yCoordinate-1][xCoordinate-3].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-1][xCoordinate-1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-1][xCoordinate+1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate+1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate-3].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+2][xCoordinate].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+2][xCoordinate-2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        }
                    }

                } else if (tileToBePlaced.getOrientation() == TileOrientation.BOTTOMHEAVY) {

                    if (tileToBePlaced.getAnchorPosition() == HexagonPosition.MIDDLE) {
                        //Checks if positions within the new tile are going to overlap existing tiles
                        if (gameBoard[yCoordinate+1][xCoordinate-1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate+1][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate+1][xCoordinate+1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate-1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate+1].getTileID() != 0)
                            continue;

                        //Check if new tile is adjacent to existing tiles
                        //If we found one no need to check the rest
                        if (gameBoard[yCoordinate+2][xCoordinate-2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+2][xCoordinate].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+2][xCoordinate+2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate][xCoordinate+2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate][xCoordinate-2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-1][xCoordinate-1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-1][xCoordinate+1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        }

                    } else if (tileToBePlaced.getAnchorPosition() == HexagonPosition.LEFT) {
                        //Checks if positions within the new tile are going to overlap existing tiles
                        if (gameBoard[yCoordinate-1][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate-1][xCoordinate+1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate-1][xCoordinate+2].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate+1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate+2].getTileID() != 0)
                            continue;

                        //Check if new tile is adjacent to existing tiles
                        //If we found one no need to check the rest
                        if (gameBoard[yCoordinate-1][xCoordinate-1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-1][xCoordinate+3].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-2][xCoordinate].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-2][xCoordinate+2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate-1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate+1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate+3].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        }

                    } else if (tileToBePlaced.getAnchorPosition() == HexagonPosition.RIGHT) {
                        //Checks if positions within the new tile are going to overlap existing tiles
                        if (gameBoard[yCoordinate-1][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate-1][xCoordinate-1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate-1][xCoordinate-2].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate-1].getTileID() != 0)
                            continue;
                        if (gameBoard[yCoordinate][xCoordinate-2].getTileID() != 0)
                            continue;

                        //Check if new tile is adjacent to existing tiles
                        //If we found one no need to check the rest
                        if (gameBoard[yCoordinate-1][xCoordinate-3].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-1][xCoordinate+1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-2][xCoordinate].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate-2][xCoordinate-2].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate+1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate-1].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        } else if (gameBoard[yCoordinate+1][xCoordinate-3].getTileID() != 0) {
                            Coordinate validCoordinate = new Coordinate(xCoordinate, yCoordinate);
                            validCoordinateList.add(validCoordinate);
                        }
                    }
                }
            }
        }

        return validCoordinateList;
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

    public void setVillagersAtPosition(int numOfVillagers, int xPosition, int yPosition){
        gameBoard[yPosition][xPosition].setVillagersOnTop(numOfVillagers);
    }

    public int getVillagerNumberAtPosition(int xPosition, int yPosition) { return gameBoard[yPosition][xPosition].getNumVillagersOnTop(); }
}