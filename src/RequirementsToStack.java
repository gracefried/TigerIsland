/**
 * Created by gonzalonunez on 4/5/17.
 */
import java.awt.Point;

public class RequirementsToStack {
    private Point offset;
    private int orientation;

    public RequirementsToStack(Point offset, int orientation) {
        this.offset = offset;
        this.orientation = orientation;
    }

    public Point getOffset() {
        return offset;
    }

    public int getOrientation() {
        return orientation;
    }
}
