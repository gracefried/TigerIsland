/**
 * Created by gonzalonunez on 3/28/17.
 */
import java.awt.Point;
import java.util.HashMap;

public class HexagonNeighborsCalculator {
    private int orientation;

    public HexagonNeighborsCalculator(Tile tile) {
        this.orientation = tile.getOrientation();
    }

    public HashMap<HexagonPosition, Point> offsetsForAB() {
        HashMap<HexagonPosition, Point> points = new HashMap<>();
        switch (orientation) {
            case 1:
                points.put(HexagonPosition.A, new Point(0, -1));
                points.put(HexagonPosition.B, new Point(1, -1));
            case 2:
                points.put(HexagonPosition.A, new Point(1, -1));
                points.put(HexagonPosition.B, new Point(1, 0));
            case 3:
                points.put(HexagonPosition.A, new Point(1, 0));
                points.put(HexagonPosition.B, new Point(0, 1));
            case 4:
                points.put(HexagonPosition.A, new Point(0, 1));
                points.put(HexagonPosition.B, new Point(-1, 1));
            case 5:
                points.put(HexagonPosition.A, new Point(-1, 1));
                points.put(HexagonPosition.B, new Point(-1, 0));
            case 6:
                points.put(HexagonPosition.A, new Point(-1, 0));
                points.put(HexagonPosition.B, new Point(0, -1));
            default:
                break;
        }
        return points;
    }

    static public Point[] hexagonNeighborOffsets() {
        return new Point[] {
                    new Point(1,0),
                    new Point(1,-1),
                    new Point(0,-1),
                    new Point(-1,0),
                    new Point(-1,1),
                    new Point( 0,1)
                };
    }

}
