public class Tile {
    private Hexagon topHex;
    private Hexagon rightHex;
    private Hexagon leftHex;

    public Tile(TerrainType leftType, TerrainType rightType, TerrainType topType) {
        topHex = new Hexagon(topType);
        rightHex = new Hexagon(rightType);
        leftHex = new Hexagon(leftType);
    }

    public TerrainType getTerrainTypeForPosition(HexagonPosition position) {
        switch (position) {
            case TOP:
                return topHex.getTerrainType();
            case RIGHT:
                return rightHex.getTerrainType();
            case LEFT:
                return leftHex.getTerrainType();
        }
        return null;
    }
}
