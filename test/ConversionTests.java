import org.junit.Assert;
import org.junit.Test;

import javafx.geometry.Point3D;
import java.awt.Point;

/**
 * Created by gonzalonunez on 4/5/17.
 */
public class ConversionTests {
    @Test
    public void testAxialToCube() {
        Point axial = new Point(-1, -1);
        Point3D cubeEquivalent = new Point3D(-1, 2, -1);
        Assert.assertEquals(Board.axialToCube(axial), cubeEquivalent);
    }

    @Test
    public void testCubeToAxial() {
        Point3D cube = new Point3D(-1, 2, -1);
        Point axialEquivalent = new Point(-1, -1);
        Assert.assertEquals(Board.cubeToAxial(cube), axialEquivalent);
    }
}
