import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gonzalonunez on 3/16/17.
 */
public class HexagonTests {
    @Test
    public void testHexagonInitializedWithCorrectTerrainType() {
        TerrainType volcano = TerrainType.VOLCANO;
        Hexagon hex = new Hexagon(volcano);
        Assert.assertEquals(volcano, hex.getTerrainType());
    }
}
