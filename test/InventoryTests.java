import org.junit.Test;
import org.junit.Assert;

/**
 * Created by user on 3/27/2017.
 */
public class InventoryTests {
    @Test
    public void initMeepleListCountTest(){
        Inventory inventory = new Inventory();
        Assert.assertEquals(20, inventory.getMeepleSize());
    }
    @Test
    public void initTotoroListCountTest(){
        Inventory inventory = new Inventory();
        Assert.assertEquals(3, inventory.getTotoroSize());
    }
    @Test
    public void initTigerListCountTest(){
        Inventory inventory = new Inventory();
        Assert.assertEquals(2, inventory.getTigerSize());
    }
    @Test
    public void getMeepleTest(){
        Inventory inventory = new Inventory();
        Assert.assertEquals(Pieces.MEEPLE,inventory.getMeepleObject());
    }
    @Test
    public void getTotoroTest(){
        Inventory inventory = new Inventory();
        Assert.assertEquals(Pieces.TOTORO,inventory.getTotoroObject());
    }
    @Test
    public void getTigerTest(){
        Inventory inventory = new Inventory();
        Assert.assertEquals(Pieces.TIGER,inventory.getTigerObject());
    }
    @Test
    public void MeepleListCountTest(){
        Inventory inventory = new Inventory();
        inventory.removeMeeplePiece();
        inventory.removeMeeplePiece();
        Assert.assertEquals(18, inventory.getMeepleSize());
    }
    @Test
    public void TotoroListCountTest(){
        Inventory inventory = new Inventory();
        inventory.removeTotoroPiece();
        inventory.removeTotoroPiece();
        Assert.assertEquals(1, inventory.getTotoroSize());
    }
    @Test
    public void TigerListCountTest(){
        Inventory inventory = new Inventory();
        inventory.removeTigerPiece();
        Assert.assertEquals(1, inventory.getTigerSize());
    }
    @Test
    public void isEmptyInventoryTest(){
        Inventory inventory = new Inventory();
        for (int i = 0; i<20; i++){
            inventory.removeMeeplePiece();
        }
        for (int i = 0; i<3; i++){
            inventory.removeTotoroPiece();
        }
        for (int i = 0; i<2; i++){
            inventory.removeTigerPiece();
        }
        Assert.assertEquals(true, inventory.isInventoryEmpty());
    }
}
