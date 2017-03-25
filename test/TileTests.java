import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gonzalonunez on 3/16/17.
 */
public class TileTests {
    @Test
    public void testTileInitializedWithCorrectTerrainTypes() {
        TerrainType volcano = TerrainType.VOLCANO;
        TerrainType grasslands = TerrainType.GRASSLANDS;
        TerrainType jungle = TerrainType.JUNGLE;

        Tile tile = new Tile(volcano, grasslands, jungle);

        Assert.assertEquals(jungle, tile.getTerrainTypeForPosition(HexagonPosition.MIDDLE));
        Assert.assertEquals(grasslands, tile.getTerrainTypeForPosition(HexagonPosition.RIGHT));
        Assert.assertEquals(volcano, tile.getTerrainTypeForPosition(HexagonPosition.LEFT));
    }

    @Test
    public void testTileClockwiseRotation() {
        TerrainType volcano = TerrainType.VOLCANO;
        TerrainType grasslands = TerrainType.GRASSLANDS;
        TerrainType jungle = TerrainType.JUNGLE;

        Tile tile = new Tile(volcano, grasslands, jungle);

        tile.clockwiseRotation(); //topHeavy Tile rotating clockwise

        Assert.assertEquals(jungle, tile.getTerrainTypeForPosition(HexagonPosition.LEFT));
        Assert.assertEquals(volcano, tile.getTerrainTypeForPosition(HexagonPosition.RIGHT));
        Assert.assertEquals(grasslands, tile.getTerrainTypeForPosition(HexagonPosition.MIDDLE));

        tile.clockwiseRotation(); //bottomHeavy Tile rotating clockwise

        Assert.assertEquals(grasslands, tile.getTerrainTypeForPosition(HexagonPosition.LEFT));
        Assert.assertEquals(jungle, tile.getTerrainTypeForPosition(HexagonPosition.RIGHT));
        Assert.assertEquals(volcano, tile.getTerrainTypeForPosition(HexagonPosition.MIDDLE));
    }

    @Test
    public void testTileChangeOrientation() {
        TerrainType volcano = TerrainType.VOLCANO;
        TerrainType grasslands = TerrainType.GRASSLANDS;
        TerrainType jungle = TerrainType.JUNGLE;

        Tile tile = new Tile(volcano, grasslands, jungle);

        tile.changeOrientation(); //topHeavy tile becomes bottomHeavy

        Assert.assertEquals(volcano, tile.getTerrainTypeForPosition(HexagonPosition.LEFT));
        Assert.assertEquals(jungle, tile.getTerrainTypeForPosition(HexagonPosition.RIGHT));
        Assert.assertEquals(grasslands, tile.getTerrainTypeForPosition(HexagonPosition.MIDDLE));

        tile.changeOrientation(); //bottomHeavy tile becomes topHeavy

        Assert.assertEquals(volcano, tile.getTerrainTypeForPosition(HexagonPosition.LEFT));
        Assert.assertEquals(grasslands, tile.getTerrainTypeForPosition(HexagonPosition.RIGHT));
        Assert.assertEquals(jungle, tile.getTerrainTypeForPosition(HexagonPosition.MIDDLE));
    }
}
