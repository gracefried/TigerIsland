public class Tile {
    private Hexagon middleHex;
    private Hexagon rightHex;
    private Hexagon leftHex;
    private TileOrientationType tileOrientation;

    public Tile(TerrainType leftType, TerrainType rightType, TerrainType middleType) {
        middleHex = new Hexagon(middleType);
        rightHex = new Hexagon(rightType);
        leftHex = new Hexagon(leftType);
        tileOrientation = TileOrientationType.TOPHEAVY;
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

    public void clockiseRotation() {
        //swap L and M hexagons
        if(tileOrientation == TileOrientationType.TOPHEAVY) {
            Hexagon toBeMiddleHex = leftHex;
            leftHex = middleHex;
            middleHex = toBeMiddleHex;
        }
        //swap swap M and R hexagons
        else if(tileOrientation == TileOrientationType.BOTTOMHEAVY) {
            Hexagon toBeMiddleHex = rightHex;
            rightHex = middleHex;
            middleHex = toBeMiddleHex;
        }
    }

    public void counterClockwiseRotation() {
        //swap M and R hexagons
        if(tileOrientation == TileOrientationType.TOPHEAVY) {
            Hexagon toBeMiddleHex = rightHex;
            rightHex = middleHex;
            middleHex = toBeMiddleHex;
        }
        //swap L and M hexagons
        else if(tileOrientation == TileOrientationType.BOTTOMHEAVY) {
            Hexagon toBeMiddleHex = leftHex;
            leftHex = middleHex;
            middleHex = toBeMiddleHex;
        }
    }
}
