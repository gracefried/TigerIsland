public class Tile {
    private Hexagon middleHex;
    private Hexagon rightHex;
    private Hexagon leftHex;
    private TileOrientation tileOrientation;

    public Tile(TerrainType leftType, TerrainType rightType, TerrainType middleType) {
        middleHex = new Hexagon(middleType);
        rightHex = new Hexagon(rightType);
        leftHex = new Hexagon(leftType);
        tileOrientation = TileOrientation.TOPHEAVY;
    }

    public TerrainType getTerrainTypeForPosition(HexagonPosition position) {
        switch (position) {
            case MIDDLE:
                return middleHex.getTerrainType();
            case RIGHT:
                return rightHex.getTerrainType();
            case LEFT:
                return leftHex.getTerrainType();
        }
        return null;
    }

    public void clockwiseRotation() {
        //swap L and M hexagons
        if(tileOrientation == TileOrientation.TOPHEAVY) {
            Hexagon toBeMiddleHex = leftHex;
            leftHex = middleHex;
            middleHex = toBeMiddleHex;
            tileOrientation = TileOrientation.BOTTOMHEAVY;
        }
        //swap swap M and R hexagons
        else if(tileOrientation == TileOrientation.BOTTOMHEAVY) {
            Hexagon toBeMiddleHex = rightHex;
            rightHex = middleHex;
            middleHex = toBeMiddleHex;
            tileOrientation = TileOrientation.TOPHEAVY;
        }
    }

    public void counterClockwiseRotation() {
        //swap M and R hexagons
        if(tileOrientation == TileOrientation.TOPHEAVY) {
            Hexagon toBeMiddleHex = rightHex;
            rightHex = middleHex;
            middleHex = toBeMiddleHex;
            tileOrientation = TileOrientation.BOTTOMHEAVY;
        }
        //swap L and M hexagons
        else if(tileOrientation == TileOrientation.BOTTOMHEAVY) {
            Hexagon toBeMiddleHex = leftHex;
            leftHex = middleHex;
            middleHex = toBeMiddleHex;
            tileOrientation = TileOrientation.TOPHEAVY;
        }
    }
}
