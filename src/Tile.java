import javafx.geometry.Point3D;
import java.util.HashMap;

public class Tile {
    private Hexagon aHex;
    private Hexagon bHex;

    private Integer orientation;

    public Tile(TerrainType leftType, TerrainType rightType) {
        aHex = new Hexagon(leftType);
        bHex = new Hexagon(rightType);
        orientation = 1;
    }

    public TerrainType getTerrainTypeForPosition(HexagonPosition position) {
        switch (position) {
            case A:
                return aHex.getTerrainType();
            case B:
                return bHex.getTerrainType();

        }
        return null;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

}
