import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gonzalonunez on 3/16/17.
 */
public class HexagonTests {
    @Test
    public void testHexagonInitializedWithCorrectTerrainType() {
        Hexagon hex = new Hexagon();
        TerrainType volcano = TerrainType.VOLCANO;
        hex.setTerrainType(volcano);
        Assert.assertEquals(volcano, hex.getTerrainType());
    }
    @Test
    public void getLevelTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.incrementLevel();
        Assert.assertEquals(2, hex.getLevel());
    }
    @Test
    public void getMeepleOnTopTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.addPiece(new Meeple(1));
        Assert.assertEquals(true, hex.getMeepleOnTop());
    }
    @Test
    public void getTotoroOnTopTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.addPiece(new Totoro(1));
        Assert.assertEquals(true, hex.getTotoroOnTop());
    }
    @Test
    public void getTigerOnTopTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.incrementLevel();
        hex.incrementLevel();
        Assert.assertEquals(3, hex.getLevel());
        hex.addPiece(new Tiger(1));
        Assert.assertEquals(true, hex.getTigerOnTop());
    }
    @Test
    public void getMeepleSizeTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.incrementLevel();
        hex.incrementLevel();
        hex.addPiece(new Meeple(1));
        hex.addPiece(new Meeple(1));
        hex.addPiece(new Meeple(1));
        Assert.assertEquals(3, hex.getMeepleSize());
    }
    @Test
    public void getTotoroSizeTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.addPiece(new Totoro(1));
        Assert.assertEquals(1, hex.getTotoroSize());
    }
    @Test
    public void getTigerSizeTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.incrementLevel();
        hex.incrementLevel();
        hex.addPiece(new Tiger(1));
        Assert.assertEquals(1, hex.getTigerSize());
    }
    @Test
    public void getMeepleTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.addPiece(new Meeple(1));
        Assert.assertTrue(hex.getPiece() instanceof Meeple);
    }
    @Test
    public void getTotoroTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.addPiece(new Totoro(1));
        Assert.assertTrue(hex.getPiece() instanceof Totoro);
    }
    @Test
    public void getTigerTest() {
        Hexagon hex = new Hexagon();
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.incrementLevel();
        hex.incrementLevel();
        hex.addPiece(new Tiger(1));
        Assert.assertTrue(hex.getPiece() instanceof Tiger);
    }
    @Test
    public void removeMeepleTest() {
        Hexagon hex = new Hexagon();
        Meeple meepleToBeRemoved = new Meeple(1);
        TerrainType grass = TerrainType.GRASSLANDS;
        hex.setTerrainType(grass);
        hex.incrementLevel();
        hex.incrementLevel();
        hex.incrementLevel();
        hex.addPiece(new Meeple(1));
        hex.addPiece(new Meeple(1));
        hex.addPiece(meepleToBeRemoved);
        hex.removeMeeple(meepleToBeRemoved);
        Assert.assertEquals(2, hex.getMeepleSize());
    }

}
